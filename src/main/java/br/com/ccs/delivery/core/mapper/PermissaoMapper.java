package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.PermissaoController;
import br.com.ccs.delivery.api.model.representation.input.PermissaoInput;
import br.com.ccs.delivery.api.model.representation.response.PermissaoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Permissao;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@MapperQualifier(MapperQualifierType.PERMISSAO)
@Component
public class PermissaoMapper extends AbstractMapper<PermissaoResponse, PermissaoInput, Permissao> {

    public PermissaoMapper() {
        super(PermissaoController.class, PermissaoResponse.class);
    }

    @Override
    public PermissaoResponse toModel(Permissao permissao) {
        return super.toModel(permissao)
                .add(linkTo(
                        methodOn(PermissaoController.class)
                                .getById(permissao.getId())).withSelfRel())
                .add(linkTo(PermissaoController.class).withRel("permissoes"));
    }

    @Override
    public CollectionModel<PermissaoResponse> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(PermissaoController.class).withSelfRel());
    }
}
