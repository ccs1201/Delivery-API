package br.com.ccs.delivery.domain.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

}