package br.com.ccs.delivery.api.controller;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Estado> findaAll() {
        return service.findAll();
    }

    @GetMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public Estado findById(@PathVariable int estadoId) {
        return service.findById(estadoId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Estado save(@RequestBody @Valid Estado estado) {
        return service.save(estado);
    }

    @PutMapping("{estadoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Estado update(@PathVariable int estadoId, @RequestBody @Valid Estado estado) {
        return service.update(estadoId, estado);
    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int estadoId) {
        service.delete(estadoId);

    }
}
