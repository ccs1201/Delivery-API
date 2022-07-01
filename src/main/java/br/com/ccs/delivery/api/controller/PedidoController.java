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
        try {
            return mapper.toResponseModel(service.findById(pedidoId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse add(@RequestBody @Valid PedidoInput pedidoInput) {

        Pedido pedido = service.save(mapper.toEntity(pedidoInput));
        return mapper.toResponseModel(pedido);
    }
}
