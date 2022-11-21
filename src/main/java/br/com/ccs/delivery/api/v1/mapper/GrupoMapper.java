package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.GrupoController;
import br.com.ccs.delivery.api.v1.model.representation.input.GrupoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.GrupoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Grupo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.GRUPO)
public class GrupoMapper extends AbstractMapper<GrupoResponse, GrupoInput, Grupo> {
    public GrupoMapper() {
        super(GrupoController.class, GrupoResponse.class);
    }

    @Override
    public GrupoResponse toModel(Grupo grupo) {
        var model = super.toModel(grupo)
                .add(linkTo(
                        methodOn(GrupoController.class)
                                .getById(grupo.getId())).withSelfRel())
                .add(linkTo(GrupoController.class).withRel("grupos"))
                .add(linkTo(methodOn(GrupoController.class)
                        .getPermissoes(grupo.getId())).withRel("permissoes"))
                .add(linkTo(methodOn(GrupoController.class)
                        .addPermissao(grupo.getId(), null)).withRel("associar-permissao"));

        addLinkToRemovePermissao(model);

        return model;

    }

    @Override
    public CollectionModel<GrupoResponse> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(GrupoController.class).withSelfRel());
    }

    private void addLinkToRemovePermissao(GrupoResponse model) {

        model.getPermissoes().forEach((permissao ->
                       permissao.add(linkTo(
                                methodOn(GrupoController.class)
                                        .removePermissao(model.getId(), permissao.getId()))
                                .withRel("desassociar")
                        )
                )
        );
    }
}
