package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.api.v1.mapper.ProdutoMapper;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.service.ProdutoService;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/produtos")
@RequiredArgsConstructor
public class CadastroProdutoRestauranteController {

    private final RestauranteService service;
    //    @MapperQualifier(MapperQualifierType.PRODUTO)
//    private MapperInterface<ProdutoResponse, ProdutoInput, Produto> produtoMapper;
    private final ProdutoService produtoService;
    private final ProdutoMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getProdutos(@PathVariable @Positive Long restauranteId,
                                                   @RequestParam(required = false) Boolean ativos) {
        Restaurante restaurante;

        if (ativos == null) {
            restaurante = service.findProdutos(restauranteId);
        } else {
            restaurante = service.findProdutos(restauranteId, ativos);
        }

        return mapper.toCollection(restaurante.getProdutos());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<ProdutoResponse> addProduto(@PathVariable @Positive Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {

        Restaurante restaurante = service.findById(restauranteId);

        Produto produto = mapper.toEntity(produtoInput);

        produto.setRestaurante(restaurante);
        produto.setAtivo(true);

        produtoService.save(produto);

        return mapper.toCollection(service.findProdutos(restauranteId, true).getProdutos());
    }

    @PutMapping("{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponse updateProduto(@PathVariable @Positive Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoService.findById(produtoId);
        mapper.updateEntity(produtoInput, produto);

        return mapper.toModel(produtoService.update(produto));
    }

    @DeleteMapping("{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable @Positive Long produtoId) {
        Produto produto = produtoService.findById(produtoId);
        produtoService.delete(produto);
    }

    @PatchMapping("{produtoId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarProduto(@PathVariable @Positive Long produtoId) {

        produtoService.ativar(produtoId);
    }

    @PatchMapping("{produtoId}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarProduto(@PathVariable @Positive Long produtoId) {

        produtoService.inativar(produtoId);
    }

}
