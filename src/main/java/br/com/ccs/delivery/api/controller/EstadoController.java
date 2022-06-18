package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.EstadoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierEnum;
import br.com.ccs.delivery.domain.model.entity.Estado;
import br.com.ccs.delivery.domain.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/estados")
@AllArgsConstructor
public class EstadoController {
    EstadoService service;
    @MapperQualifier(MapperQualifierEnum.ESTADO)
    MapperInterface<EstadoResponse, EstadoInput, Estado> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EstadoResponse> findaAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse findById(@PathVariable int estadoId) {
        return mapper.toResponseModel(service.findById(estadoId));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse save(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = mapper.toEntity(estadoInput);
        return mapper.toResponseModel(service.save(estado));
    }

    @PutMapping("{estadoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoResponse update(@PathVariable int estadoId, @RequestBody @Valid EstadoInput estadoInput) {

        Estado estado = service.findById(estadoId);
        mapper.updateEntity(estadoInput, estado);

        return mapper.toResponseModel(service.update(estado));
    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int estadoId) {
        service.delete(estadoId);

    }
}
