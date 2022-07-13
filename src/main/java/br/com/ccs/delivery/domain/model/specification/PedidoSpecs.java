package br.com.ccs.delivery.domain.model.specification;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.specification.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;


public class PedidoSpecs {

    public static Specification<Pedido> applyFilter(PedidoFilter pedidoFilter) {
        return (root, query, builder) -> {
            /*
             *Se o retorno da query for um pedido
             * faz o fetch, caso contrário não faz
             * o fetch.
             *
             * Isso evita uma {@link org.hibernate.QueryException}
             * quando o hibernate tenta fazer um select count() usando
             * nosso filtro, dado que count não pode conter fetch
             */
            if (Pedido.class.equals(query.getResultType())) {

                root.fetch("cliente");

                root.fetch("tipoPagamento");

                root.fetch("itensPedido").fetch("produto");

                root.fetch("enderecoEntrega")
                        .fetch("municipio")
                        .fetch("estado");

                root.fetch("restaurante");
            }

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
