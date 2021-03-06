package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("Select distinct r from Restaurante r join fetch r.cozinha join fetch r.endereco.municipio m join fetch m.estado")
    Collection<Restaurante> findAllEager();

    @Query(value = "Select distinct r from Restaurante r join fetch r.cozinha join fetch r.endereco.municipio m join fetch m.estado",
    countQuery = "select count (r) from  Restaurante r")
    Page<Restaurante> findAllEagerPageable(Pageable pageable);

    @Query("Select r.id, r.nome, r.taxaEntrega, r.ativo, r.aberto, r.cozinha.nome, r.cozinha.id, r.endereco.logradouro, r.endereco.bairro, r.endereco.cep, r.endereco.complemento, r.endereco.numero, r.endereco.municipio, m.id, m.nome, e.nome from Restaurante r join r.cozinha c join r.endereco.municipio m join m.estado e")
    Page<Restaurante> findAllEager(Pageable pageable);

    @Query("select distinct r from Restaurante r join fetch r.cozinha join fetch r.endereco.municipio m join fetch m.estado where r.id= :id")
    Optional<Restaurante> findByIdEager(Long id);

    @Query("select r from Restaurante r join fetch r.cozinha c where c.nome like %:nomeCozinha% ")
    Collection<Restaurante> findByNomeCozinha(String nomeCozinha);

    Restaurante findByNomeContaining(String nome);

    @Query("select distinct r from Restaurante r join fetch r.tiposPagamento tp where r.id = :restauranteId order by tp.nome asc")
    Optional<Restaurante> findComTiposPagamento(Long restauranteId);

    @Modifying
    @Query(value = "delete from restaurante_tipo_pagamento where restaurante_id= :restauranteId and tipo_pagamento_id = :tipoPagamentoId",
            nativeQuery = true)
    void deleteTipoPagamentoByIdFromRestauranteId(Long restauranteId, Long tipoPagamentoId);

    @Query("select r from  Restaurante r join fetch r.produtos p where r.id=:id")
    Optional<Restaurante> findProdutos(Long id);

    @Query("select r from  Restaurante r join fetch r.produtos p where r.id=:id and p.ativo=:ativo ")
    Optional<Restaurante> findProdutos(Long id, Boolean ativo);

    @Query("select r from  Restaurante  r join fetch  r.usuarios where r.id =:restauranteId")
    Optional<Restaurante> findUsuarios(Long restauranteId);


}