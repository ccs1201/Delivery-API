package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;

public interface MailService {

    void send(SimpleMailMessage mailMessage);
    void send(MimeMailMessage mailMessage);

    default String buildEmailBody(Pedido pedido) {
        String body = String.format(
                "Número do Pedido: %s \n" +
                        "Status: %s \n" +
                        "Restaurante: %s \n" +
                        "Data Pedido: %s \n" +
                        "Valor Total: %S",
                pedido.getCodigo(),
                pedido.getStatusPedido().toString(),
                pedido.getRestaurante().getNome(),
                pedido.getDataCriacao(),
                pedido.getValorTotal().toString()
        );

        return body;
    }
}
