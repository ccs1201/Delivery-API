package br.com.ccs.delivery.api.model.representation.input.components.ids;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProdutoIdInput {
    @Positive
    @NotNull
    private Long id;
}
