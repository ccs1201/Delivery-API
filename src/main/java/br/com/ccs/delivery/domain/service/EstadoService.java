package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Estado;
import br.com.ccs.delivery.domain.repository.EstadoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityPersistException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityRemoveException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class EstadoService {

    EstadoRepository repository;


    public Collection<Estado> findAll() {

        return repository.findAll();

    }

    public Estado findById(int estadoId) {

        return repository.findById(estadoId)
                .orElseThrow(() ->
                        new RepositoryEntityNotFoundException(String.format("Estado ID: %d não encontrado.", estadoId)));
    }


    @Transactional
    public Estado save(Estado estado) {
        try {
            return repository.saveAndFlush(estado);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(String.format("Não foi possível salvar o Estado\n Detalhes:\n %s", e.getMessage()));
        }
    }


    @Transactional
    public Estado update(Estado estado) {
        try {
            return repository.saveAndFlush(estado);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(String.format("Erro ao atualizar Estado ID: %d\nDetalhes:\n %s", estado.getId(), e.getMessage()));
        }
    }


    @Transactional
    public void delete(int estadoId) {

        try {
            repository.deleteById(estadoId);
            repository.flush();
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            throw new RepositoryEntityRemoveException(String.format("Não foi possível remover o Estado ID: %d\n Detalhes:\n %s", estadoId, e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(String.format("Não foi possível remover o Estado ID: %d , pois está em uso.", estadoId), e);
        }
    }
}
