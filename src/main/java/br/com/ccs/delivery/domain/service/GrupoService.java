package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Grupo;
import br.com.ccs.delivery.domain.repository.GrupoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityPersistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class GrupoService {

    GrupoRepository repository;

    public Collection<Grupo> findAll() {
        return repository.findAll();
    }

    public Grupo save(Grupo grupo) {
        try {
            return repository.saveAndFlush(grupo);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException("Erro ao cadastrar Grupo.", e);
        }
    }

    public Grupo findById(Long grupoId) {
        return repository.findById(grupoId).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Grupo ID: %d não encontrado.", grupoId)));
    }

    public Grupo update(Grupo grupo) {
        return repository.saveAndFlush(grupo);
    }

    public void deleteById(Long grupoId) {
        repository.deleteById(grupoId);
    }
}
