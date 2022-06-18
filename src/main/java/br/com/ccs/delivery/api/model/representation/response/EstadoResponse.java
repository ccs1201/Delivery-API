package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoResponse {

    private Integer id;

    private String nome;

    private String sigla;
}
