package br.com.ccs.delivery.core.mapper;

import org.springframework.data.domain.Page;

import java.util.Collection;

public interface MapperInterface<RESPONSEMODEL, INPUTMODEL, ENTITY> {

    RESPONSEMODEL toModel(ENTITY entity);

    ENTITY toEntity(INPUTMODEL inputmodel);

    void updateEntity(INPUTMODEL inputmodel, ENTITY entity);

    Page<RESPONSEMODEL> toPage(Page<ENTITY> page);

     Collection<RESPONSEMODEL> toCollection(Page<ENTITY> page);

     Collection<RESPONSEMODEL> toCollection(Collection<ENTITY> collection);
}

