package br.com.ccs.delivery.api.v1.model.representation.response;

import br.com.ccs.delivery.api.v1.model.representation.response.jsonview.RestauranteResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("cozinhas")
@Getter
@Setter
@Schema(name = "Cozinha")
public class CozinhaResponse extends RepresentationModel<CozinhaResponse> {

    private Long id;
    @JsonView(RestauranteResponseView.Resumo.class)
    private String nome;

}
