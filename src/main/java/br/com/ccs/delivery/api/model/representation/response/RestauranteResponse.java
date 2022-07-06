package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.api.model.representation.response.view.RestauranteResponseView;
import br.com.ccs.delivery.domain.model.component.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteResponse {

    @JsonView({RestauranteResponseView.Resumo.class, RestauranteResponseView.SomenteNome.class})
    private Long id;

    @JsonView({RestauranteResponseView.Resumo.class, RestauranteResponseView.SomenteNome.class})
    private String nome;

    @JsonView(RestauranteResponseView.Resumo.class)
    private BigDecimal taxaEntrega;

    private boolean ativo;
    private boolean aberto;

    @JsonIgnoreProperties("id")
    @JsonView(RestauranteResponseView.Resumo.class)
    private CozinhaResponse cozinha;

    @JsonIgnoreProperties("municipio.id")
    private Endereco endereco;


}
