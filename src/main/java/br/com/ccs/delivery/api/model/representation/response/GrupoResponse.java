package br.com.ccs.delivery.api.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Collection;

@Relation("grupos")
@Getter
@Setter
@Schema(name = "Grupo")
public class GrupoResponse extends RepresentationModel<GrupoResponse> {
    private Long id;
    private String nome;
    private Collection<PermissaoResponse> permissoes;
}
