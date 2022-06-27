package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.core.validations.annotations.TaxaEntregaValidator;
import br.com.ccs.delivery.core.validations.validationgroups.ValidationGroups;
import br.com.ccs.delivery.domain.model.component.Endereco;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;


@Getter
@Setter
@DynamicUpdate
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ValorZeroIncluiDescricao(fieldTaxa = "taxaEntrega", fieldName = "nome", descricaoObrigatoria = "Frete Grátis")
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
    //@PositiveOrZero (message = "{TaxaEntrega.invalida}")
    @TaxaEntregaValidator
    //@ExemploValidadorDeUmNumeroMultiplo(numero = 3)
    private BigDecimal taxaEntrega;

    @Embedded
    private Endereco endereco;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    @Valid
    @ConvertGroup(to = ValidationGroups.CozinhaId.class)
    private Cozinha cozinha;

    private boolean ativo = true;

    @CreationTimestamp
    @Column(nullable = false,
            columnDefinition = "datetime",
            updatable = false)
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataUltimaAtualizacao;


    @ManyToMany
    @JoinTable(name = "restaurante_tipo_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_pagamento_id"))
    Collection<TipoPagamento> tiposPagamento;


    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    private Collection<Produto> produtos;

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void addProduto(Produto produto){
        produto.setRestaurante(this);
        this.produtos.add(produto);
    }

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
