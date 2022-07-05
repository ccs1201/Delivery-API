package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.ItemPedido;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.StatusPedido;
import br.com.ccs.delivery.domain.repository.PedidoRepository;
import br.com.ccs.delivery.domain.service.exception.ProdutoNaoExisteNoCardapioDoRestauranteException;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.TipoPagamentoException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

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
     * <ul>
     *     <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.TipoPagamento}</li>
     *     <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Restaurante}</li>
     *     <li>Verifica se o {@link br.com.ccs.delivery.domain.model.entity.Restaurante}
     *     aceita {@link br.com.ccs.delivery.domain.model.entity.TipoPagamento}
     *     informado no {@link Pedido}</li>
     *     <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Municipio}</li>
     *     <li>Associa o {@link br.com.ccs.delivery.domain.model.entity.Produto}(s)
     *     ao ItemPedido da {@link Collection<ItemPedido>} </li>
     *     <li>Seta a taxa de entrega do pedido pelo valor da TaxaEntrega do {@link br.com.ccs.delivery.domain.model.entity.Restaurante}</li>
     *     <li>Seta o {@link StatusPedido} do {@link Pedido} como CRIADO </li>
     *     <li>Calcula os valores do {@link Pedido}</li>
     *     <li>Salva o {@link Pedido}</li>
     * </ul>
     *
     * @param pedido O pedido a ser cadastrado.
     * @return Pedido — O pedido cadastrado.
     */
    @Transactional
    public Pedido cadastrarPedido(Pedido pedido) {
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

        pedido.setTaxaEntrega(pedido.getRestaurante().getTaxaEntrega());
        pedido.setStatusPedido(StatusPedido.CRIADO);

        pedido.calcularPedido();

        pedido = this.save(pedido);
        return this.findById(pedido.getId());

    }

    private void validarItensPedido(Pedido pedido) {

        HashSet<Produto> produtosInvalidos = new HashSet<>();

        pedido.getRestaurante().setProdutos(
                restauranteService.findComProdutos(
                        pedido.getRestaurante().getId()).getProdutos());

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
     * Busco o município do Endereço de entrega
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
     * pelo ID do {@link br.com.ccs.delivery.domain.model.entity.TipoPagamento}.
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
}
