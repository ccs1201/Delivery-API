package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;

@Getter
@Setter
@JsonFilter("pedidoFilter")
public class PedidoResponse {

    private String codigo;
    private ClienteResponse cliente;
    private RestauranteSomenteNomeResponse restaurante;
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
