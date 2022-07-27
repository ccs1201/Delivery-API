package br.com.ccs.delivery.domain.infrastructure.mail;

import br.com.ccs.delivery.core.email.EmailProperties;
import br.com.ccs.delivery.domain.infrastructure.exception.EmailSendException;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


public class SmtpMailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailProperties properties;
    @Autowired
    private Configuration freeMakerConfiguration;

    @Override
    public void send(Pedido pedido) {
        try {
            var message = this.buildMimeMailMessage(pedido);

            mailSender.send(message);
        } catch (MailException e) {
            throw new EmailSendException(
                    String.format("Erro ao enviar E-mail: %s", e.getMessage()), e);
        }
    }

    public MimeMessage buildMimeMailMessage(Pedido pedido) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(properties.getSender());
            helper.setTo(pedido.getCliente().getEmail());
            helper.setSubject("Seu pedido em: " + pedido.getRestaurante().getNome() + " foi " + pedido.getStatusPedido());
            helper.setText(buildHtmlEmailBody(pedido));

            return mimeMessage;
        } catch (MessagingException e) {
            throw new EmailSendException("Falha ao criar mensagem, " + e.getMessage(), e);
        }
    }

    @Override
    public String buildHtmlEmailBody(Pedido pedido) {

        try {

            Template template = freeMakerConfiguration.getTemplate("email_pedido.html");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, pedido);

        } catch (Exception e) {
            throw new EmailSendException("Falha ao gerar template do E-mail. " + e.getMessage(), e);
        }

    }
}
