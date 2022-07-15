package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoResponse {

    private Long id;
    private String nome;
    private String descricao;
}
