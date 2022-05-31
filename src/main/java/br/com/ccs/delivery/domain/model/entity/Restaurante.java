package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.component.Endereco;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Embedded
    private Endereco endereco;

    @ManyToOne(optional = false)
    @NotNull
    private Cozinha cozinha;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_tipo_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "tipoPgamento_id"))
    Collection<TipoPagamento> tiposPagamento;


}
