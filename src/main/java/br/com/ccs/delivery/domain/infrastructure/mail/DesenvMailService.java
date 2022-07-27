package br.com.ccs.delivery.domain.infrastructure.mail;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DesenvMailService extends SmtpMailServiceImpl {
    @Override
    public void send(Pedido pedido) {
        var message = super.buildHtmlEmailBody(pedido);

        log.info("Enviando email do pedido", message);
    }

}
