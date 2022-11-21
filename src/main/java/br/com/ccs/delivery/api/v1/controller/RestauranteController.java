package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.v1.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.api.v1.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.api.v1.model.representation.response.jsonview.RestauranteResponseView;
import br.com.ccs.delivery.api.v1.mapper.RestauranteMapper;
import br.com.ccs.delivery.api.v1.mapper.TipoPagamentoMapper;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.model.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:8081", maxAge = 10)
@RestController
@RequestMapping(path = "/api/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;
    private final TipoPagamentoMapper tipoPagamentoMapper;
    private final RestauranteMapper restauranteMapper;

//    private final ProdutoService produtoService;
//    private final GenericEntityUpdateMergerUtil mergerUtil;
//    @MapperQualifier(MapperQualifierType.RESTAURANTE)
//    MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> restauranteMapper;
//    private final ProdutoMapper produtoMapper;
//
//    private final UsuarioMapper mapperUsuario;

    @GetMapping(value = "/projecao")
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getProjecao(@RequestParam(required = false) String projecao) {
        Collection<RestauranteResponse> restauranteResponses = restauranteMapper.toCollection(service.findAll());

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(restauranteResponses);

        if (projecao == null) {
            return mappingJacksonValue;
        } else if (projecao.equals("nome")) {
            mappingJacksonValue.setSerializationView(RestauranteResponseView.Nome.class);
        } else if (projecao.equals("resumo")) {
            mappingJacksonValue.setSerializationView(RestauranteResponseView.Resumo.class);
        }
        return mappingJacksonValue;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // @JsonView(RestauranteResponseView.Resumo.class) //não funciona com page
    public PagedModel<RestauranteResponse> getAll(@PageableDefault(size = 5) Pageable pageable) {

        return restauranteMapper.toPagedModel(service.findAll(pageable));

        //Habilita o CORS somente para este método
//        return ResponseEntity.ok()
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8081")
//                .body(pageResponse);
    }

    @GetMapping(params = "projecao=nome")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(RestauranteResponseView.Nome.class)
    public Collection<RestauranteResponse> getNome() {

        return restauranteMapper.toCollection(service.findAll());
    }

    @GetMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse findById(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);

        return restauranteMapper.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse save(@RequestBody @Valid RestauranteInput restauranteInput) {

        return restauranteMapper.toModel(service.save(restauranteMapper.toEntity(restauranteInput)));
    }

    @PutMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse update(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {

        Restaurante restaurante = service.findById(restauranteId);

        restauranteMapper.updateEntity(restauranteInput, restaurante);

        return restauranteMapper.toModel(service.update(restaurante));
    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }

    @PatchMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse patchUpdate(@PathVariable Long restauranteId, @RequestBody Map<String, Object> updates) {

        return restauranteMapper.toModel(service.patchUpdate(restauranteId, updates));
    }

    @GetMapping("/nome-cozinha")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> findByNomeCozinha(@RequestParam String nomeCozinha) {
        return restauranteMapper.toCollection(service.findByNomeCozinha(nomeCozinha));
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> anyCriteria(String nomeRestaurante, BigDecimal taxaEntregaMin,
                                                       BigDecimal taxaEntregaMax, String nomeCozinha) {

        return restauranteMapper.toCollection(service.anyCriteria(nomeRestaurante, taxaEntregaMin, taxaEntregaMax, nomeCozinha));

    }

    @GetMapping("/com-frete-gratis")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> comFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteNomeLikeSpec(nome);

        return restauranteMapper.toCollection(service.findAll(comFreteGratis, comNomeSemelhante));
    }

    @GetMapping("/specs")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> getComSpecs(String nomeRestaurante, String nomeCozinha) {
        return restauranteMapper.toCollection(service.findAll(nomeRestaurante, nomeCozinha));
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse getFirst() {
        return restauranteMapper.toModel(service.getFirst());
    }

    @PatchMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {

        service.ativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@RequestBody List<Long> restauranteIds) {

        service.ativar(restauranteIds);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody List<Long> restauranteIds) {

        service.inativar(restauranteIds);
    }

    @DeleteMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {

        service.inativar(restauranteId);
    }

    @GetMapping("/{restauranteId}/tipos-pagamento")
    @ResponseStatus(HttpStatus.OK)
    public Collection<TipoPagamentoResponse> getTiposPagamentoRestaurante(@PathVariable Long restauranteId) {

        Restaurante restaurante = service.findComTiposPagamento(restauranteId);

        return tipoPagamentoMapper.toCollection(restaurante.getTiposPagamento());
    }

    @DeleteMapping("{restauranteId}/tipos-pagamento/{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoPagamento(@PathVariable Long restauranteId, @PathVariable Long tipoPagamentoId) {

        Restaurante restaurante = service.findById(restauranteId);

        service.deleteTipoPagamento(restaurante, tipoPagamentoId);
    }

    @PutMapping("{restauranteId}/tipos-pagamento/{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<TipoPagamentoResponse> addTipoPagamento(@PathVariable Long restauranteId, @PathVariable Long tipoPagamentoId) {

        Restaurante restaurante = service.findById(restauranteId);

        restaurante = service.addTipoPagamento(restaurante, tipoPagamentoId);

        return tipoPagamentoMapper.toCollection(restaurante.getTiposPagamento());
    }

    @PatchMapping("{restauranteId}/aberto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable @Positive Long restauranteId) {

        service.abrir(restauranteId);
    }

    @PatchMapping("{restaruanteId}/fechado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fecharRestaurante(@PathVariable @Positive Long restaruanteId) {

        service.fechar(restaruanteId);
    }
}
