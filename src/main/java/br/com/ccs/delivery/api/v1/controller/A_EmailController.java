package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.domain.service.MailService;
import br.com.ccs.delivery.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        mailService.send(pedido);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> getReciboPedido(@PathVariable Long pedidoId) {
        var response = pedidoService.getReciboHtml(pedidoId);
        return ResponseEntity.ok(response);
    }
}
