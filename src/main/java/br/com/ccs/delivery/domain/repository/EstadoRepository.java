package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}