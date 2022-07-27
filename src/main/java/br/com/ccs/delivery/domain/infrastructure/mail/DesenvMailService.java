package br.com.ccs.delivery.domain.infrastructure.mail;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

@Slf4j
public class DesenvMailService extends SmtpMailServiceImpl {
    @Override
    public void send(Pedido pedido) {

        if (super.getProperties().isSandBox()) {
            var message = super.buildMimeMailMessage(pedido);

            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

                helper.setTo(super.getProperties().getDestinationSandBox());

                super.getMailSender().send(message);

            } catch (MessagingException e) {
                System.out.println(this.getClass().getName());
                e.printStackTrace();
            }

        } else {
            var message = super.buildHtmlEmailBody(pedido);

            log.info("[FAKE E-MAIL] Para: {}\n{}", pedido.getCliente().getEmail(), message);
        }


    }

}
