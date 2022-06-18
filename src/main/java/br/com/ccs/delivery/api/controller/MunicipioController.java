package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.MunicipioInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.MunicipioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.domain.service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/municipios")
@AllArgsConstructor
public class MunicipioController {

    MunicipioService service;
    @MapperQualifier(MapperQualifierType.MUNICIPIO)
    MapperInterface<MunicipioResponse, MunicipioInput,Municipio> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<MunicipioResponse> getAll() {
        return mapper.toCollection(service.getAll());
    }

    @GetMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public MunicipioResponse findbyId(@PathVariable int municipioId) {
        return mapper.toResponseModel(service.findById(municipioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MunicipioResponse save(@RequestBody @Valid MunicipioInput municipioInput) {

        return mapper.toResponseModel(service.save(mapper.toEntity(municipioInput)));
    }

    @PutMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public MunicipioResponse update(@PathVariable int municipioId, @RequestBody @Valid MunicipioInput municipioInput) {

        Municipio municipio = service.findById(municipioId);
        mapper.updateEntity(municipioInput, municipio);

        return mapper.toResponseModel(service.update(municipio));
    }

    @DeleteMapping("{municipioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int municipioId) {
        service.delete(municipioId);
    }

}
