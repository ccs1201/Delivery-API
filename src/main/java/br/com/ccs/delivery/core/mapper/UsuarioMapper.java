package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioUpdateInput;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.USUARIO)
public class UsuarioMapper extends AbstractMapper<UsuarioResponse, UsuarioInput, Usuario> {

    public void updateUsuarioFromUsuarioUpdateInput(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {
        getMapper().map(usuarioUpdateInput, usuario);
    }
}
