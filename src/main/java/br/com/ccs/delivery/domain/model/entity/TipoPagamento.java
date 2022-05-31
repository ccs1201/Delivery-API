package br.com.ccs.delivery.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class TipoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(nullable = false, length = 30)
    private String nome;
    @JsonIgnore
    @ManyToMany(mappedBy = "tiposPagamento")
    private Collection<Restaurante> restaurantes;

}
