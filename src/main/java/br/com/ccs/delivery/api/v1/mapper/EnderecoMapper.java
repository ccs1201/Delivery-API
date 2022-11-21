package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.EstadoController;
import br.com.ccs.delivery.api.v1.controller.MunicipioController;
import br.com.ccs.delivery.api.v1.model.representation.response.EnderecoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.component.Endereco;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.ENDERECO)
public class EnderecoMapper extends AbstractMapper<EnderecoResponse, Object, Endereco> {

    private final MunicipioMapper municipioMapper;

    private final EstadoMapper estadoMapper;

    public EnderecoMapper(MunicipioMapper municipioMapper, EstadoMapper estadoMapper) {

        super(MunicipioController.class, EnderecoResponse.class);
        this.municipioMapper = municipioMapper;
        this.estadoMapper = estadoMapper;
    }


    public EnderecoResponse toModel(EnderecoResponse enderecoResponse) {

        //links HATEOAS do Município
        enderecoResponse.getMunicipio()
                .add(linkTo(
                        methodOn(MunicipioController.class)
                                .findById(enderecoResponse.getMunicipio().getId())).withSelfRel())
                .add(linkTo(
                        methodOn(MunicipioController.class).getAll()
                ).withRel("municipios"));

        //links HATEOAS do Estado
        enderecoResponse.getMunicipio().getEstado()
                .add(linkTo(
                        methodOn(EstadoController.class)
                                .findById(enderecoResponse.getMunicipio().getEstado().getId())).withSelfRel())
                .add(linkTo(methodOn(EstadoController.class).findaAll()).withRel("Estados"));

        return enderecoResponse;

    }

}
