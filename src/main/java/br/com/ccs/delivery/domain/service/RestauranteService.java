package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.exception.EntityInUseException;
import br.com.ccs.delivery.domain.exception.EntityPersistException;
import br.com.ccs.delivery.domain.exception.EntityRemoveException;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RestauranteService {

    RestauranteRepository repository;

    public Collection<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(String.format("Restaurante ID: %d não encontrado.", id));
        }
    }

    @Transactional
    public void delete(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(String.format("Restaurante ID: %d não encontrado.", restauranteId));
        } catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format("Não é possível remover o Restaurante ID: %d pois esta em uso", restauranteId));
        }
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {
        try {
            return repository.save(restaurante);
        } catch (IllegalArgumentException e) {
            throw new EntityPersistException(String.format("Erro ao cadastrar Restaurante. Detalhes:\n %s", e.getMessage()));
        } catch (DataIntegrityViolationException e){
            throw new EntityPersistException(String.format("Erro ao cadastrar Restaurante. Detalhes:\n %s", e.getMessage()));
        }
    }

    @Transactional
    public Restaurante update(Long id, Restaurante restaurante) {

        try {
            findById(id);

            restaurante.setId(id);
            return repository.save(restaurante);
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(String.format("Restaurante ID: %d não encontrado.", id));
        }
    }

}
