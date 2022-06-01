package br.com.ccs.delivery.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipio municipio = (Municipio) o;
        return Objects.equals(id, municipio.id) && Objects.equals(nome, municipio.nome) && Objects.equals(estado, municipio.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, estado);
    }
}
