package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioResponse {

    private Integer id;

    private String nome;
    @JsonIgnoreProperties({"id", "sigla"})
    private EstadoResponse estado;
}
