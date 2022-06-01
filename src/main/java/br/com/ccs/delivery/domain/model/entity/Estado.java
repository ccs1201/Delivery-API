package br.com.ccs.delivery.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 2)
    private String sigla;
    @JsonIgnore
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private Collection<Municipio> municipios;

    public Municipio addMunicipio(Municipio municipio) {

        municipio.setEstado(this);

        municipios.add(municipio);

        return municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id) && Objects.equals(nome, estado.nome) && Objects.equals(sigla, estado.sigla) && Objects.equals(municipios, estado.municipios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sigla, municipios);
    }
}