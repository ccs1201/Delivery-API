package br.com.ccs.delivery.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@JsonRootName("cozinha")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@JsonProperty(value = "Categoria Cozinha")
    @Column(unique = true , nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha", fetch = FetchType.LAZY)
    private Collection<Restaurante> restaurantes;
}
