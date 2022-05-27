package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.exception.EntityPersistException;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.repository.CozinhaRepository;
import br.com.ccs.delivery.domain.exception.EntityInUseException;
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
public class CozinhaService {

    CozinhaRepository repository;

    public Collection<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long cozinhaId) {

        try {
            return repository.findById(cozinhaId).get();

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(String.format("Cozinha ID: %d não encontrada.", cozinhaId));
        }
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        try {
            return repository.save(cozinha);
        } catch (IllegalArgumentException e) {
            throw new EntityPersistException(String.format("Erro ao cadastrar Cozinha. Detalhes:\n %s", e.getMessage()));
        } catch (DataIntegrityViolationException e){
            throw new EntityPersistException(String.format("Erro ao cadastrar Cozinha. Detalhes:\n %s", e.getMessage()));
        }
    }

    @Transactional
    public Cozinha update(Long cozinhaId, Cozinha cozinha) {

        findById(cozinhaId);
        cozinha.setId(cozinhaId);

        return repository.save(cozinha);
    }

    @Transactional
    public void delete(Long cozinhaId) {
        try {
            repository.deleteById(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cozinha ID: %d não pode ser removida pois esta em uso.", cozinhaId));

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cozinha ID: %d não encontrada.", cozinhaId));
        }
    }
}
