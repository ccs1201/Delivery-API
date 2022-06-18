package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.EstadoInput;
import br.com.ccs.delivery.api.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierEnum;
import br.com.ccs.delivery.domain.model.entity.Estado;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierEnum.ESTADO)
public class EstadoMapper extends  AbstractMapper<EstadoResponse, EstadoInput, Estado>{
}
