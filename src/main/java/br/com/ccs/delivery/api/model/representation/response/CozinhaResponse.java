package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.api.model.representation.response.view.RestauranteResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaResponse {

    private Long id;
    @JsonView(RestauranteResponseView.Resumo.class)
    private String nome;

}
