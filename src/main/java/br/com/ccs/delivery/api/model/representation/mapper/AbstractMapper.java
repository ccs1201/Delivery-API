package br.com.ccs.delivery.api.model.representation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractMapper<RESPONSEMODEL, INPUTMODEL, ENTITY> implements MapperInterface<RESPONSEMODEL, INPUTMODEL, ENTITY> {

    @Autowired
    private ModelMapper mapper;
    private final Class<RESPONSEMODEL> responseModelClass;
    private final Class<ENTITY> entityClass;

    public AbstractMapper() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();

        this.responseModelClass = (Class<RESPONSEMODEL>) type.getActualTypeArguments()[0];

        this.entityClass = (Class<ENTITY>) type.getActualTypeArguments()[2];
    }

    public RESPONSEMODEL toResponseModel(ENTITY entity) {

        return mapper.map(entity, responseModelClass);
    }


    public ENTITY toEntity(INPUTMODEL inputmodel) {
        return mapper.map(inputmodel, entityClass);
    }

    @Override
    public void updateEntity(INPUTMODEL inputmodel, ENTITY entity) {
        mapper.map(inputmodel, entity);
    }
}
