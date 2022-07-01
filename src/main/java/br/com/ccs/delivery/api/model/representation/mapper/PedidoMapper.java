package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.response.PedidoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.PEDIDO)
public class PedidoMapper extends AbstractMapper<PedidoResponse, PedidoInput, Pedido> {
}
