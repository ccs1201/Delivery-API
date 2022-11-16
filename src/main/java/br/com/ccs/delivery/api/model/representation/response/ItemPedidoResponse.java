package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoResponse extends RepresentationModel<ItemPedidoResponse> {

    @JsonIgnoreProperties({"id", "descricao", "ativo", "valor"})
    private ProdutoResponse produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String observacao;
}
