package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.component.Endereco;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private BigDecimal valorTotal= BigDecimal.ZERO;
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

    public void calcularSubTotal() {

        itensPedido.forEach(itemPedido -> {
            itemPedido.calcularValorTotal();
            itemPedido.setPedido(this);
            subTotal = this.subTotal.add(itemPedido.getValorTotal());
        });
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSubTotal() {
        if (subTotal == null || subTotal.intValue() == 0) {
            this.calcularSubTotal();
        }
        return subTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void calcularTotal(){
        valorTotal = subTotal.add(taxaEntrega);
    }

    public BigDecimal getValorTotal() {
        if (subTotal == null || subTotal.intValue() == 0) {
            calcularSubTotal();
        }
        valorTotal = subTotal.add(taxaEntrega);
        return valorTotal.setScale(2, RoundingMode.HALF_UP);
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