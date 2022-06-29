package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
