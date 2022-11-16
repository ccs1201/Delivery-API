package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.PedidoController;
import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.response.filter.PedidoResponseFilter;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoResponseFilterMapper extends AbstractMapper<PedidoResponseFilter, PedidoInput, Pedido> {
    public PedidoResponseFilterMapper() {
        super(PedidoController.class, PedidoResponseFilter.class);
    }
}
