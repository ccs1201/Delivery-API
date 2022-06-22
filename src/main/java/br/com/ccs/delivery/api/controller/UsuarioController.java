package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioSenhaInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioUpdateInput;
import br.com.ccs.delivery.api.model.representation.mapper.UsuarioMapper;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    UsuarioService service;
    @MapperQualifier(MapperQualifierType.USUARIO)
    UsuarioMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<UsuarioResponse> getAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse getById(@PathVariable Long usuarioId) {
        return mapper.toResponseModel(service.findaById(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody @Valid UsuarioInput usuarioInput) {
        return mapper.toResponseModel(service.save(mapper.toEntity(usuarioInput)));
    }

    @PutMapping("{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput) {
        service.updateSenha(usuarioId, usuarioSenhaInput.getSenhaAtual(), usuarioSenhaInput.getNovaSenha());
    }

    @PutMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse update(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioUpdateInput usuarioUpdateInput) {

        Usuario usuario = service.findaById(usuarioId);
        mapper.updateUsuarioFromUsuarioUpdateInput(usuarioUpdateInput, usuario);

        return mapper.toResponseModel(service.update(usuario));
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long usuarioId) {
        service.delete(usuarioId);
    }


}
