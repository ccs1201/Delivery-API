package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Restaurante;

import java.math.BigDecimal;
import java.util.Collection;

public interface RestauranteRepositoryQueries {
    Collection<Restaurante> anyCriteria(
            String nomeRestaurante, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha);
}
