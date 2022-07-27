package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.CozinhaInput;
import br.com.ccs.delivery.api.model.representation.response.CozinhaResponse;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.model.wrapper.CozinhaXmlResponse;
import br.com.ccs.delivery.domain.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@AllArgsConstructor
public class CozinhaController {
    CozinhaService service;
    @MapperQualifier(MapperQualifierType.COZINHA)
    MapperInterface<CozinhaResponse, CozinhaInput, Cozinha> cozinhaMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<CozinhaResponse> findAll() {
        return cozinhaMapper.toCollection(service.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CozinhaXmlResponse findAllXml() {
        return new CozinhaXmlResponse(service.findAll());
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CozinhaResponse> findByNome(@RequestParam String nome) {

        return cozinhaMapper.toCollection(service.findByNomeContaining(nome));
    }

    @GetMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse findById(@PathVariable Long cozinhaId) {
        return cozinhaMapper.toResponseModel(service.findById(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse save(@RequestBody @Valid CozinhaInput cozinhaInput) {

        Cozinha cozinha = service.save(cozinhaMapper.toEntity(cozinhaInput));

        return cozinhaMapper.toResponseModel(cozinha);
    }

    @PutMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse update(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = service.update(cozinhaId, cozinhaMapper.toEntity(cozinhaInput));
        return cozinhaMapper.toResponseModel(cozinha);
    }

    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {

        service.delete(cozinhaId);
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse getFirst() {
        return cozinhaMapper.toResponseModel(service.getFirst());
    }

}
