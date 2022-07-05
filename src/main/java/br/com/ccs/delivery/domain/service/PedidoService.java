package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.ItemPedido;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.entity.StatusPedido;
import br.com.ccs.delivery.domain.repository.PedidoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.TipoPagamentoException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
        pedido.setTaxaEntrega(pedido.getRestaurante().getTaxaEntrega());
        pedido.setStatusPedido(StatusPedido.CRIADO);

        pedido.calcularSubTotal();
        pedido.calcularTotal();
        pedido = this.save(pedido);
        return this.findById(pedido.getId());

    }

    private void findMunicipio(Pedido pedido) {
        pedido.getEnderecoEntrega().setMunicipio(
                municipioService
                        .findById(pedido.getEnderecoEntrega().getMunicipio().getId())
        );
    }

    private void getTipoPagamento(Pedido pedido) {
        pedido.setTipoPagamento(
                tipoPagamentoService
                        .findById(pedido.getTipoPagamento().getId())
        );
    }

    private void getProdutosForItensPedido(Collection<ItemPedido> itensPedido) {

        itensPedido.forEach(item -> item
                .setProduto(
                        produtoService
                                .findById(item.getProduto().getId()))
        );
    }

    private void getRestaurante(Pedido pedido) {
        pedido.setRestaurante(
                restauranteService
                        .findComTiposPagamento(pedido.getRestaurante().getId())
        );
    }
}
