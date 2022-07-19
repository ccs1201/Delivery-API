package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.FotoProdutoInput;
import br.com.ccs.delivery.api.model.representation.response.FotoProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.FOTOPRODUTO)
public class FotoProdutoMapper extends AbstractMapper<FotoProdutoResponse, FotoProdutoInput, FotoProduto> {
}
