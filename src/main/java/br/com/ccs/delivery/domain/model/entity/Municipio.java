package br.com.ccs.delivery.domain.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
}
