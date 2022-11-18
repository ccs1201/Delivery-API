package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.PedidoController;
import br.com.ccs.delivery.api.controller.ProdutoController;
import br.com.ccs.delivery.api.controller.RestauranteController;
import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.response.PedidoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.specification.filter.PedidoFilter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.PEDIDO)
public class PedidoMapper extends AbstractMapper<PedidoResponse, PedidoInput, Pedido> {

    private final EnderecoMapper enderecoMapper;

    public PedidoMapper(EnderecoMapper enderecoMapper) {

        super(PedidoController.class, PedidoResponse.class);
        this.enderecoMapper = enderecoMapper;
    }

    public Pedido toEntity(PedidoFilter inputmodel) {
        return mapper.map(inputmodel, Pedido.class);
    }

    @Override
    public PedidoResponse toModel(Pedido pedido) {
        var pedidoResponse = super.toModel(pedido)
                .add(linkTo(methodOn(PedidoController.class)
                        .getByCodigo(pedido.getCodigo()))
                        .withSelfRel())
                .add(linkTo(PedidoController.class).withRel("pedidos"));

        //Adiciona os links HATEOAS do Restaurante
        addLinksToRestaurante(pedido, pedidoResponse);

        //Adiciona os links HATEOAS dos Produtos do pedido
        addLinksToProdutos(pedidoResponse);

        //Adiciona os links HATEOAS do Endereco de entrega
        enderecoMapper.toModel(pedidoResponse.getEnderecoEntrega());

        return pedidoResponse;
    }

    private void addLinksToRestaurante(Pedido pedido, PedidoResponse pedidoResponse) {
        pedidoResponse.getRestaurante()
                .add(linkTo(
                        methodOn(RestauranteController.class)
                                .findById(pedido.getRestaurante().getId()))
                        .withSelfRel());
    }

    private void addLinksToProdutos(PedidoResponse pedidoResponse) {

        pedidoResponse.getItensPedido().forEach(item -> item.getProduto()
                .add(linkTo(
                        methodOn(ProdutoController.class)
                                .getById(item.getProduto().getId())).withSelfRel())
                .add(linkTo(ProdutoController.class).withRel("produtos"))
        );
    }

    @Override
    public CollectionModel<PedidoResponse> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(PedidoMapper.class).withSelfRel());
    }
}
