package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.PermissaoInput;
import br.com.ccs.delivery.api.model.representation.response.PermissaoResponse;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Permissao;
import br.com.ccs.delivery.domain.service.PermissaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/api/permissoes")
@AllArgsConstructor
public class PermissaoController {

    PermissaoService service;
    @MapperQualifier(MapperQualifierType.PERMISSAO)
    MapperInterface<PermissaoResponse, PermissaoInput, Permissao> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<PermissaoResponse> getAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.OK)
    public PermissaoResponse getById(@PathVariable @Positive Long permissaoId) {
        return mapper.toResponseModel(service.findById(permissaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoResponse save(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = mapper.toEntity(permissaoInput);

        return mapper.toResponseModel(service.save(permissao));
    }

    @PutMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.OK)
    public PermissaoResponse update(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = service.findById(permissaoId);

        mapper.updateEntity(permissaoInput, permissao);

        return mapper.toResponseModel(service.update(permissao));
    }

    @DeleteMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long permissaoId) {
        service.remove(permissaoId);
    }
}
