package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.api.v1.mapper.UsuarioMapper;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/usuarios")
@AllArgsConstructor
public class CadastroUsuarioRestauranteController {

    @MapperQualifier(MapperQualifierType.USUARIO)
    private final UsuarioMapper usuarioMapper;
    private final RestauranteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UsuarioResponse> getUsuarios(@PathVariable @Positive Long restauranteId) {
        return usuarioMapper.toCollectionModel(service.findUsuarios(restauranteId).getUsuarios())
                .removeLinks()
                .add(linkTo(methodOn(CadastroUsuarioRestauranteController.class)
                        .getUsuarios(restauranteId)).withSelfRel());
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
