package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.exception.EntityPersistException;
import br.com.ccs.delivery.domain.exception.EntityRemoveException;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.domain.repository.MunicipioRepository;
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
public class MunicipioService {

    MunicipioRepository repository;

    public Collection<Municipio> getAll() {
        return repository.findAllEager();

    }

    public Municipio findById(int municipioId) {
        try {
            Municipio municipio = repository.findById(municipioId).get();
            return municipio;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(String.format("Município ID: %d, não encontrado.", municipioId));
        }
    }

    @Transactional
    public Municipio save(Municipio municipio) {
        try {
            return repository.save(municipio);
        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            throw new EntityPersistException(String.format("Erro ao salvar Município. \nDetalhes:\n %s", e.getMessage()));
        }
    }

    @Transactional
    public Municipio update(int municipioId, Municipio municipio) {
        try {
            findById(municipioId);
            municipio.setId(municipioId);
            return repository.save(municipio);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            throw new EntityPersistException(String.format("Erro ao atualizar Município ID: %d\nDetalhes:\n %s", municipioId, e.getMessage()));
        }
    }

    @Transactional
    public void delete(int municpioId) {
        try {
            repository.deleteById(municpioId);
        } catch (IllegalArgumentException | EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new EntityRemoveException(String.format("Não foi possível remover o Município ID: %d.\nDetalhes:\n%s", municpioId, e.getMessage()));
        }
    }
}
