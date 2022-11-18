package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.UsuarioController;
import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioUpdateInput;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.USUARIO)
public class UsuarioMapper extends AbstractMapper<UsuarioResponse, UsuarioInput, Usuario> {
    public UsuarioMapper() {
        super(UsuarioController.class, UsuarioResponse.class);
    }

    @Override
    public UsuarioResponse toModel(Usuario usuario) {

        var model = createModelWithId(usuario.getId(), usuario);
        copyProperties(usuario, model);

        model.add(linkTo(methodOn(UsuarioController.class).getGrupos(usuario.getId()))
                        .withRel("grupos-usuario"))
                .add(linkTo(UsuarioController.class).withRel("usuarios"));

        return model;
    }

    @Override
    public CollectionModel<UsuarioResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        var models = super.toCollectionModel(entities);

        models.add(linkTo(methodOn(UsuarioController.class).getAll()).withRel("usuarios"));

        return models;
    }

    public void updateUsuarioFromUsuarioUpdateInput(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {
        getMapper().map(usuarioUpdateInput, usuario);
    }
}
