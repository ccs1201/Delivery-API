package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.PedidoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@RestController
@RequestMapping("api/pedidos")
@AllArgsConstructor
public class PedidoController {

    private PedidoService service;
    @MapperQualifier(MapperQualifierType.PEDIDO)
    private MapperInterface<PedidoResponse, PedidoInput, Pedido> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<PedidoResponse> getAll() {
        return mapper.toCollection(service.findAllEager());
    }

    @GetMapping("{pedidoId}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse findById(@PathVariable Long pedidoId) {
        return mapper.toResponseModel(service.findById(pedidoId));
    }
    @GetMapping(params = "pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse getByCodigo(@RequestParam("pedido") @NotBlank String codigoPedido){
        return mapper.toResponseModel(service.findByCodigo(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse add(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = mapper.toEntity(pedidoInput);
        return mapper.toResponseModel(service.cadastrarPedido(pedido));
    }

    @PatchMapping("{pedidoId}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable Long pedidoId) {
        service.cancelarPedido(pedidoId);
    }

    @PatchMapping("{pedidoId}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable Long pedidoId){
        service.confirmarPedido(pedidoId);
    }
    @PatchMapping("{pedidoId}/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarEntregaPedido(@PathVariable Long pedidoId){
        service.confirmarEntregaPedido(pedidoId);
    }
}
