package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u left outer join fetch u.grupos g where u.id= :usuarioId")
    Optional<Usuario> findByIdEager(Long usuarioId);

    @Query("select u from Usuario u left outer join fetch u.grupos g")
    List<Usuario> findAll();

    @Query("select u from Usuario u join fetch u.grupos g join fetch g.permissoes p where u.id= :usuarioId order by g.nome ASC")
    Optional<Usuario> findSeTiverGrupos(Long usuarioId);

}