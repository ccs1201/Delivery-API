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

    /**
     * <p>
     * Calcula o valor total
     * dos produtos.
     *</p>
     * <ul>
     *     <li>valorUnitario x quantidade</li>
     * </ul>
     */
    public void calcularValorTotal(){
        this.valorTotal = valorUnitario
                .multiply(
                        new BigDecimal(quantidade)
                                .setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * <p>
     * Garante que ao adicionar um
     * produto seu valor seja setado
     * em valorUnitario
     *</p>
     * @param produto {@link Produto} que sera adicionado
     *                               ao {@link ItemPedido}
     */
    public void setProduto(Produto produto){
        this.produto = produto;
        valorUnitario = produto.getValor();
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
