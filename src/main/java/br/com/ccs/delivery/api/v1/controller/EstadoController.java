package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.input.EstadoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.api.v1.mapper.EstadoMapper;
import br.com.ccs.delivery.domain.model.entity.Estado;
import br.com.ccs.delivery.domain.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService service;
    //    @MapperQualifier(MapperQualifierType.ESTADO)
//    MapperInterface<EstadoResponse, EstadoInput, Estado> mapper;
    private final EstadoMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EstadoResponse> findaAll() {

        return mapper.toCollectionModel(service.findAll());
    }

    @GetMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse findById(@PathVariable int estadoId) {
        return mapper.toModel(service.findById(estadoId));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse save(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = mapper.toEntity(estadoInput);
        return mapper.toModel(service.save(estado));
    }

    @PutMapping("{estadoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoResponse update(@PathVariable int estadoId, @RequestBody @Valid EstadoInput estadoInput) {

        Estado estado = service.findById(estadoId);
        mapper.updateEntity(estadoInput, estado);

        return mapper.toModel(service.update(estado));
    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int estadoId) {
        service.delete(estadoId);

    }
}
