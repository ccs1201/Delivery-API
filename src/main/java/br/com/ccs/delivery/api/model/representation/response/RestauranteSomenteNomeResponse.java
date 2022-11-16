package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class RestauranteSomenteNomeResponse extends RepresentationModel<RestauranteSomenteNomeResponse> {
    private String nome;
}
