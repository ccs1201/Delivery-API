package br.com.ccs.delivery.api.model.representation.input;

import br.com.ccs.delivery.api.model.representation.input.components.EnderecoInput;
import br.com.ccs.delivery.api.model.representation.input.components.ids.CozinhaIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Valid
    @NotNull
    private CozinhaIdInput cozinha;
    @Valid
    @NotNull
    private EnderecoInput endereco;
}
