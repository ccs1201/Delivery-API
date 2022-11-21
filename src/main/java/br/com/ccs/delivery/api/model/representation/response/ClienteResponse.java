package br.com.ccs.delivery.api.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Schema(name = "Cliente")
public class ClienteResponse extends RepresentationModel<ClienteResponse> {

    private String nome;

    private String email;
}
