package br.com.ccs.delivery.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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

}