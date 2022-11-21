package br.com.ccs.delivery.api.v1.model.representation.input.components.ids;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class GrupoIdInput {
    @NotNull
    @Positive
    private Long id;
}
