package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PermissaoResponse extends RepresentationModel<PermissaoResponse> {

    private Long id;
    private String nome;
    private String descricao;
}
