package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.component.Endereco;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@DynamicUpdate
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
                    @AttributeOverride(name = "logradouro", column = @Column(name = "logradouro")),
                    @AttributeOverride(name = "numero", column = @Column(name = "numero")),
                    @AttributeOverride(name = "complemento", column = @Column(name = "complemento")),
                    @AttributeOverride(name = "cep", column = @Column(name = "cep"))
            }
    )
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Collection<ItemPedido> itensPedido = new ArrayList<>();

  /*  public BigDecimal getSubTotal() {
//        if (subTotal == null || subTotal.intValue() == 0) {
//            this.calcularSubTotal();
//        }
        return subTotal.setScale(2, RoundingMode.HALF_UP);
    }*/

   /* public BigDecimal getValorTotal() {
//        if (subTotal == null || subTotal.intValue() == 0) {
//            calcularSubTotal();
//        }
//        valorTotal = subTotal.add(taxaEntrega);
        return valorTotal.setScale(2, RoundingMode.HALF_UP);
    }*/

    /**
     * <p>
     * Efetua os cálculos dos valores do pedido.
     * </p>
     * Calculando o subtotal e total (subtotal + taxa Entrega)
     * <p>
     *
     */
    public void calcularPedido() {
        this.taxaEntrega = this.getRestaurante().getTaxaEntrega();
        this.calcularSubTotal();
        this.calcularTotal();
    }

    /**
     * <p>
     * Calcula o subtotal do pedido
     * pegando o Total de cada item
     * do pedido.
     * </p>
     *
     * <ul>
     *      Garante também que o relacionamento
     *      entre  {@link Pedido} {@link ItemPedido}
     *      esteja estabelecido, para que não ocorram
     *      problemas na camada de persistência da JPA
     *      ao cadastrar um pedido.
     * </ul>
     */
    private void calcularSubTotal() {

        itensPedido.forEach(itemPedido -> {
            itemPedido.calcularValorTotal();
            itemPedido.setPedido(this);
            subTotal = this.subTotal.add(itemPedido.getValorTotal());
        });
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * <p>Calcula o valor total do pedido
     * somando valorTotal + taxaEntrega</p>
     */
    private void calcularTotal() {
        valorTotal = subTotal.add(taxaEntrega);
        valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void cancelar(){
        this.dataCancelamento = OffsetDateTime.now();
        this.statusPedido = StatusPedido.CANCELADO;
    }

    public void entregar(){
        this.dataEntrega = OffsetDateTime.now();
        this.statusPedido = StatusPedido.ENTREGUE;
    }

    public void confirmar(){
        this.dataConfirmacao = OffsetDateTime.now();
        this.statusPedido = StatusPedido.CONFIRMADO;
    }
    public void criar(){
        this.statusPedido = StatusPedido.CRIADO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}