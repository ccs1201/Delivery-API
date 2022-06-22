package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.repository.UsuarioRepository;
import br.com.ccs.delivery.domain.service.exception.AtualizaSenhaException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UsuarioService {

    UsuarioRepository repository;

    public Collection<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findaById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(String.format("Usuário ID: %d, não encontrado.", id)));
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.saveAndFlush(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        return repository.saveAndFlush(usuario);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(String.format("Erro ao remover usuário ID: %d pois está em uso.", id));
        } catch (EmptyResultDataAccessException e) {
            throw new RepositoryEntityNotFoundException(String.format("Usuario ID: %d, não encontrado.", id));
        }
    }

    @Transactional
    public void updateSenha(Long idUsuario, String senhaAtual, String novaSenha) {

        Usuario usuario = this.findaById(idUsuario);

        if (!senhaAtual.contentEquals(usuario.getSenha())) {
            throw new AtualizaSenhaException("Senha atual incorreta verifique.");
        }

        usuario.setSenha(novaSenha);

        repository.saveAndFlush(usuario);
    }
}
