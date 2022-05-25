package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}