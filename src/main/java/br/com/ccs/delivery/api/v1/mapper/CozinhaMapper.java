package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.CozinhaController;
import br.com.ccs.delivery.api.v1.model.representation.input.CozinhaInput;
import br.com.ccs.delivery.api.v1.model.representation.response.CozinhaResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.COZINHA)
public final class CozinhaMapper extends AbstractMapper<CozinhaResponse, CozinhaInput, Cozinha> {

    public CozinhaMapper() {
        super(CozinhaController.class, CozinhaResponse.class);
    }

    @Override
    public CozinhaResponse toModel(Cozinha cozinha) {
        return super.toModel(cozinha)
                .add(linkTo(
                        methodOn(CozinhaController.class).findById(cozinha.getId())).withSelfRel())
                .add(linkTo(CozinhaController.class).withRel("cozinhas"));
    }

    @Override
    public CollectionModel<CozinhaResponse> toCollectionModel(Iterable<? extends Cozinha> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CozinhaController.class).withSelfRel());
    }
}
