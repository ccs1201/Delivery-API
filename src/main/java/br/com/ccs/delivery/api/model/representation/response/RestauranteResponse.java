package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.domain.model.component.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteResponse {

    private Long id;
    private String nome;
    private BigDecimal taxaEntrega;
    private boolean ativo;
    @JsonIgnoreProperties("id")
    private CozinhaResponse cozinha;
    @JsonIgnoreProperties("municipio.id")
    private Endereco endereco;

}
