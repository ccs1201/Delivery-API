package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.PedidoController;
import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.response.filter.PedidoResponseFilter;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResponseFilterMapper extends AbstractMapper<PedidoResponseFilter, PedidoInput, Pedido> {
    public PedidoResponseFilterMapper() {
        super(PedidoController.class, PedidoResponseFilter.class);
    }

    @Override
    public PedidoResponseFilter toModel(Pedido pedido) {
        return super.toModel(pedido)
                .add(linkTo(
                        methodOn(PedidoController.class)
                                .getByCodigo(pedido.getCodigo())
                ).withSelfRel())
                .add(linkTo(PedidoController.class).withRel("pedidos"));
    }

    @Override
    public CollectionModel<PedidoResponseFilter> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(PedidoController.class)).withSelfRel());
    }
}
