package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.domain.service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/municipios")
@AllArgsConstructor
public class MunicipioController {

    MunicipioService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Municipio> getAll() {
        return service.getAll();
    }

    @GetMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public Municipio findbyId(@PathVariable int municipioId) {
        return service.findById(municipioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio save(@RequestBody Municipio municipio) {
        return service.save(municipio);
    }

    @PutMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public Municipio update(@PathVariable int municipioId, @RequestBody Municipio municipio) {
        return service.update(municipioId, municipio);
    }

    @DeleteMapping("{municipioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int municipioId) {
        service.delete(municipioId);
    }

}
