package br.com.ccs.delivery.api.model.representation.response;

import br.com.ccs.delivery.api.model.representation.response.jsonview.RestauranteResponseView;
import br.com.ccs.delivery.domain.model.component.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation("restaurantes")
@Getter
@Setter
@Schema(name = "Restaurante")
public class RestauranteResponse extends RepresentationModel<RestauranteResponse> {

    @JsonView({RestauranteResponseView.Resumo.class, RestauranteResponseView.Nome.class})
    private Long id;

    @JsonView({RestauranteResponseView.Resumo.class, RestauranteResponseView.Nome.class})
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
