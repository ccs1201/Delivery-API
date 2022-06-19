package br.com.ccs.delivery.api.model.representation.input.components.ids;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TipoPagamentoIdInput {

    @NotNull
    private Long id;
}
