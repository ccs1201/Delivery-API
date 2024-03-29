package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.RestauranteController;
import br.com.ccs.delivery.api.v1.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.v1.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.RESTAURANTE)
public class RestauranteMapper extends AbstractMapper<RestauranteResponse, RestauranteInput, Restaurante> {

    public RestauranteMapper() {
        super(RestauranteController.class, RestauranteResponse.class);
    }

    @Override
    public RestauranteResponse toModel(Restaurante restaurante) {
        return super.toModel(restaurante)
                .add(linkTo(
                        methodOn(RestauranteController.class)
                                .findById(restaurante.getId())).withSelfRel())
                .add(linkTo(RestauranteController.class).withRel("restaurantes"));
    }

    @Override
    public CollectionModel<RestauranteResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(RestauranteController.class).withSelfRel());
    }

    /*   @Override
    public Restaurante toEntity(RestauranteInput restauranteInput) {

//        Foi preciso criar uma variável local
//        para poder alterar a estratégia de mapping
//        Visto que, se deixarmos com o default
//        ele injeta o ID da cozinha no ID do Restaurante

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PropertyMap<RestauranteInput, Restaurante> propertyMap = new PropertyMap<RestauranteInput, Restaurante>() {
            @Override
            protected void configure() {
                map().getCozinha().setId(source.getCozinhaId());
            }
        };

        modelMapper.addMappings(propertyMap);

        Restaurante restaurante = modelMapper.map(restauranteInput, Restaurante.class);
        return restaurante;
    }*/
}


