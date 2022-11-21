package br.com.ccs.delivery.api.v1.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;
import java.util.Collection;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
@Schema(name = "Usuario")
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {

    private Long id;

    private String nome;

    private String email;

    private OffsetDateTime dataCadastro;

//    @JsonIgnoreProperties(value = "permissoes")
    private Collection<GrupoResponse> grupos;

}
