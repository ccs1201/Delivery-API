package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    @Query("Select r from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    @Override
    @Query("select r from Restaurante r join fetch r.cozinha where r.id= :id")
    Optional<Restaurante> findById(Long id);

    @Query("select r from Restaurante r join fetch r.cozinha c where c.nome like %:nomeCozinha% ")
    Collection<Restaurante> findByNomeCozinha(String nomeCozinha);
}