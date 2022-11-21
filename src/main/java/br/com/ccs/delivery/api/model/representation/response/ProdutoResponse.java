package br.com.ccs.delivery.api.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation("produtos")
@Getter
@Setter
@Schema(name = "Produto")
public class ProdutoResponse extends RepresentationModel<ProdutoResponse> {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private boolean ativo;
}
