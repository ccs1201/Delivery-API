package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.*;
import br.com.ccs.delivery.domain.model.specification.PedidoSpecs;
import br.com.ccs.delivery.domain.model.specification.filter.PedidoFilter;
import br.com.ccs.delivery.domain.repository.PedidoRepository;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository repository;
    @Lazy
    private ProdutoService produtoService;
    @Lazy
    private RestauranteService restauranteService;
    @Lazy
    private TipoPagamentoService tipoPagamentoService;
    @Lazy
    private MunicipioService municipioService;
    @Lazy
    private UsuarioService usuarioService;
    @Lazy
    private MailService mailService;

    public Page<Pedido> findAllEager(Pageable pageable) {
        return repository.findAllEagerPageable(pageable);
    }

    public Collection<Pedido> findAllEager() {
        return repository.findAllEager();
    }

    public Pedido findById(Long id) {
        return repository.findByIdEager(id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Pedido ID: %d não encontrado.", id)
                )
        );
    }

    public Pedido findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).orElseThrow(
                () -> new RepositoryEntityNotFoundException(
                        String.format("Pedido código: %s, não encontrado.", codigo)
                )
        );
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        try {
            return repository.saveAndFlush(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException("Erro ao cadastrar Pedido", e);
        }
    }

    /**
     * <p>
     * Prepara um pedido para ser salvo
     * no banco de dados.
     * </p>
     *
     * @param pedido O pedido a ser cadastrado.
     * @return Pedido — O pedido cadastrado.
     */
    @Transactional
    public Pedido cadastrarPedido(Pedido pedido) {

        this.validarPedido(pedido);

        pedido.criar();

        pedido = this.save(pedido);

        this.sendEmail(pedido);
        return this.findById(pedido.getId());

    }

    /**
     * <p>Valida um Pedido</p>
     * <p>
     * Valida um pedido para que possa ser
     * salvo no Banco de Dados.
     *
     * <ul>
     *           <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.TipoPagamento}</li>
     *           <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Restaurante}</li>
     *           <li>Verifica se o {@link br.com.ccs.delivery.domain.model.entity.Restaurante}
     *           aceita {@link br.com.ccs.delivery.domain.model.entity.TipoPagamento}
     *           informado no {@link Pedido}</li>
     *           <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Municipio}</li>
     *           <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Produto}(s)
     *           ao ItemPedido da {@link Collection<ItemPedido>} </li>
     *           <li>Calcula os valores do {@link Pedido}</li>
     * </ul>
     *
     * @param pedido O pedido que será validado.
     */
    private void validarPedido(Pedido pedido) {
        this.getTipoPagamento(pedido);

        this.getRestaurante(pedido);

        if (!pedido.getRestaurante().validarTipoPagamento(pedido.getTipoPagamento())) {
            throw new TipoPagamentoException(
                    String.format(
                            "Tipo de pagamento %s, não é aceito pelo restaurante.",
                            pedido.getTipoPagamento().getNome()));
        }

        this.findMunicipio(pedido);

        this.getProdutosForItensPedido(pedido.getItensPedido());

        this.validarItensPedido(pedido);
        this.ValidarCliente(pedido);

        pedido.calcularPedido();

    }

    private void ValidarCliente(Pedido pedido) {
        pedido.setCliente(
                usuarioService.findaById(
                        pedido.getCliente().getId()
                ));
    }

    /**
     * <p>Valida os Itens do Pedido</p>
     * <br />
     * Garante que o produtos contidos na
     * {@link Collection} @param pedido.itensPedido
     * contenha somente produtos que constam no
     * cardápio do restaurante a que o pedido se refere.
     * <br />
     *
     * @param pedido O pedido que terá seus Itens validados.
     * @throws ProdutoNaoExisteNoCardapioDoRestauranteException se algum dos
     *                                                          produtos não pertencer ao cardápio do {@link br.com.ccs.delivery.domain.model.entity.Restaurante}
     */
    private void validarItensPedido(Pedido pedido) {

        Set<Produto> produtosInvalidos = new HashSet<>();

        pedido.getRestaurante().setProdutos(
                restauranteService.findProdutos(
                        pedido.getRestaurante().getId(), true).getProdutos());

        pedido.getItensPedido().forEach(itemPedido -> {

            if (!pedido.getRestaurante().validarProduto(itemPedido.getProduto())) {
                produtosInvalidos.add(itemPedido.getProduto());
            }
        });

        if (!produtosInvalidos.isEmpty()) {
            StringBuilder message = new StringBuilder();

            produtosInvalidos.forEach(produto ->
                    message.append(String
                            .format("O produto: %s, não é vendido pelo Restaurante: %s. ",
                                    produto.getNome(), pedido.getRestaurante().getNome())
                    )
            );

            throw new ProdutoNaoExisteNoCardapioDoRestauranteException(message.toString());
        }
    }

    /**
     * <p>
     * Busca o município do Endereço de entrega
     * para o {@link Pedido} que esta sendo cadastrado.
     * </p>
     *
     * @param pedido O Pedido que esta sendo cadastrado.
     */
    private void findMunicipio(Pedido pedido) {
        pedido.getEnderecoEntrega().setMunicipio(
                municipioService
                        .findById(pedido.getEnderecoEntrega().getMunicipio().getId())
        );
    }

    /**
     * <p>
     * Busca o tipo de pagamento do pedido
     * pelo ID do {@link TipoPagamento}.
     * </p>
     *
     * @param pedido O pedido cujo tipo de pagamento deve ser encontrado.
     */
    private void getTipoPagamento(Pedido pedido) {
        pedido.setTipoPagamento(
                tipoPagamentoService
                        .findById(pedido.getTipoPagamento().getId())
        );
    }

    /**
     * <p>
     * Busca os produtos no Banco de Dados
     * pelo seu ID e seta no {@link ItemPedido}
     * </p>
     *
     * @param itensPedido Coleção de itens do Pedido que esta sendo cadastrado.
     */
    private void getProdutosForItensPedido(Collection<ItemPedido> itensPedido) {

        itensPedido.forEach(item -> item
                .setProduto(
                        produtoService
                                .findById(item.getProduto().getId()))
        );
    }

    /**
     * <p>
     * Busca o restaurante para o
     * pedido que esta sendo cadastrado
     * pelo seu ID.
     * </p>
     *
     * @param pedido O pedido que está sendo cadastrado.
     */
    private void getRestaurante(Pedido pedido) {
        pedido.setRestaurante(
                restauranteService
                        .findComTiposPagamento(pedido.getRestaurante().getId())
        );
    }

    /**
     * <p>
     * Cancela um pedido realizado pelo usuário.
     * </p>
     * <p>
     * O cancelamento somente é permitido se
     * o pedido estiver com {@link StatusPedido} CRIADO.
     *
     * @param pedidoId ID do pedido que será cancelado.
     */
    @Transactional
    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = this.findById(pedidoId);

        if (pedido.getStatusPedido() != StatusPedido.CRIADO) {
            throw new StatusPedidoException(
                    String.format("O pedido já esta %s e não pode ser cancelado.", pedido.getStatusPedido().getDescricao()));
        }

        pedido.cancelar();

        repository.saveAndFlush(pedido);

        this.sendEmail(pedido);
    }

    /**
     * <p>Atualiza um pedido</p>
     * <p>
     * Antes de atualizar o pedido
     * executa validação dos dados
     * em {@link #validarPedido(Pedido)}.
     *
     * @param pedido A ser atualizado
     */
    @Transactional
    public void update(Pedido pedido) {

        this.validarPedido(pedido);

        repository.saveAndFlush(pedido);

        this.sendEmail(pedido);
    }

    /**
     * Confirma o aceite de um pedido
     * pelo Restaurante
     *
     * @param pedidoId ID do pedido que será confirmado.
     */
    @Transactional
    public void confirmarPedido(Long pedidoId) {

        Pedido pedido = this.findById(pedidoId);

        if (pedido.getStatusPedido().equals(StatusPedido.CONFIRMADO)) {
            throw new StatusPedidoException("Pedido já confirmado.");
        }

        if (pedido.getStatusPedido() != StatusPedido.CRIADO) {
            throw new StatusPedidoException(
                    String.format("Pedido não pode ser confirmado pois seu Status é: %s", pedido.getStatusPedido().getDescricao()));
        }

        pedido.confirmar();

        repository.saveAndFlush(pedido);

        this.sendEmail(pedido);
    }


    /**
     * Confirma a entrega de um pedido
     *
     * @param pedidoId ID do pedido que foi entregue.
     */
    @Transactional
    public void confirmarEntregaPedido(Long pedidoId) {
        Pedido pedido = this.findById(pedidoId);

        if (pedido.getStatusPedido() != StatusPedido.CONFIRMADO) {
            throw new StatusPedidoException(
                    String.format("Entrega não pode ser confirmado pois o status do pedido é diferente de CONFIRMADO" +
                            " O Status atual do Pedido é: %s", pedido.getStatusPedido().getDescricao()));
        }

        pedido.entregar();

        repository.saveAndFlush(pedido);

        this.sendEmail(pedido);
    }

    /**
     * Envia um email para cliente com as
     * atualizações do seu pedido.
     *
     * @param pedido pedido cujo cliente deve ser notificado
     */
    private void sendEmail(Pedido pedido) {

        mailService.send(pedido);
    }

    public Page<Pedido> filter(PedidoFilter pedidoFilter, Pageable pageable) {
        Page<Pedido> pedidos = repository.findAll(PedidoSpecs.applyFilter(pedidoFilter), pageable);

        if (pedidos.isEmpty()) {
            throw new RepositoryEntityNotFoundException("Nenhum registro localizado com os parâmetros informados.");
        }

        return pedidos;
    }

    public String getReciboHtml(Long pedidoId) {
        var pedido = this.findById(pedidoId);

        return mailService.buildHtmlEmailBody(pedido);
    }
}
