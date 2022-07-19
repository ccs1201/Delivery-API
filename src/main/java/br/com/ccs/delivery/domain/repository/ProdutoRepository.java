package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
    Collection<Produto> findByNomeContaining(String nome);

    Optional<Produto> findByRestauranteIdAndId(Long restauranteId, Long produtoId);

    @Query("select f from FotoProduto f join f.produto p where p.restaurante.id=:restauranteId and f.produto.id = :fotoProdutoId")
    Optional<FotoProduto> findFotoById(long restauranteId, Long fotoProdutoId);
}
