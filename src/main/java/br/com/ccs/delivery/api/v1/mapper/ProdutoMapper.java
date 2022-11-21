package br.com.ccs.delivery.api.v1.mapper;

import br.com.ccs.delivery.api.v1.controller.ProdutoController;
import br.com.ccs.delivery.api.v1.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@MapperQualifier(MapperQualifierType.PRODUTO)
public class ProdutoMapper extends AbstractMapper<ProdutoResponse, ProdutoInput, Produto> {
    public ProdutoMapper() {

        super(ProdutoController.class, ProdutoResponse.class);
    }

    @Override
    public ProdutoResponse toModel(Produto produto) {
        return super.toModel(produto)
                .add(linkTo(
                        methodOn(ProdutoController.class)
                                .getById(produto.getId()))
                        .withSelfRel())
                .add(linkTo(ProdutoController.class).withRel("produtos"));
    }

    @Override
    public CollectionModel<ProdutoResponse> toCollectionModel(Iterable<? extends Produto> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(ProdutoController.class).withRel("produtos"));

    }
}
