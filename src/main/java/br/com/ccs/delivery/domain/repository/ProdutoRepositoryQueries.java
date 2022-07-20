package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto saveFotoProduto(FotoProduto fotoProduto);

    void deleteFotoProduto(FotoProduto foto);
}
