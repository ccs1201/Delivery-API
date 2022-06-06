package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityPersistException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityRemoveException;
import br.com.ccs.delivery.domain.model.entity.Estado;
import br.com.ccs.delivery.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EstadoService {

    EstadoRepository repository;


    public Collection<Estado> findAll() {

        return repository.findAll();

    }

    public Estado findById(int estadoId) {
        try {
            return repository.findById(estadoId).get();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(String.format("Estado ID: %d não encontrado.", estadoId));
        }

    }

    @Transactional
    public Estado save(Estado estado) {
        try {
            return repository.save(estado);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(String.format("Não foi possível salvar o Estado\n Detalhes:\n %s", e.getMessage()));
        }
    }

    @Transactional
    public Estado update(int estadoId, Estado estado) {
        try {
            findById(estadoId);
            estado.setId(estadoId);
            return repository.save(estado);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(String.format("Erro ao atualizar Estado ID: %d\nDetalhes:\n %s", estadoId, e.getMessage()));
        }
    }

    @Transactional
    public void delete(int estadoId) {

        try {
            repository.deleteById(estadoId);
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            throw new RepositoryEntityRemoveException(String.format("Não foi possível remover o Estado ID: %d\n Detalhes:\n %s", estadoId, e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(String.format("Não foi possível remover o Estado ID: %d , pois está em uso.", estadoId), e);
        }

    }
}
