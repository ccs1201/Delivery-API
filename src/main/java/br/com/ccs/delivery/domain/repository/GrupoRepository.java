package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    @Query("select g from Grupo g left outer join fetch g.permissoes where g.id= :grupoId")
    Optional<Grupo> findByIdComPermissoes(Long grupoId);

    @Query("select g from Grupo g left outer join fetch g.permissoes p order by p.nome")
    Collection<Grupo> findAllEager();
}
