package br.com.ccs.delivery.domain.repository.impl;

import br.com.ccs.delivery.domain.model.dto.VendaDiaria;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.entity.StatusPedido;
import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;
import br.com.ccs.delivery.domain.service.VendaQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Repository
@AllArgsConstructor
public class VendaDiariaServiceImpl implements VendaQueryService {

    @PersistenceContext
    EntityManager entityManager;

    public Collection<VendaDiaria> findVendasDiarias(VendaDiariaFilter vendaDiariaFilter, String timeOffset) {


        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        //função do MySQL para conversão de offSetDateTime
        var functionConverteTimeZone = builder.function(
            "convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));

        //Função do MySQL para converter um DateTime para Date
        var functionConvertDateTimeToDate = builder.function(
                "date", Date.class, functionConverteTimeZone);

        var selection = builder.construct(
                VendaDiaria.class,
                functionConvertDateTimeToDate,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        var predicates = new ArrayList<Predicate>();

        predicates.add(
                root.get("statusPedido").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        if (vendaDiariaFilter.getRestauranteId() != null) {
            predicates.add(builder.equal(
                    root.get("restaurante"), vendaDiariaFilter.getRestauranteId()));
        }

        if (vendaDiariaFilter.getDataInicio() != null && vendaDiariaFilter.getDataFim() != null) {
            predicates.add(
                    builder.between(root.get("dataCriacao"),
                            vendaDiariaFilter.getDataInicio(), vendaDiariaFilter.getDataFim()));
        }

        query.select(selection);
        query.groupBy(functionConvertDateTimeToDate);
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}