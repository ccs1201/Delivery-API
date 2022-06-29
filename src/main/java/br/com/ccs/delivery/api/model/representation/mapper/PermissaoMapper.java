package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.PermissaoInput;
import br.com.ccs.delivery.api.model.representation.response.PermissaoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Permissao;
import org.springframework.stereotype.Component;

@MapperQualifier(MapperQualifierType.PERMISSAO)
@Component
public class PermissaoMapper extends AbstractMapper<PermissaoResponse, PermissaoInput, Permissao>{
}
