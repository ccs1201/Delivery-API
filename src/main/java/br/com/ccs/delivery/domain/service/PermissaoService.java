package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Permissao;
import br.com.ccs.delivery.domain.repository.PermissaoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
@Service
public class PermissaoService {

    private final PermissaoRepository repository;

    public Collection<Permissao> findAll() {
        return repository.findAll();
    }

    public Permissao findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Permissão ID: %d não encontrada.", id)
                ));
    }

    @Transactional
    public Permissao save(Permissao permissao) {
        try {
            return repository.saveAndFlush(permissao);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Já existe uma permissão de nome: %s cadastrada.", permissao.getNome()), e);
        }
    }

    @Transactional
    public void remove(Long permissaoId) {
        try {
            repository.delete(this.findById(permissaoId));
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(
                    String.format(
                            "Não foi possível remover a Permissão : %d, pois está em uso.", permissaoId), e);
        }
    }

    @Transactional
    public Permissao update(Permissao permissao) {
        try {
            return repository.saveAndFlush(permissao);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Já existe uma permissão de nome: %s cadastrada.", permissao.getNome()), e);
        }
    }
}
