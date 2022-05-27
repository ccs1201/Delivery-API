package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    RestauranteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Restaurante> findAll() {
        return service.findAll();
    }

    @GetMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante findById(@PathVariable Long restauranteId) {
        return service.findById(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody Restaurante restaurante) {
        return service.save(restaurante);
    }

    @PutMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante update(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        return service.update(restauranteId, restaurante);
    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }

}
