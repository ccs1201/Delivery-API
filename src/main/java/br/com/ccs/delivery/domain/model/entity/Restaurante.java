package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.component.Endereco;
import br.com.ccs.delivery.domain.model.util.validationgroups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;


@Getter
@Setter
@DynamicUpdate
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @Size(max = 60, min = 3)
    @NotBlank
    private String nome;

    @Column(nullable = false)
    @PositiveOrZero
    @NotNull
    private BigDecimal taxaEntrega;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @ManyToOne(optional = false)
    @NotNull
    @Valid
    @ConvertGroup(to = ValidationGroups.CozinhaId.class)
    private Cozinha cozinha;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false,
            columnDefinition = "datetime",
            updatable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataUltimaAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_tipo_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_pagamento_id"))
    Collection<TipoPagamento> tiposPagamento;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    private Collection<Produto> produtos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(taxaEntrega, that.taxaEntrega) && Objects.equals(endereco, that.endereco) && Objects.equals(cozinha, that.cozinha) && Objects.equals(dataCadastro, that.dataCadastro) && Objects.equals(dataUltimaAtualizacao, that.dataUltimaAtualizacao) && Objects.equals(tiposPagamento, that.tiposPagamento) && Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, taxaEntrega, endereco, cozinha, dataCadastro, dataUltimaAtualizacao, tiposPagamento, produtos);
    }
}
