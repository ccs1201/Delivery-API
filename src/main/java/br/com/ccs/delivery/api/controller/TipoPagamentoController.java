package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.service.TipoPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/tipos-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TipoPagamentoController {


    TipoPagamentoService service;
    @MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
    MapperInterface<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<TipoPagamentoResponse> getAll() {
        return mapper.toCollection(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamentoResponse save(@RequestBody @Valid TipoPagamentoInput tipoPagamentoInput) {
        return mapper.toResponseModel(service.save(mapper.toEntity(tipoPagamentoInput)));
    }

    @GetMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public TipoPagamentoResponse getById(@PathVariable Long tipoPagamentoId) {
        return mapper.toResponseModel(service.findById(tipoPagamentoId));
    }

    @PutMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public TipoPagamentoResponse update(@PathVariable Long tipoPagamentoId, @RequestBody @Valid TipoPagamentoInput tipoPagamentoInput) {
        TipoPagamento tipoPagamento = service.findById(tipoPagamentoId);
        mapper.updateEntity(tipoPagamentoInput, tipoPagamento);
        return mapper.toResponseModel(service.update(tipoPagamento));
    }

    @DeleteMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long tipoPagamentoId) {
        service.delete(tipoPagamentoId);
    }

}
