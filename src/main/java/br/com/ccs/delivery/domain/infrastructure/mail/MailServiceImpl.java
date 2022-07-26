package br.com.ccs.delivery.domain.infrastructure.mail;

import br.com.ccs.delivery.core.email.EmailProperties;
import br.com.ccs.delivery.domain.infrastructure.exception.EmailSendException;
import br.com.ccs.delivery.domain.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private MailSender mailSender;
    private EmailProperties properties;

    @Override
    public void send(SimpleMailMessage mailMessage) {

        try {
            mailMessage.setFrom(properties.getSender());
            mailSender.send(mailMessage);
        } catch (MailException e) {
            throw new EmailSendException(
                    String.format("Erro ao enviar E-mail: %s", e.getMessage()), e);
        }


    }
}
