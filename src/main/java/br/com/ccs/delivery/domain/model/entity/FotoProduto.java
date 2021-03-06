package br.com.ccs.delivery.domain.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@DynamicUpdate
public class FotoProduto {
    @Id
    @Column(name = "produto_id")
    @EqualsAndHashCode.Include
    private Long produtoId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
