package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Pedido;

import javax.mail.internet.MimeMessage;

public interface MailService {

    void send(Pedido pedido);

    MimeMessage buildMimeMailMessage(Pedido pedido);

    default String buildPlainTextEmailBody(Pedido pedido) {
        String body = String.format("Número do Pedido: %s \n"
                        + "Status: %s \n" + "Restaurante: %s \n"
                        + "Data Pedido: %s \n"
                        + "Valor Total: %S",
                pedido.getCodigo(),
                pedido.getStatusPedido().toString(),
                pedido.getRestaurante().getNome(),
                pedido.getDataCriacao(),
                pedido.getValorTotal().toString());

        return body;
    }

    String buildHtmlEmailBody(Pedido pedido);
}
