package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.EstadoController;
import br.com.ccs.delivery.api.v1.model.representation.input.EstadoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Estado;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.ESTADO)
public class EstadoMapper extends AbstractMapper<EstadoResponse, EstadoInput, Estado> {
    public EstadoMapper() {
        super(EstadoController.class, EstadoResponse.class);
    }

    @Override
    public EstadoResponse toModel(Estado estado) {
        return super.toModel(estado)
                .add(linkTo(
                        methodOn(EstadoController.class).findById(estado.getId())).withSelfRel())
                .add(linkTo(EstadoController.class).withRel("estados"));
    }

    @Override
    public CollectionModel<EstadoResponse> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(EstadoController.class).withSelfRel());
    }
}
