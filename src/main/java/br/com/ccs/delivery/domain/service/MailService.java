package br.com.ccs.delivery.domain.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;

public interface MailService {

    void send(SimpleMailMessage mailMessage);
    void send(MimeMailMessage mailMessage);

}
