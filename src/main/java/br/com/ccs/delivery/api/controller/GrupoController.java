package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.GrupoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.GrupoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Grupo;
import br.com.ccs.delivery.domain.service.GrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/grupos")
@AllArgsConstructor
public class GrupoController {

    GrupoService service;
    @MapperQualifier(MapperQualifierType.GRUPO)
    MapperInterface<GrupoResponse, GrupoInput, Grupo> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<GrupoResponse> getAll(){
        return mapper.toCollection(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse save(@RequestBody @Valid GrupoInput grupoInput){
        return mapper.toResponseModel(service.save(mapper.toEntity(grupoInput)));
    }

    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoResponse update(@PathVariable Long grupoId , @RequestBody @Valid GrupoInput grupoInput){

        Grupo grupo = service.findById(grupoId);
        mapper.updateEntity(grupoInput, grupo);

        return mapper.toResponseModel(service.update(grupo));
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long grupoId){
        service.deleteById(grupoId);
    }

    @GetMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoResponse getById(@PathVariable Long grupoId){
        return mapper.toResponseModel(service.findById(grupoId));
    }

}
