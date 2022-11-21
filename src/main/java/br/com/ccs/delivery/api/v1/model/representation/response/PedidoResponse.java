package br.com.ccs.delivery.api.v1.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
@Relation("pedido")
@Getter
@Setter
@Schema(name = "Pedido")
public class PedidoResponse extends RepresentationModel<PedidoResponse> {

    private String codigo;
    private ClienteResponse cliente;
    //private String clienteNome;
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
