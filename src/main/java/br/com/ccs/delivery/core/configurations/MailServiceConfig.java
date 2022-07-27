package br.com.ccs.delivery.core.configurations;

import br.com.ccs.delivery.core.email.EmailProperties;
import br.com.ccs.delivery.domain.infrastructure.mail.DesenvMailService;
import br.com.ccs.delivery.domain.infrastructure.mail.SmtpMailServiceImpl;
import br.com.ccs.delivery.domain.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MailServiceConfig {

    private EmailProperties properties;

    @Bean
    public MailService mailService(){
        if (properties.getImpl().equals(EmailProperties.Implementacao.PRODUCAO)) {
            return new SmtpMailServiceImpl();
        } else {
            return new DesenvMailService();
        }
    }
}
