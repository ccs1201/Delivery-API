package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.repository.CozinhaRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityPersistException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CozinhaService {

    public static final String ERRO_ATUALIZAR_COZINHA = "Erro ao Atualizar cozinha ID: %d";
    public static final String ERRO_CADASTRAR_COZINHA = "Erro ao cadastrar Cozinha. Detalhes:\n %s";
    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha ID: %d não encontrada";
    public static final String NAO_PODE_REMOVER_COZINHA = "Cozinha ID: %d não pode ser removida pois está em uso.";
    CozinhaRepository repository;

    public Page<Cozinha> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cozinha findById(Long cozinhaId) {
        return repository
                .findById(cozinhaId)
                .orElseThrow(() ->
                        new RepositoryEntityNotFoundException(String.format(COZINHA_NAO_ENCONTRADA + ".", cozinhaId)));
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        try {
            return repository.save(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Cozinha com nome: %s já está cadastrada.", cozinha.getNome()), e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(
                    String.format(ERRO_CADASTRAR_COZINHA, e.getMessage()));
        }
    }

    @Transactional
    public Cozinha update(Long cozinhaId, Cozinha cozinha) {
        try {
            findById(cozinhaId);
            cozinha.setId(cozinhaId);

            return repository.save(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format(ERRO_ATUALIZAR_COZINHA, cozinhaId), e);
        }

    }

    @Transactional
    public void delete(Long cozinhaId) {
        try {
            repository.deleteById(cozinhaId);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(
                    String.format(NAO_PODE_REMOVER_COZINHA, cozinhaId), e.getRootCause());

        } catch (EmptyResultDataAccessException e) {
            throw new RepositoryEntityNotFoundException(
                    String.format(COZINHA_NAO_ENCONTRADA, cozinhaId));
        }
    }

   /* public void delete(Long cozinhaId) {
        try {
            repository.deleteById(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("Cozinha ID: %d não pode ser removida pois está em uso.", cozinhaId));
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Registro não encontrado.");
        }
    }*/

    public Collection<Cozinha> findByNomeContaining(String nome) {

        Collection<Cozinha> cozinhas = repository.findByNomeContaining(nome);

        if (cozinhas.isEmpty()) {
            throw new RepositoryEntityNotFoundException(
                    String.format("Nenhum registro encontrado com para critério de pesquisa: %s", nome));
        }

        return cozinhas;
    }

    public Cozinha getFirst() {

        return repository.getFirstOccurrence()
                .orElseThrow(() ->
                        new RepositoryEntityNotFoundException(COZINHA_NAO_ENCONTRADA));
    }

    public Collection<Cozinha> findAll() {
        return repository.findAll();
    }
}
