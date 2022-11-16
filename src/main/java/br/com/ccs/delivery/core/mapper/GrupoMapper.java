package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.GrupoController;
import br.com.ccs.delivery.api.model.representation.input.GrupoInput;
import br.com.ccs.delivery.api.model.representation.response.GrupoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Grupo;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.GRUPO)
public class GrupoMapper extends AbstractMapper<GrupoResponse, GrupoInput, Grupo> {
    public GrupoMapper() {
        super(GrupoController.class, GrupoResponse.class);
    }
}
