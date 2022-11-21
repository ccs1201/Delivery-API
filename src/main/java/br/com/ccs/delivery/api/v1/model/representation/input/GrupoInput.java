package br.com.ccs.delivery.api.v1.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {
    @NotBlank
    private String nome;
}
