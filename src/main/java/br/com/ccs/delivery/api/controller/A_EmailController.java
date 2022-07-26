package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.service.MailService;
import br.com.ccs.delivery.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email/pedidos/{pedidoId}")
@AllArgsConstructor
public class A_EmailController {


    PedidoService pedidoService;
    private MailService mailService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void send(@PathVariable Long pedidoId) {

        var pedido = pedidoService.findById(pedidoId);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("Informações do seu pedido em: " + pedido.getRestaurante().getNome());
        mailMessage.setText(buildEmailBody(pedido));
        mailMessage.setTo(pedido.getCliente().getEmail());

        mailService.send(mailMessage);
    }

    private String buildEmailBody(Pedido pedido) {
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
