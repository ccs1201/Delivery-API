package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    @Query("select distinct m from Municipio m join fetch m.estado")
    Collection<Municipio> findAllEager();

    @Query("select m from Municipio m join fetch m.estado where m.id= :id")
    Optional<Municipio> findById(int id);
}