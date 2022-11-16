package br.com.ccs.delivery.core.mapper;

import br.com.ccs.delivery.api.controller.RestauranteController;
import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import org.springframework.stereotype.Component;

@Component
@MapperQualifier(MapperQualifierType.RESTAURANTE)
public class RestauranteMapper extends AbstractMapper<RestauranteResponse, RestauranteInput, Restaurante> {

    public RestauranteMapper() {
        super(RestauranteController.class, RestauranteResponse.class);
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


