package br.com.ccs.delivery.api.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInput {

    @NotBlank
    private String nome;
    @PositiveOrZero
    @NotNull
    private BigDecimal taxaEntrega;
    @NotBlank
    @Positive
    private Long cozinhaId;
}
