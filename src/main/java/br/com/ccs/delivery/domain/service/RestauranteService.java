package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.exception.EntityInUseException;
import br.com.ccs.delivery.domain.exception.EntityPersistException;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.repository.RestauranteRepository;
import br.com.ccs.delivery.domain.repository.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.repository.specification.RestauranteNomeLikeSpec;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.NoSuchElementException;

import static br.com.ccs.delivery.domain.repository.specification.RestauranteSpecs.*;

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
            throw new EntityNotFoundException(
                    String.format("Restaurante ID: %d não encontrado.", id)
            );
        }
    }

    @Transactional
    public void delete(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(
                    String.format("Restaurante ID: %d não encontrado.", restauranteId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Não é possível remover o Restaurante ID: %d pois esta em uso", restauranteId)
            );
        }
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {
        try {
            return repository.save(restaurante);
        } catch (DataIntegrityViolationException e) {
            throw new EntityPersistException(
                    String.format("Erro ao cadastrar Restaurante. Detalhes:\n %s", e.getMessage())
            );
        } catch (IllegalArgumentException e) {
            throw new EntityPersistException(
                    String.format("Erro ao cadastrar Restaurante. Detalhes:\n %s", e.getMessage())
            );
        }
    }

    @Transactional
    public Restaurante update(Long id, Restaurante restaurante) {
        try {
            restaurante.setId(id);
            return repository.save(restaurante);
        } catch (IllegalArgumentException | DataIntegrityViolationException | EmptyResultDataAccessException e) {
            throw new EntityPersistException(
                    String.format("Erro ao atualizar Restaurante.\nDetalhes:\n %s", e.getMessage())
            );
        }
    }

    @Transactional
    public Restaurante patchUpdate(Restaurante restaurante) {

        try {
            return repository.save(restaurante);
        } catch (IllegalArgumentException | DataIntegrityViolationException | EmptyResultDataAccessException e) {
            throw new EntityPersistException(
                    String.format("Erro ao atualizar Restaurante.\nDetalhes:\n %s", e.getMessage())
            );
        }
    }

    public Collection<Restaurante> findByNomeCozinha(String nomeCozinha) {
        return repository.findByNomeCozinha(nomeCozinha);
    }

    public Collection<Restaurante> anyCriteria(String nome, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha) {
        return repository.anyCriteria(nome, taxaEntregaMin, taxaEntregaMax, nomeCozinha);
    }

    public Collection<Restaurante> findAll(RestauranteComFreteGratisSpec comFreteGratis, RestauranteNomeLikeSpec comNomeSemelhante) {

        return repository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    public Collection<Restaurante> findAll(String nomeRestaurante, String nomeCozinha) {
        return repository.findAll(comFreteGratis()
                .and(nomeLike(nomeRestaurante)
                        .and(cozinhaNomeLike(nomeCozinha))));
    }
}
