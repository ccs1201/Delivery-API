package br.com.ccs.delivery.api.v1.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Schema(name = "Estado")
public class EstadoResponse extends RepresentationModel<EstadoResponse> {

    private Integer id;

    private String nome;

    private String sigla;
}
