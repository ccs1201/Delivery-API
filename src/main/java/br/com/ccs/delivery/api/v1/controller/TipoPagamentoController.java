package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.api.v1.mapper.TipoPagamentoMapper;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.service.TipoPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/api/tipos-pagamento", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class TipoPagamentoController {
    private final TipoPagamentoService service;

    private final TipoPagamentoMapper mapper;

//    @MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
//    MapperInterface<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> mapper;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionModel<TipoPagamentoResponse>> getAll(ServletWebRequest request) {

        /*
        Desabilita a implementação de ShallowEtags, para usarmos
        DeepEtags somente neste método
         */
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        var lastUpdate = service.getLastUpdate();

        String eTag = "0";

        /*
        Se o valor do lastUpdate não é nulo seta na etag
         */
        if (lastUpdate != null) {
            eTag = String.valueOf(lastUpdate.toEpochSecond());
        }

        /*
        A eTag é enviada através do header da request no parâmetro If-None-Match:"XXXXX"
        qdo a idade do cache do cliente expira

        Se a etag recebida na request é a mesma do lastUpdate
        então não é necessário retornar novos valores ao cliente
        neste caso retornamos um NOT_MODIFIED
         */
        if (request.checkNotModified(eTag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .cacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS)
                            .cachePublic())
                    .eTag(eTag)
                    .build();
        }

        var tiposPagamento = mapper.toCollectionModel(service.findAll());

        return ResponseEntity.ok()
                /*
                 Habilita o cache para esta URI //cachePrivate garante que somente o cliente possa fazer cache
                 e não em cache compartilhado (ex. servidores proxy intermediários)
                 */
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())

                /*
                noCache faz com que todas as requisições sejam validadas com etag no servidor
                e não que não seja feito cache como o nome sugere.
                .cacheControl(CacheControl.noCache())
                */

                /*
                 noStore realmente impede que o cliente faça cache da resposta
                 .cacheControl(CacheControl.noStore())
                */

                //Adiciona o eTag na response
                .eTag(eTag)
                .body(tiposPagamento);
    }

    @GetMapping("/async")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ResponseEntity<Collection<TipoPagamentoResponse>>> getAllAsync() {

        return CompletableFuture.supplyAsync(() ->

                ResponseEntity.ok()
                        // Habilita o cache para esta URI //cachePrivate garante que somente o cliente possa fazer cache
                        // e não em cache compartilhado (ex. servidores proxy intermediários)
                        .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())

                        // noCache faz com que todas as requisições sejam validadas com etag no servidor
                        // e não que não seja feito cache como o nome sugere.
                        //.cacheControl(CacheControl.noCache())

                        // noStore realmente impede que o cliente faça cache da resposta
                        // .cacheControl(CacheControl.noStore())
                        .body(
                                mapper.toCollection(service.findAll())
                        )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamentoResponse save(@RequestBody @Valid TipoPagamentoInput tipoPagamentoInput) {

        return mapper.toModel(service.save(mapper.toEntity(tipoPagamentoInput)));
    }

    @GetMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TipoPagamentoResponse> getById(@PathVariable Long tipoPagamentoId) {
        var tipoPagamento = mapper.toModel(service.findById(tipoPagamentoId));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(tipoPagamento);
    }

    @PutMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public TipoPagamentoResponse update(@PathVariable Long tipoPagamentoId, @RequestBody @Valid TipoPagamentoInput tipoPagamentoInput) {
        TipoPagamento tipoPagamento = service.findById(tipoPagamentoId);
        mapper.updateEntity(tipoPagamentoInput, tipoPagamento);
        return mapper.toModel(service.update(tipoPagamento));
    }

    @DeleteMapping("{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive Long tipoPagamentoId) {
        service.delete(tipoPagamentoId);
    }

}
