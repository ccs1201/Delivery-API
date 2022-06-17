package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestauranteMapper implements MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> {
    private ModelMapper mapper;

    @Override
    public RestauranteResponse toResponseModel(Restaurante restaurante) {
        return mapper.map(restaurante, RestauranteResponse.class);
    }

    @Override
    public Restaurante toEntity(RestauranteInput restauranteInput) {
        /*
        Foi precisa criar uma variável local
        para poder configurar a estratégia de mapping
        Visto que, se deixarmos com o default
        ele injeta o ID da cozinha no ID do Restaurante
        */
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

    }
}

//    @Override
//    public Page<RestauranteResponse> toPage(Page<Restaurante> page) {
//        return page.map(this::toResponseModel);
//    }
//
//    @Override
//    public Collection<RestauranteResponse> toCollection(Page<Restaurante> page) {
//        return page.stream()
//                .toList()
//                .stream()
//                .map(this::toResponseModel)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<RestauranteResponse> toCollection(Collection<Restaurante> collection) {
//        return collection.stream()
//                .map(this::toResponseModel)
//                .collect(Collectors.toList());
//    }


