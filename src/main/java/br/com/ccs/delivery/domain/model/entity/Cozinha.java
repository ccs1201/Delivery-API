package br.com.ccs.delivery.domain.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode
@Entity
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "cozinha")
    private Collection<Restaurante> restaurantes;
}
