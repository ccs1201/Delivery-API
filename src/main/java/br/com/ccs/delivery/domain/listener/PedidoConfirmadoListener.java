package br.com.ccs.delivery.domain.listener;

import br.com.ccs.delivery.domain.event.PedidoConfirmadoEvent;
import br.com.ccs.delivery.domain.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class PedidoConfirmadoListener {
    private MailService mailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    //@EventListener
    public void onConfirmPedido(PedidoConfirmadoEvent event) {

        mailService.send(event.getPedido());

    }
}
