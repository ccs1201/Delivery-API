package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.repository.TipoPagamentoRepository;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class TipoPagamentoService {

    TipoPagamentoRepository repository;

    public Collection<TipoPagamento> findAll() {
        return repository.findAll();
    }

    public TipoPagamento findById(Long Id) {

        return repository.findById(Id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(String.format("Tipo de Pagamento com ID: %d não encontrado.", Id)));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(
                    String.format("Tipo de Pagamento ID: %d, em uso e não pode ser removido", id), e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityNotFoundException(
                    String.format("Tipo de Pagamento com ID: %d não encontrado.", id));
        }
    }

    @Transactional
    public TipoPagamento save(TipoPagamento tipoPagamento) {
        try {
            return repository.saveAndFlush(tipoPagamento);

        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException("Tipo de pagamento já cadastrado", e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException("Erro ao cadastrar tipo de pagamento", e);
        }
    }

    @Transactional
    public TipoPagamento update(TipoPagamento tipoPagamento) {
        try {
            return repository.saveAndFlush(tipoPagamento);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityUpdateException(
                    String.format("Erro ao atualizar tipo de pagamento ID: %d", tipoPagamento.getId()), e);
        }
    }

    public OffsetDateTime getLastUpdate(){
        return repository.findLastUpdate();
    }
}
