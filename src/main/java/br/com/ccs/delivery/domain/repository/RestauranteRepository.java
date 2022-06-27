package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("Select distinct r from Restaurante r join fetch r.cozinha join fetch r.endereco.municipio m join fetch m.estado")
    List<Restaurante> findAllEager();


    @Query("select r from Restaurante r join fetch r.cozinha join fetch r.tiposPagamento join fetch r.endereco.municipio m join fetch m.estado where r.id= :id")
    Optional<Restaurante> findByIdEager(Long id);

    @Query("select r from Restaurante r join fetch r.cozinha c where c.nome like %:nomeCozinha% ")
    Collection<Restaurante> findByNomeCozinha(String nomeCozinha);

    Restaurante findByNomeContaining(String nome);

    @Query("select distinct r from Restaurante r join fetch r.tiposPagamento tp where r.id = :restauranteId order by tp.nome asc")
    Restaurante findComTiposPagamento(Long restauranteId);

    @Query("select r from Restaurante r join fetch r.tiposPagamento tp where r.id=:restauranteId and tp.id =:tipoPagamentoId")
    Restaurante findByTiposPagamentoIs(Long restauranteId,Long tipoPagamentoId);

    @Modifying
    @Query(value = "delete from restaurante_tipo_pagamento where restaurante_id= :restauranteId and tipo_pagamento_id = :tipoPagamentoId",
            nativeQuery = true)
    void deleteTipoPagamentoByIdFromRestauranteId(Long restauranteId, Long tipoPagamentoId);

    @Query("select r from  Restaurante r join fetch r.produtos where r.id=:id")
    Restaurante findComProdutos(Long id);

}