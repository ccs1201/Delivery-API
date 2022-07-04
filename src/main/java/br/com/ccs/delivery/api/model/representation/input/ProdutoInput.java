package br.com.ccs.delivery.api.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {
    @NotBlank
    @Column(unique = true, length = 50)
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private BigDecimal valor;

}
