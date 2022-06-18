package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.MunicipioInput;
import br.com.ccs.delivery.api.model.representation.response.MunicipioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierEnum;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierEnum.MUNICIPIO)
public class MunicipioMapper extends AbstractMapper<MunicipioResponse, MunicipioInput, Municipio> {
}
