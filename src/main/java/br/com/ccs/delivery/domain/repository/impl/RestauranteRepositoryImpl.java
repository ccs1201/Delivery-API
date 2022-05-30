package br.com.ccs.delivery.domain.repository.impl;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Collection<Restaurante> anyCriteria(
            String nomeRestaurante, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nomeRestaurante)) {
            predicates.add(builder
                    .like(root.get("nome"), "%" + nomeRestaurante + "%"));
        }

        if (taxaEntregaMin != null) {

            predicates.add(builder
                    .greaterThanOrEqualTo(root.get("taxaEntrega"), taxaEntregaMin));
        }

        if (taxaEntregaMax != null) {
            predicates.add(builder
                    .lessThanOrEqualTo(root.get("taxaEntrega"), taxaEntregaMax));
        }

        if (StringUtils.hasText(nomeCozinha)) {

            predicates.add(builder
                    .like(root.get("cozinha").get("nome"), "%" + nomeCozinha + "%"));
        }

        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }
}
