package br.com.ccs.delivery.api.model.representation.mapper;

import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestauranteMapper implements MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> {
    ModelMapper mapper;

    @Override
    public RestauranteResponse toResponseModel(Restaurante restaurante) {
        return mapper.map(restaurante, RestauranteResponse.class);
    }

    @Override
    public Restaurante toEntity(RestauranteInput restauranteInput) {
        return mapper.map(restauranteInput, Restaurante.class);
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


