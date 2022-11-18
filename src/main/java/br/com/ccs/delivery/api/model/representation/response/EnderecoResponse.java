package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("endereco")
@Getter
@Setter
public class EnderecoResponse extends RepresentationModel<EnderecoResponse> {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @JsonIgnoreProperties("id")
    private MunicipioResponse municipio;
}
