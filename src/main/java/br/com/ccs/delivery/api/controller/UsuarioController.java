package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioSenhaInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioUpdateInput;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapper.UsuarioMapper;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

   private final UsuarioService service;
   private final UsuarioMapper mapper;
//    @MapperQualifier(MapperQualifierType.USUARIO)
//    UsuarioMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UsuarioResponse> getAll() {

        return mapper.toCollectionModel(service.findAll());
    }

    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse getById(@PathVariable Long usuarioId) {
        return mapper.toModel(service.findaById(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody @Valid UsuarioInput usuarioInput) {
        return mapper.toModel(service.save(mapper.toEntity(usuarioInput)));
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

        return mapper.toModel(service.update(usuario));
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long usuarioId) {

        service.delete(usuarioId);
    }

    @GetMapping("{usuarioId}/grupos")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse getGrupos(@PathVariable Long usuarioId){

        return mapper.toModel(service.findGrupos(usuarioId));
    }

    @PutMapping("{usuarioId}/grupos/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){

        service.addGrupo(usuarioId, grupoId);


        return ResponseEntity.ok().build();
    }
}
