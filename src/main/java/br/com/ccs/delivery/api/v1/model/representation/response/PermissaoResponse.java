package br.com.ccs.delivery.api.v1.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("permissoes")
@Getter
@Setter
@Schema(name = "Permissao")
public class PermissaoResponse extends RepresentationModel<PermissaoResponse> {

    private Long id;
    private String nome;
    private String descricao;
}
