package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/usuarios")
@AllArgsConstructor
public class CadastroUsuarioRestauranteController {

    @MapperQualifier(MapperQualifierType.USUARIO)
    private final MapperInterface<UsuarioResponse, UsuarioInput, Usuario> mapperUsuario;
    private final RestauranteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<UsuarioResponse> getUsuarios(@PathVariable @Positive Long restauranteId) {
        return mapperUsuario.toCollection(service.findUsuarios(restauranteId).getUsuarios());
    }

    @PutMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUsuario(@PathVariable @Positive Long restauranteId, @PathVariable @Positive Long usuarioId) {
        service.addUsuario(restauranteId, usuarioId);

    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUsuario(@PathVariable @Positive Long restauranteId, @PathVariable @Positive Long usuarioId) {
        service.removeUsuario(restauranteId, usuarioId);
    }

}
