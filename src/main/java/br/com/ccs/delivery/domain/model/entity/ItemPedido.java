package br.com.ccs.delivery.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@Setter
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal valorUnitario;
    @NotNull
    private BigDecimal valorTotal;
    private String observacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public void calcularValorTotal(){
        this.valorTotal = valorUnitario
                .multiply(
                        new BigDecimal(quantidade)
                                .setScale(2, RoundingMode.HALF_UP));
    }

    public void setProduto(Produto produto){
        this.produto = produto;
        valorUnitario = produto.getValor();
    }

    public BigDecimal getValorTotal(){
        return this.valorTotal.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
