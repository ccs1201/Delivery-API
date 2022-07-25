package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.model.representation.input.TipoPagamentoInput;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.TIPOPAGAMENTO)
public class TipoPagamentoMapper extends AbstractMapper<TipoPagamentoResponse, TipoPagamentoInput, TipoPagamento> {

}
