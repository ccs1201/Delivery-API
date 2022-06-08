package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.model.wrapper.CozinhaXmlResponse;
import br.com.ccs.delivery.domain.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@AllArgsConstructor
@DynamicUpdate
public class CozinhaController {

    CozinhaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<Cozinha> findAll() {
        return service.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CozinhaXmlResponse findAllXml() {
        return new CozinhaXmlResponse(service.findAll());
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Cozinha> findByNome(@RequestParam String nome) {

        return service.findByNomeContaining(nome);
    }

    @GetMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha findById(@PathVariable Long cozinhaId) {
        return service.findById(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody @Valid Cozinha cozinha) {
        return service.save(cozinha);
    }

    @PutMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha update(@PathVariable Long cozinhaId, @RequestBody @Valid Cozinha cozinha) {
        return service.update(cozinhaId, cozinha);
    }

    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {

        service.delete(cozinhaId);
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha getFirst() {
        return service.getFirst();
    }

}
