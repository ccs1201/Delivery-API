package br.com.ccs.delivery.api.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EstadoInput {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 2, max = 2)
    private String sigla;
}
