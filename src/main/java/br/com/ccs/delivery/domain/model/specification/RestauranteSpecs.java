package br.com.ccs.delivery.domain.model.specification;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis() {

        return (root, query, builder) ->
                builder.equal(root.get("taxaEntrega"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> nomeLike(String nome) {

        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Restaurante> cozinhaNomeLike(String nomeCozinha) {
        return (root, query, builder) ->
                builder.like(root.get("cozinha").get("nome"), "%" + nomeCozinha + "%");

    }
}
