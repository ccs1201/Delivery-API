package br.com.ccs.delivery.api.model.representation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<RESPONSEMODEL, INPUTMODEL, ENTITY>  implements MapperInterface<RESPONSEMODEL, INPUTMODEL, ENTITY>{

    @Autowired
    private ModelMapper mapper;
    private final Class<RESPONSEMODEL> responseModelClass;
    private final Class <ENTITY> entityClass;

    public AbstractMapper(Class<RESPONSEMODEL> responseModelClass, Class<ENTITY> entityClass){
        this.responseModelClass = responseModelClass;
        this.entityClass = entityClass;
    }

    public RESPONSEMODEL toResponseModel(ENTITY entity) {

        return mapper.map(entity, responseModelClass);
    }


    public ENTITY toEntity(INPUTMODEL inputmodel) {
        return mapper.map(inputmodel, entityClass);
    }

//    public Page<RESPONSEMODEL> toPage(Page<ENTITY> page) {
//        return page.map(this::toResponseModel);
//    }
//
//
//    public Collection<RESPONSEMODEL> toCollection(Page<ENTITY> page) {
//        return page.stream()
//                .toList()
//                .stream()
//                .map(this::toResponseModel)
//                .collect(Collectors.toList());
//    }
//
//
//    public Collection<RESPONSEMODEL> toCollection(Collection<ENTITY> collection) {
//        return collection.stream()
//                .map(this::toResponseModel)
//                .collect(Collectors.toList());
//    }
}
