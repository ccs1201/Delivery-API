package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteResponse {

    private Long id;
    private String nome;
    private BigDecimal taxaEntrega;
    private CozinhaResponse cozinha;
}
