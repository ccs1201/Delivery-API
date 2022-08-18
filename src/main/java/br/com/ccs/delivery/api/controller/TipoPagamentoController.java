package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.service.TipoPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

//@CrossOrigin(origins = "http://localhost:8081")
@SuppressWarnings("NonAsciiCharacters")
@RestController
@RequestMapping(value = "/api/tipos-pagamento", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@AllArgsConstructor
public class TipoPagamentoController {


    TipoPagamentoService service;
    @MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
    MapperInterface<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<TipoPagamentoResponse>> getAll() {
        var tiposPagamento = mapper.toCollection(service.findAll());
        return ResponseEntity.ok()
                // Habilita o cache para esta URI //cachePrivate garante que somente o cliente possa fazer cache
                // e não em cache compartilhado (ex. servidores proxy intermediários)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())

                // noCache faz com que todas as requisições sejam validadas com etag no servidor
                // e não que não seja feito cache como o nome sugere.
                //.cacheControl(CacheControl.noCache())

                // noStore realmente impede que o cliente faça cache da resposta
                // .cacheControl(CacheControl.noStore())
                .body(tiposPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamentoResponse save(@RequestBody @Valid TipoPagamentoInput tipoPagamentoInput) {
        return mapper.toResponseModel(service.save(mapper.toEntity(tipoPagamentoInput)));
    }

    @GetMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TipoPagamentoResponse> getById(@PathVariable Long tipoPagamentoId) {
        var tipoPagamento = mapper.toResponseModel(service.findById(tipoPagamentoId));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(tipoPagamento);
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
    public void delete(@PathVariable @Positive Long tipoPagamentoId) {
        service.delete(tipoPagamentoId);
    }

}
