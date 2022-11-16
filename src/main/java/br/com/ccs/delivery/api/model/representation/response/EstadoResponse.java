package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class EstadoResponse extends RepresentationModel<EstadoResponse> {

    private Integer id;

    private String nome;

    private String sigla;
}
