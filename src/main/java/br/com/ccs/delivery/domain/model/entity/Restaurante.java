package br.com.ccs.delivery.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(min = 3, max = 60)
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal taxaEntrega;

    @ManyToOne(optional = false)
    @NotNull
    private Cozinha cozinha;

    @ManyToMany
            @JoinTable(name = "restaurante_tipo_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "tipoPgamento_id"))
    Collection<TipoPagamento> tiposPagamento;

}
