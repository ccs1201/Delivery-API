package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.FotoProdutoController;
import br.com.ccs.delivery.api.v1.model.representation.input.FotoProdutoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.FotoProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.FOTOPRODUTO)
public class FotoProdutoMapper extends AbstractMapper<FotoProdutoResponse, FotoProdutoInput, FotoProduto> {
    public FotoProdutoMapper() {
        super(FotoProdutoController.class, FotoProdutoResponse.class);
    }
}
