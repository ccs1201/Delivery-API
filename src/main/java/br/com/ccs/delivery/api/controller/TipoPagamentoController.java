package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.mapper.TipoPagamentoMapper;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.domain.service.TipoPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/tipos-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TipoPagamentoController {


    TipoPagamentoService service;
    TipoPagamentoMapper mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<TipoPagamentoResponse> getAll(){
        return mapper.toCollection(service.findAll());
    }

}
