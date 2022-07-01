package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.component.Endereco;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    private BigDecimal subTotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    @ManyToOne
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

    @OneToMany(mappedBy = "pedido", orphanRemoval = true)
    private Collection<ItemPedido> itensPedido = new ArrayList<>();

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
