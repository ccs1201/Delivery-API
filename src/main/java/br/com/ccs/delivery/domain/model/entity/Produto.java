package br.com.ccs.delivery.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true,length = 50)
    private String nome;

    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private boolean ativo;


    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return ativo == produto.ativo && id.equals(produto.id) && nome.equals(produto.nome) && valor.equals(produto.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, ativo);
    }
}
