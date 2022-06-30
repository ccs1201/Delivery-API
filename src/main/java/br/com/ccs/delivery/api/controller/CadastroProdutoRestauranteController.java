package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.service.ProdutoService;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/produtos")
@AllArgsConstructor
public class CadastroProdutoRestauranteController {

    private RestauranteService service;
    @MapperQualifier(MapperQualifierType.PRODUTO)
    private MapperInterface<ProdutoResponse, ProdutoInput, Produto> produtoMapper;
    private ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getProdutos(@PathVariable @Positive Long restauranteId) {
        Restaurante restaurante = service.findComProdutos(restauranteId);

        return produtoMapper.toCollection(restaurante.getProdutos());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<ProdutoResponse> addProduto(@PathVariable @Positive Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {

        Restaurante restaurante = service.findById(restauranteId);

        Produto produto = produtoMapper.toEntity(produtoInput);

        produto.setRestaurante(restaurante);
        produto.setAtivo(true);

        produtoService.save(produto);

        return produtoMapper.toCollection(service.findComProdutos(restauranteId).getProdutos());
    }

    @PutMapping("{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponse updateProduto(@PathVariable @Positive Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoService.findById(produtoId);
        produtoMapper.updateEntity(produtoInput, produto);

        return produtoMapper.toResponseModel(produtoService.update(produto));
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
