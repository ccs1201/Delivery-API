package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.util.GenericEntityUpdateMergerUtil;
import br.com.ccs.delivery.domain.repository.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.repository.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    RestauranteService service;
    GenericEntityUpdateMergerUtil mergerUtil;
    @MapperQualifier(MapperQualifierType.RESTAURANTE)
    MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> findAll() {
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

    private void merge(Map<String, Object> updates, Restaurante restaurante) {

        mergerUtil.updateModel(updates, restaurante, Restaurante.class);

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

}
