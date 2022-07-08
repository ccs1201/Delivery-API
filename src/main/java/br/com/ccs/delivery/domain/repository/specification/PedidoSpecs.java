package br.com.ccs.delivery.domain.repository.specification;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.repository.specification.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;


public class PedidoSpecs {

    public static Specification<Pedido> applyFilter(PedidoFilter pedidoFilter) {
        return (root, query, builder) -> {

            query.distinct(true);

            root.fetch("tipoPagamento");

            root.fetch("itensPedido")
                    .fetch("produto");

            root.fetch("enderecoEntrega")
                    .fetch("municipio")
                    .fetch("estado");

            root.fetch("cliente");

            root.fetch("restaurante");


            var predicates = new ArrayList<Predicate>();

            if (pedidoFilter.getClienteId() != null) {
                predicates.add(builder.equal(
                        root.get("cliente"), pedidoFilter.getClienteId()));
            }

            if (pedidoFilter.getRestauranteId() != null) {
                predicates.add(builder.equal(
                        root.get("restaurante"), pedidoFilter.getRestauranteId()));
            }

            if (pedidoFilter.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));

            }

            if (pedidoFilter.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get("dataCriacao"), pedidoFilter.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
