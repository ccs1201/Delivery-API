package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.PermissaoInput;
import br.com.ccs.delivery.api.model.representation.response.PermissaoResponse;
import br.com.ccs.delivery.core.mapper.PermissaoMapper;
import br.com.ccs.delivery.domain.model.entity.Permissao;
import br.com.ccs.delivery.domain.service.PermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoService service;

    private final PermissaoMapper mapper;

//    @MapperQualifier(MapperQualifierType.PERMISSAO)
//    MapperInterface<PermissaoResponse, PermissaoInput, Permissao> mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<PermissaoResponse> getAll() {
        return mapper.toCollectionModel(service.findAll());
    }

    @GetMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.OK)
    public PermissaoResponse getById(@PathVariable @Positive Long permissaoId) {
        return mapper.toModel(service.findById(permissaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoResponse save(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = mapper.toEntity(permissaoInput);

        return mapper.toModel(service.save(permissao));
    }

    @PutMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.OK)
    public PermissaoResponse update(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = service.findById(permissaoId);

        mapper.updateEntity(permissaoInput, permissao);

        return mapper.toModel(service.update(permissao));
    }

    @DeleteMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long permissaoId) {

        service.remove(permissaoId);
    }
}
