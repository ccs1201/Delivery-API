package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Grupo;
import br.com.ccs.delivery.domain.model.entity.Permissao;
import br.com.ccs.delivery.domain.repository.GrupoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityPersistException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class GrupoService {

    GrupoRepository repository;
    PermissaoService permissaoService;

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

    @Transactional
    public Grupo update(Grupo grupo) {
        return repository.saveAndFlush(grupo);
    }

    @Transactional
    public void deleteById(Long grupoId) {
        try {
            repository.deleteById(grupoId);
        } catch (EmptyResultDataAccessException e) {
            throw new RepositoryEntityNotFoundException(
                    String.format("Grupo ID: %d, não encontrado.", grupoId));
        }
    }

    public Grupo findComPermissoes(Long grupoId) {

        this.findById(grupoId);

        return repository.findByIdComPermissoes(grupoId).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Grupo ID: %d, não possui permissões.", grupoId)));
    }

    @Transactional
    public void addPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = this.findById(grupoId);
        Permissao permissao = permissaoService.findById(permissaoId);
        grupo.addPermissao(permissao);

        try {
            repository.saveAndFlush(grupo);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Permissao: %d-%s, já cadastrada para o Grupo: %d-%s.", permissao.getId(), permissao.getNome(), grupo.getId(), grupo.getNome()));
        }
    }

    @Transactional
    public void removePermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = this.findComPermissoes(grupoId);
        Permissao permissao = permissaoService.findById(permissaoId);

        grupo.removerPermissao(permissao);
        repository.saveAndFlush(grupo);
    }
}
