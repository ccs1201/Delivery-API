package br.com.ccs.delivery.domain.repository.impl;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.repository.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public FotoProduto saveFotoProduto(FotoProduto fotoProduto) {
        return entityManager.merge(fotoProduto);
    }

}
