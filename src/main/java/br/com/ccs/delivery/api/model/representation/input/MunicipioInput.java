package br.com.ccs.delivery.api.model.representation.input;

import br.com.ccs.delivery.api.model.representation.input.components.ids.EstadoIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MunicipioInput {
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
