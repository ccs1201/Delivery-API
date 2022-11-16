package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.ProdutoController;
import br.com.ccs.delivery.api.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.PRODUTO)
public class ProdutoMapper extends AbstractMapper<ProdutoResponse, ProdutoInput, Produto> {
    public ProdutoMapper() {
        super(ProdutoController.class, ProdutoResponse.class);
    }
}
