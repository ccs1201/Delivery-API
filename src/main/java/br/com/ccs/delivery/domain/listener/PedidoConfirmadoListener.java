package br.com.ccs.delivery.domain.listener;

import br.com.ccs.delivery.domain.event.PedidoConfirmadoEvent;
import br.com.ccs.delivery.domain.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoConfirmadoListener {
    private MailService mailService;

    @EventListener
    public void onConfirmPedido(PedidoConfirmadoEvent event) {

        mailService.send(event.getPedido());

    }
}
