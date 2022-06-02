package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.exceptionhandler.ApiExceptionResponse;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.model.wrapper.CozinhaXmlResponse;
import br.com.ccs.delivery.domain.service.CozinhaService;
import br.com.ccs.delivery.domain.service.exception.BusinessLogicException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@AllArgsConstructor
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
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return service.save(cozinha);
    }

    @PutMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha update(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e) {

        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .dateTime(OffsetDateTime.now())
                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> businessLogicExceptionHandler(BusinessLogicException e) {

        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .dateTime(OffsetDateTime.now())
                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
