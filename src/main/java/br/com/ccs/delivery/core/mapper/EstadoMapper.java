package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.EstadoController;
import br.com.ccs.delivery.api.model.representation.input.EstadoInput;
import br.com.ccs.delivery.api.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Estado;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.ESTADO)
public class EstadoMapper extends  AbstractMapper<EstadoResponse, EstadoInput, Estado>{
    public EstadoMapper() {
        super(EstadoController.class, EstadoResponse.class);
    }
}
