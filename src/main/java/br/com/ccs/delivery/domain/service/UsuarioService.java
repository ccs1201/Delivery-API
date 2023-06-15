package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Grupo;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.repository.UsuarioRepository;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final String EMAIL_JA_CADASTRADO = "Já existe um usuário com e-mail %s cadastrado.";

    public Collection<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findaById(Long id) {
        return repository.findByIdEager(id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(String.format("Usuário ID: %d, não encontrado.", id)));
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        try {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return repository.saveAndFlush(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new EmailJaCadastradoException(String.format(EMAIL_JA_CADASTRADO, usuario.getEmail()), e);
        }
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        try {
            return repository.saveAndFlush(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new EmailJaCadastradoException(String.format(EMAIL_JA_CADASTRADO, usuario.getEmail()), e);
        }

    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(String.format("Erro ao remover usuário ID: %d pois está em uso.", id));
        } catch (EmptyResultDataAccessException e) {
            throw new RepositoryEntityNotFoundException(String.format("Usuario ID: %d, não encontrado.", id));
        }
    }

    @Transactional
    public void updateSenha(Long idUsuario, String senhaInformada, String novaSenha) {

        Usuario usuario = this.findaById(idUsuario);

        if (validarSenha(usuario.getSenha(), senhaInformada)) {
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            repository.saveAndFlush(usuario);
        } else {
            throw new AtualizaSenhaException("Senha atual incorreta verifique.");
        }
    }

    private boolean validarSenha(String senhaAtual, String senhaInformada) {

        return passwordEncoder.matches(senhaInformada, senhaAtual);
//        return senhaAtual.contentEquals(senhaParaValidar);
    }

    public Usuario findGrupos(Long usuarioId) {

        var usuario = repository.findSeTiverGrupos(usuarioId).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Usuário id %d não possui nenhum grupo cadastrado.", usuarioId))
        );

        return usuario;
    }

    public void addGrupo(Long usuarioId, Long grupoId) {

        Grupo grupo = new Grupo();
        grupo.setId(grupoId);

        try {
            var usuario = repository.findByIdEager(usuarioId).get();
            usuario.getGrupos().add(grupo);
            repository.saveAndFlush(usuario);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Erro ao adicionar Usuário ao grupo.");

        }

    }
}