package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.api.model.representation.response.jsonview.RestauranteResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CozinhaResponse extends RepresentationModel<CozinhaResponse> {

    private Long id;
    @JsonView(RestauranteResponseView.Resumo.class)
    private String nome;

}
