package br.com.ccs.delivery.api.model.representation.input;

import br.com.ccs.delivery.api.model.representation.input.components.ids.ProdutoIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ItemPedidoInput {
    @Valid
    @NotNull
    private ProdutoIdInput produto;
    @NotNull
    @Positive
    private Integer quantidade;
    private String observacao;
}
