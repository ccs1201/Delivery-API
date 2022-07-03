package br.com.ccs.delivery.api.model.representation.input;

import br.com.ccs.delivery.api.model.representation.input.components.EnderecoInput;
import br.com.ccs.delivery.api.model.representation.input.components.ids.RestauranteIdInput;
import br.com.ccs.delivery.api.model.representation.input.components.ids.TipoPagamentoIdInput;
import br.com.ccs.delivery.api.model.representation.input.components.ids.UsuarioIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PedidoInput {
    @Valid
    @NotNull
    private RestauranteIdInput restaurante;
    @Valid
    @NotNull
    private UsuarioIdInput cliente;
    @Valid
    @NotNull
    private TipoPagamentoIdInput tipoPagamento;
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;
    @Valid
    @Size(min = 1)
    @NotNull
    private Collection<ItemPedidoInput> itensPedido = new ArrayList<>();
}
