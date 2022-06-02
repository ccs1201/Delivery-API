package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.service.exception.EntityIdNotFoundException;
import br.com.ccs.delivery.domain.service.exception.EntityInUseException;
import br.com.ccs.delivery.domain.service.exception.EntityPersistException;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class CozinhaService {

    CozinhaRepository repository;

    public Collection<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long cozinhaId) {
        return repository.findById(cozinhaId).orElseThrow(() -> new EntityNotFoundException(String.format("Cozinha ID: %d não encontrada.", cozinhaId)));
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        try {
            return repository.save(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntityPersistException(String.format("Erro ao cadastrar Cozinha. Detalhes:\n %s", e.getMessage()));
        } catch (IllegalArgumentException e) {
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
            throw new EntityInUseException(String.format("Cozinha ID: %d não pode ser removida pois está em uso.", cozinhaId), e);
        } catch (EmptyResultDataAccessException e){
            throw new EntityIdNotFoundException("Registro não encontrado.");
        }
    }

    public Collection<Cozinha> findByNomeContaining(String nome) {

        return repository.findByNomeContaining(nome);
    }

    public Cozinha getFirst() {

        return repository.getFirstOccurrence().orElseThrow(() -> new EntityNotFoundException("Não foi possível localizar a primeira ocorrência de cozinha."));
    }
}
