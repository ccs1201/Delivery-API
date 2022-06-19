package br.com.ccs.delivery.api.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TipoPagamentoInput {
    @NotBlank
    private String nome;
}
