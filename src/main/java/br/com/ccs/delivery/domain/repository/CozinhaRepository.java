package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    @Query("select distinct c from Cozinha c join fetch c.restaurantes r")
    Collection<Cozinha> findAllEager();

}