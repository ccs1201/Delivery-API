package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {
    @Query(value = "select max(tp.lastUpdate) from TipoPagamento  tp")
    OffsetDateTime findLastUpdate();
}