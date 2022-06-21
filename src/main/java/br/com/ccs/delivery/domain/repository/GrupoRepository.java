package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
