package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ClienteResponse extends RepresentationModel<ClienteResponse> {

    private String nome;

    private String email;
}
