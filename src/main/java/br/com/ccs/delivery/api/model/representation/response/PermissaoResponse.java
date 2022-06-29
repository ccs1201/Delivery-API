package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PermissaoResponse {

    private Long id;
    private String nome;
    private String descricao;
}
