package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u join fetch u.grupos g where u.id= :usuarioId order by g.nome ASC")
    Optional<Usuario> findGrupos(Long usuarioId);
}
