package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.api.model.representation.response.jsonview.RestauranteResponseView;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.model.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.model.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.model.util.GenericEntityUpdateMergerUtil;
import br.com.ccs.delivery.domain.service.ProdutoService;
import br.com.ccs.delivery.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    RestauranteService service;
    ProdutoService produtoService;
    GenericEntityUpdateMergerUtil mergerUtil;
    @MapperQualifier(MapperQualifierType.RESTAURANTE)
    MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> mapper;

    @MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
    MapperInterface<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> tipoPagamentoMapper;

    @MapperQualifier(MapperQualifierType.PRODUTO)
    MapperInterface<ProdutoResponse, ProdutoInput, Produto> produtoMapper;

    @MapperQualifier(MapperQualifierType.USUARIO)
    MapperInterface<UsuarioResponse, UsuarioInput, Usuario> mapperUsuario;

    @GetMapping(value = "/projecao")
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getProjecao(@RequestParam(required = false) String projecao) {
        Collection<RestauranteResponse> restauranteResponses = mapper.toCollection(service.findAll());

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
    public ResponseEntity<Page<RestauranteResponse>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Restaurante> restaurantePage = service.findAll(pageable);

        var pageResponse =  mapper.toPage(restaurantePage);

        return ResponseEntity.ok(pageResponse);

        //Habilita o CORS somente para este método
//        return ResponseEntity.ok()
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8081")
//                .body(pageResponse);
    }

    @GetMapping(params = "projecao=nome")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(RestauranteResponseView.Nome.class)
    public Collection<RestauranteResponse> getNome() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse findById(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);

        return mapper.toResponseModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse save(@RequestBody @Valid RestauranteInput restauranteInput) {

        return mapper.toResponseModel(service.save(mapper.toEntity(restauranteInput)));
    }

    @PutMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse update(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {

        Restaurante restaurante = service.findById(restauranteId);

        mapper.updateEntity(restauranteInput, restaurante);

        return mapper.toResponseModel(service.update(restaurante));
    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }

    @PatchMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse patchUpdate(@PathVariable Long restauranteId, @RequestBody Map<String, Object> updates) {

        return mapper.toResponseModel(service.patchUpdate(restauranteId, updates));
    }

    @GetMapping("/nome-cozinha")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> findByNomeCozinha(@RequestParam String nomeCozinha) {
        return mapper.toCollection(service.findByNomeCozinha(nomeCozinha));
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> anyCriteria(String nomeRestaurante, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha) {

        return mapper.toCollection(service.anyCriteria(nomeRestaurante, taxaEntregaMin, taxaEntregaMax, nomeCozinha));

    }

    @GetMapping("/com-frete-gratis")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> comFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteNomeLikeSpec(nome);

        return mapper.toCollection(service.findAll(comFreteGratis, comNomeSemelhante));
    }

    @GetMapping("/specs")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> getComSpecs(String nomeRestaurante, String nomeCozinha) {
        return mapper.toCollection(service.findAll(nomeRestaurante, nomeCozinha));
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse getFirst() {
        return mapper.toResponseModel(service.getFirst());
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
