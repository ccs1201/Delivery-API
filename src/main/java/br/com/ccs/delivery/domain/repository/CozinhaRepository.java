package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>, JpaSpecificationExecutor<Cozinha> {

    @Query("select distinct c from Cozinha c join fetch c.restaurantes r")
    Collection<Cozinha> findAllEager();

    Collection<Cozinha> findByNomeContaining(String nome);
}