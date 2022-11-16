package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {

    private Long id;

    private String nome;

    private String email;

    private OffsetDateTime dataCadastro;

}
