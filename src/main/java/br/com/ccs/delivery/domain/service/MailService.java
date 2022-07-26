package br.com.ccs.delivery.domain.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

    void send(SimpleMailMessage mailMessage);
}
