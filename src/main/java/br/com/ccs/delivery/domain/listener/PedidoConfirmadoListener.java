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

    /**
     * Envia um e-mail ao cliente durante um evento
     * de confirmação de pedido.
     *<p>
     * A anotação <b>{@code @TransactionalEventListener}</b> com <b>phase</b>
     * <b>{@code TransactionPhase.BEFORE_COMMIT} </b>faz com que o e-mail seja enviado
     * antes do commit no banco de dados e caso seja lançada alguma
     * exceção durante a tentativa de envio do e-mail, será efetuado
     * o rollback da transação no banco de dados.
     *</p>
     * @param event o {@link PedidoConfirmadoEvent} que carrega o pedido gerador do evento
     * <p><b>Return</b> void</p>
     * */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    //@EventListener
    public void onConfirmarPedido(PedidoConfirmadoEvent event) {

        mailService.send(event.getPedido());

    }
}
