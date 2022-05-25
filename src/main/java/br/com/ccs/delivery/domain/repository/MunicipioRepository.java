package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
}