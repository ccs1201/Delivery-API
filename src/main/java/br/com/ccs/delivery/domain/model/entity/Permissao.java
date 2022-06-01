package br.com.ccs.delivery.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(id, permissao.id) && Objects.equals(nome, permissao.nome) && Objects.equals(descricao, permissao.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao);
    }
}