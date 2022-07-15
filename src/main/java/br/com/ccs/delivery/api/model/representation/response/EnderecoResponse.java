package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponse {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @JsonIgnoreProperties("id")
    private MunicipioResponse municipio;
}
