package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.service.EstadoService;
import br.com.ccs.delivery.domain.model.entity.Estado;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
