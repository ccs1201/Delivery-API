package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private boolean ativo;
}
