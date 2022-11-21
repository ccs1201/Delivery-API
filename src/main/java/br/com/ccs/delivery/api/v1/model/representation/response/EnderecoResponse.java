package br.com.ccs.delivery.api.v1.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("endereco")
@Getter
@Setter
@Schema(name = "Endereco")
public class EnderecoResponse extends RepresentationModel<EnderecoResponse> {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @JsonIgnoreProperties("id")
    private MunicipioResponse municipio;
}
