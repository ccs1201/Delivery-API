package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.EstadoController;
import br.com.ccs.delivery.api.v1.controller.MunicipioController;
import br.com.ccs.delivery.api.v1.model.representation.input.MunicipioInput;
import br.com.ccs.delivery.api.v1.model.representation.response.MunicipioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.MUNICIPIO)
public class MunicipioMapper extends AbstractMapper<MunicipioResponse, MunicipioInput, Municipio> {

    public MunicipioMapper() {

        super(MunicipioController.class, MunicipioResponse.class);
    }

    public MunicipioResponse toModel(Municipio municipio) {

        //Cria um model contendo o o link _Self
        var municipioResponse = createModelWithId(municipio.getId(), municipio);
        //Usando o createModelWithId é necessário copiar
        copyProperties(municipio, municipioResponse);

//        var municipioResponse = super.toModel(municipio);

//        municipioResponse.add(linkTo(methodOn(
//                        MunicipioController.class).findById(municipio.getId()))
//                        .withSelfRel()
//                )



                municipioResponse.add(linkTo(methodOn(MunicipioController.class).getAll())
                        .withRel("Municipios"))
                .getEstado()
                .add(linkTo(methodOn(EstadoController.class)
                        .findById(municipioResponse.getEstado().getId()))
                        .withSelfRel()
                );

        return municipioResponse;
    }

    @Override
    public CollectionModel<MunicipioResponse> toCollectionModel(Iterable<? extends Municipio> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(MunicipioController.class).withSelfRel());
    }
}
