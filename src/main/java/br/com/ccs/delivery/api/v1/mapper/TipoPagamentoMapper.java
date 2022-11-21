package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.TipoPagamentoController;
import br.com.ccs.delivery.api.v1.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
public class TipoPagamentoMapper extends AbstractMapper<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> {

    public TipoPagamentoMapper() {
        super(TipoPagamentoController.class, TipoPagamentoResponse.class);
    }

    @Override
    public TipoPagamentoResponse toModel(TipoPagamento tipoPagamento) {
        return super.toModel(tipoPagamento)
                .add(linkTo(
                        methodOn(TipoPagamentoController.class)
                                .getById(tipoPagamento.getId())).withSelfRel())
                .add(linkTo(TipoPagamentoController.class).withRel("tipos-pagamento"));
    }

    @Override
    public CollectionModel<TipoPagamentoResponse> toCollectionModel(Iterable<? extends TipoPagamento> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(TipoPagamentoController.class).withSelfRel());
    }
}
