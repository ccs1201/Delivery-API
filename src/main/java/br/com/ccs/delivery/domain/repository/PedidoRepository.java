package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select distinct p from Pedido p join fetch p.tipoPagamento join fetch p.itensPedido itens join fetch p.enderecoEntrega.municipio m join fetch p.cliente " +
            "join fetch itens.produto join fetch m.estado join fetch p.restaurante")
    Collection<Pedido> findAllEager();

    @Query("select distinct p from Pedido p join fetch p.tipoPagamento join fetch p.itensPedido itens join fetch p.enderecoEntrega.municipio m join fetch p.cliente " +
            "join fetch itens.produto join fetch p.restaurante join fetch m.estado where p.id= :id")
    Optional<Pedido> findByIdEager(Long id);
}
