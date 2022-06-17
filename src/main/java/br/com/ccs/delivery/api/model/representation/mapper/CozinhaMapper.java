package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.CozinhaInput;
import br.com.ccs.delivery.api.model.representation.response.CozinhaResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierEnum;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierEnum.COZINHA)
public class CozinhaMapper extends AbstractMapper<CozinhaResponse, CozinhaInput, Cozinha> {

}
