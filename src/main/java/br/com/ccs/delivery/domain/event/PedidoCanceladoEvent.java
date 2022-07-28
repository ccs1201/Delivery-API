package br.com.ccs.delivery.domain.event;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;
}
