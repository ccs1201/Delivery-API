package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.model.representation.input.CozinhaInput;
import br.com.ccs.delivery.api.model.representation.response.CozinhaResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.COZINHA)
public class CozinhaMapper extends AbstractMapper<CozinhaResponse, CozinhaInput, Cozinha> {

}
