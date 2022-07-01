package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.domain.model.component.Endereco;
import br.com.ccs.delivery.domain.model.entity.ItemPedido;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PedidoResponse {

    private Long id;
    private ClienteResponse cliente;
    private BigDecimal subTotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private String statusPedido;
    private EnderecoResponse enderecoEntrega;
    private Collection<ItemPedidoResponse> itensPedido;

}
