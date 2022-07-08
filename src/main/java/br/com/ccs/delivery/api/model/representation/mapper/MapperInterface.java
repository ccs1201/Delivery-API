package br.com.ccs.delivery.api.model.representation.mapper;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

public interface MapperInterface<RESPONSEMODEL, INPUTMODEL, ENTITY> {

    RESPONSEMODEL toResponseModel(ENTITY entity);

    ENTITY toEntity(INPUTMODEL inputmodel);

    void updateEntity(INPUTMODEL inputmodel, ENTITY entity);

    default Page<RESPONSEMODEL> toPage(Page<ENTITY> page) {
        return page.map(this::toResponseModel);
    }


    default Collection<RESPONSEMODEL> toCollection(Page<ENTITY> page) {

        return this.toCollection(page.getContent());

//        return page.stream()
//                .toList()
//                .stream()
//                .map(this::toResponseModel)
//                .collect(Collectors.toList());
    }


    default Collection<RESPONSEMODEL> toCollection(Collection<ENTITY> collection) {
        return collection.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
    }
}

