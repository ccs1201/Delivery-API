package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.GrupoInput;
import br.com.ccs.delivery.api.model.representation.response.GrupoResponse;
import br.com.ccs.delivery.api.model.representation.response.PermissaoResponse;
import br.com.ccs.delivery.core.mapper.GrupoMapper;
import br.com.ccs.delivery.core.mapper.PermissaoMapper;
import br.com.ccs.delivery.domain.model.entity.Grupo;
import br.com.ccs.delivery.domain.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService service;

    private final GrupoMapper grupoMapper;
    private final PermissaoMapper permissaoMapper;

//    @MapperQualifier(MapperQualifierType.GRUPO)
//    MapperInterface<GrupoResponse, GrupoInput, Grupo> grupoMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<GrupoResponse> getAll() {

        return grupoMapper.toCollection(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse save(@RequestBody @Valid GrupoInput grupoInput) {

        return grupoMapper.toModel(service.save(grupoMapper.toEntity(grupoInput)));
    }

    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoResponse update(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupo = service.findById(grupoId);
        grupoMapper.updateEntity(grupoInput, grupo);

        return grupoMapper.toModel(service.update(grupo));
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long grupoId) {

        service.deleteById(grupoId);
    }

    @GetMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoResponse getById(@PathVariable Long grupoId) {

        return grupoMapper.toModel(service.findById(grupoId));
    }

    @GetMapping("{grupoId}/permissoes")
    @ResponseStatus(HttpStatus.OK)
    public Collection<PermissaoResponse> getPermissoes(@PathVariable Long grupoId) {

        return permissaoMapper.toCollection(service.findComPermissoes(grupoId).getPermissoes());
    }

    @PutMapping("{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {

        service.addPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {

        service.removePermissao(grupoId, permissaoId);
    }
}
