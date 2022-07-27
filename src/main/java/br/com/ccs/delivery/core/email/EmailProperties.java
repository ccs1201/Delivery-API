package br.com.ccs.delivery.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Classe utilitária para carregar
 * o e-mail do remetente do arquivo
 * application.properties na propriedade
 * $delivery-api.mail.sender e
 * o tipo de implementação do
 * {@link br.com.ccs.delivery.domain.service.MailService}
 * que pode deve DESENVOLVIMENTO ou PRODUCAO
 * na propriedade $delivery-api.mail.impl
 */
@Validated // Necessário para que o spring valide as anotações no startup da aplicação
@ConfigurationProperties("delivery-api.mail")
@Component
@Getter
@Setter
public class EmailProperties {
    @NotNull
    private String sender;
    private Implementacao impl;

    public enum Implementacao {
        DESENVOLVIMENTO, PRODUCAO
    }
}
