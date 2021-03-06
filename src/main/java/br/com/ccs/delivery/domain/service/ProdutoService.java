package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.repository.ProdutoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryDataIntegrityViolationException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityInUseException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityUpdateException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ProdutoService {

    ProdutoRepository repository;

    public Collection<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RepositoryEntityNotFoundException(String.format("Produto ID: %d não encontrado", id)));
    }

    public Produto findByRestauranteIdAndProdutoId(Long restauranteId, Long produtoId) {
        return repository.findByRestauranteIdAndId(restauranteId, produtoId).orElseThrow(() -> new RepositoryEntityNotFoundException(String.format("Produto id: %d, não encontrado para o Restaurante id: %d.", produtoId, restauranteId)));
    }

    public Collection<Produto> findByName(String nome) {
        Collection<Produto> produtos = repository.findByNomeContaining(nome);

        if (produtos.isEmpty()) {
            throw new RepositoryEntityNotFoundException(String.format("Nenhum produto com nome: %s encontrado.", nome));
        }
        return produtos;
    }

    @Transactional
    public Produto save(Produto produto) {
        try {
            return repository.saveAndFlush(produto);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(String.format("Produto %s, já cadastrado para o restaurante informado.", produto.getNome()));
        }
    }

    @Transactional
    public void delete(Produto produto) {
        try {
            repository.deleteById(produto.getId());
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(String.format("Produto ID: %d Nome: %s, não pode ser removido pois está em uso.", produto.getId(), produto.getNome()));
        }
    }

    @Transactional
    public Produto update(Produto produto) {
        try {
            return repository.saveAndFlush(produto);
        } catch (Exception e) {
            throw new RepositoryEntityUpdateException("Erro ao Atualizar Produto.", e);
        }
    }

    @Transactional
    public void inativar(Long produtoId) {
        Produto produto = this.findById(produtoId);
        produto.inativar();

        repository.saveAndFlush(produto);
    }

    @Transactional
    public void ativar(Long produtoId) {
        Produto produto = this.findById(produtoId);
        produto.ativar();

        repository.saveAndFlush(produto);
    }
}
