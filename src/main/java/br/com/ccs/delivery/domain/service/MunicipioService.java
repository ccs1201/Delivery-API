package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.domain.repository.MunicipioRepository;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MunicipioService {

    MunicipioRepository repository;

    public Collection<Municipio> getAll() {
        return repository.findAllEager();
    }

    public Municipio findById(int municipioId) {
        try {
            return repository.findById(municipioId).get();
        } catch (NoSuchElementException e) {
            throw new RepositoryEntityNotFoundException(String.format("Município ID: %d, não encontrado.", municipioId));
        }
    }

    public Municipio save(Municipio municipio) {
        try {
            return repository.save(municipio);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Erro ao salvar Município. Estado ID: %d não existe.",
                            municipio.getEstado().getId()), e);
        }
    }

    @Transactional
    public Municipio update(Municipio municipio) {
        try {
            return repository.save(municipio);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Erro ao atualizar Município ID: %d Estado: %d inválido",
                            municipio.getId(), municipio.getEstado().getId()), e);

        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException("Erro ao atualizar Municipio ID: " + municipio.getId());
        }
    }

    @Transactional
    public void delete(int municpioId) {
        try {
            repository.deleteById(municpioId);
        } catch (DataIntegrityViolationException e) {

            throw new RepositoryEntityInUseException(
                    String.format("Não foi possível remover o Município ID: %d pois esta em uso.", municpioId));

        } catch (EmptyResultDataAccessException e) {

            throw new RepositoryEntityNotFoundException(
                    String.format("Municipio ID: %d não existe.", municpioId));

        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityRemoveException(
                    String.format("Não foi possível remover o Município ID: %d", municpioId), e);
        }
    }
}
