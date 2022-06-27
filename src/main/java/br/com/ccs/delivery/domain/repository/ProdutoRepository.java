package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Collection<Produto> findByNomeContaining(String nome);
}
