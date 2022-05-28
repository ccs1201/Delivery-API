package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

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

    @PatchMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante patchUpdate(@PathVariable Long restauranteId, @RequestBody Map<String, Object> updates) {

        Restaurante restaurante = service.findById(restauranteId);
        merge(updates, restaurante);

        return service.patchUpdate(restaurante);
    }

    private void merge(Map<String, Object> updates, Restaurante restaurante) {

        ObjectMapper mapper = new ObjectMapper();

        Restaurante restauranteMapper = mapper.convertValue(updates, Restaurante.class);

        updates.forEach((parametro, valor) -> {

            Field atributoDaClasse = ReflectionUtils.findField(Restaurante.class, parametro);
            atributoDaClasse.setAccessible(true);

            Object valorDoMapper = ReflectionUtils.getField(atributoDaClasse, restauranteMapper);
            ReflectionUtils.setField(atributoDaClasse, restaurante, valorDoMapper);
        });
    }

}
