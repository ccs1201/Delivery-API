package br.com.ccs.delivery.domain.listener;

import br.com.ccs.delivery.domain.event.PedidoCanceladoEvent;
import br.com.ccs.delivery.domain.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class PedidoCanceladoListener {

    private MailService mailService;
    /**
     * <b>Envia um e-mail ao cliente durante um evento
     * de cancelamento de pedido.</b>
     *<p>
     * A anotação <b>{@code @TransactionalEventListener}</b> com <b>phase</b>=
     * <b>{@code TransactionPhase.BEFORE_COMMIT} </b> faz com que o e-mail seja enviado
     * antes do commit no banco de dados e caso seja lançada alguma
     * exceção durante a tentativa de envio do e-mail, será efetuado
     * o rollback da transação no banco de dados.
     *</p>
     * @param event o {@link PedidoCanceladoEvent} que carrega o pedido gerador do evento
     * <p><b>Return</b> void</p>
     * */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onCancelarPedido(PedidoCanceladoEvent event) {
        mailService.send(event.getPedido());
    }
}
