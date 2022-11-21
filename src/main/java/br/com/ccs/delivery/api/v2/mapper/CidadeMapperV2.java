package br.com.ccs.delivery.api.v2.mapper;

import br.com.ccs.delivery.api.v1.controller.MunicipioController;
import br.com.ccs.delivery.api.v1.mapper.AbstractMapper;
import br.com.ccs.delivery.api.v2.controller.CidadeControllerV2;
import br.com.ccs.delivery.api.v2.model.input.CidadeInputV2;
import br.com.ccs.delivery.api.v2.model.response.CidadeResponseV2;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.MUNICIPIO)
public class CidadeMapperV2 extends AbstractMapper<CidadeResponseV2, CidadeInputV2, Municipio> {

    public CidadeMapperV2() {

        super(MunicipioController.class, CidadeResponseV2.class);
    }

    public CidadeResponseV2 toModel(Municipio municipio) {

        return super.toModel(municipio)
                .add(linkTo(methodOn(CidadeControllerV2.class)
                        .findById(municipio.getId())).withSelfRel())
                .add(linkTo(methodOn(CidadeControllerV2.class).getAll())
                        .withRel("Municipios"));


    }

    @Override
    public CollectionModel<CidadeResponseV2> toCollectionModel(Iterable<? extends Municipio> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CidadeControllerV2.class).withSelfRel());
    }
}
