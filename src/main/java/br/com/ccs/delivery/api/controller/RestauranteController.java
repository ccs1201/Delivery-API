package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.model.representation.input.RestauranteInput;
import br.com.ccs.delivery.api.model.representation.input.UsuarioInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.mapper.ProdutoMapper;
import br.com.ccs.delivery.api.model.representation.mapper.TipoPagamentoMapper;
import br.com.ccs.delivery.api.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.api.model.representation.response.RestauranteResponse;
import br.com.ccs.delivery.api.model.representation.response.TipoPagamentoResponse;
import br.com.ccs.delivery.api.model.representation.response.UsuarioResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.model.util.GenericEntityUpdateMergerUtil;
import br.com.ccs.delivery.domain.repository.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.repository.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.service.ProdutoService;
import br.com.ccs.delivery.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    RestauranteService service;
    ProdutoService produtoService;
    GenericEntityUpdateMergerUtil mergerUtil;
    @MapperQualifier(MapperQualifierType.RESTAURANTE)
    MapperInterface<RestauranteResponse, RestauranteInput, Restaurante> mapper;

    TipoPagamentoMapper tipoPagamentoMapper;
    ProdutoMapper produtoMapper;

    @MapperQualifier(MapperQualifierType.USUARIO)
    MapperInterface<UsuarioResponse, UsuarioInput, Usuario> mapperUsuario;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> findAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse findById(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);

        return mapper.toResponseModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse save(@RequestBody @Valid RestauranteInput restauranteInput) {

        return mapper.toResponseModel(service.save(mapper.toEntity(restauranteInput)));
    }

    @PutMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse update(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {

        Restaurante restaurante = service.findById(restauranteId);

        mapper.updateEntity(restauranteInput, restaurante);

        return mapper.toResponseModel(service.update(restaurante));
    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }

    @PatchMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse patchUpdate(@PathVariable Long restauranteId, @RequestBody Map<String, Object> updates) {

        return mapper.toResponseModel(service.patchUpdate(restauranteId, updates));
    }

    @GetMapping("/nome-cozinha")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> findByNomeCozinha(@RequestParam String nomeCozinha) {
        return mapper.toCollection(service.findByNomeCozinha(nomeCozinha));
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> anyCriteria(String nomeRestaurante, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha) {

        return mapper.toCollection(service.anyCriteria(nomeRestaurante, taxaEntregaMin, taxaEntregaMax, nomeCozinha));

    }

    @GetMapping("/com-frete-gratis")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> comFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteNomeLikeSpec(nome);

        return mapper.toCollection(service.findAll(comFreteGratis, comNomeSemelhante));
    }

    @GetMapping("/specs")
    @ResponseStatus(HttpStatus.OK)
    public Collection<RestauranteResponse> getComSpecs(String nomeRestaurante, String nomeCozinha) {
        return mapper.toCollection(service.findAll(nomeRestaurante, nomeCozinha));
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse getFirst() {
        return mapper.toResponseModel(service.getFirst());
    }

    @PutMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        service.ativar(restauranteId);
    }

    @DeleteMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        service.inativar(restauranteId);
    }

    @GetMapping("/{restauranteId}/tipos-pagamento")
    @ResponseStatus(HttpStatus.OK)
    public Collection<TipoPagamentoResponse> getTiposPagamentoRestaurante(@PathVariable Long restauranteId) {

        Restaurante restaurante = service.findById(restauranteId);

        return tipoPagamentoMapper.toCollection(restaurante.getTiposPagamento());
    }

    @DeleteMapping("{restauranteId}/tipos-pagamento/{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoPagamento(@PathVariable Long restauranteId, @PathVariable Long tipoPagamentoId) {

        Restaurante restaurante = service.findById(restauranteId);

        service.deleteTipoPagamento(restaurante, tipoPagamentoId);
    }

    @PutMapping("{restauranteId}/tipos-pagamento/{tipoPagamentoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<TipoPagamentoResponse> addTipoPagamento(@PathVariable Long restauranteId, @PathVariable Long tipoPagamentoId) {

        Restaurante restaurante = service.findById(restauranteId);

        restaurante = service.addTipoPagamento(restaurante, tipoPagamentoId);

        return tipoPagamentoMapper.toCollection(restaurante.getTiposPagamento());
    }

    @GetMapping("/{restauranteId}/produtos")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getProdutos(@PathVariable @Positive Long restauranteId) {
        Restaurante restaurante = service.findComProdutos(restauranteId);

        return produtoMapper.toCollection(restaurante.getProdutos());
    }

    @PutMapping("/{restauranteId}/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<ProdutoResponse> addProduto(@PathVariable @Positive Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {

        Restaurante restaurante = service.findById(restauranteId);

        Produto produto = produtoMapper.toEntity(produtoInput);

        produto.setRestaurante(restaurante);
        produto.setAtivo(true);

        produtoService.save(produto);

        return produtoMapper.toCollection(service.findComProdutos(restauranteId).getProdutos());
    }

    @PutMapping("/produtos/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponse updateProduto(@PathVariable @Positive Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoService.findById(produtoId);
        produtoMapper.updateEntity(produtoInput, produto);

        return produtoMapper.toResponseModel(produtoService.update(produto));
    }

    @DeleteMapping("/produtos/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable @Positive Long produtoId) {
        Produto produto = produtoService.findById(produtoId);
        produtoService.delete(produto);
    }

    @PutMapping("/produtos/{produtoId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarProduto(@PathVariable @Positive Long produtoId) {
        Produto produto = produtoService.findById(produtoId);
        produto.setAtivo(true);

        produtoService.update(produto);
    }

    @PutMapping("/produtos/{produtoId}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarProduto(@PathVariable @Positive Long produtoId) {
        Produto produto = produtoService.findById(produtoId);
        produto.setAtivo(false);

        produtoService.update(produto);
    }

    @PutMapping("{restauranteId}/aberto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable @Positive Long restauranteId) {
        service.abrir(restauranteId);
    }

    @PutMapping("{restaruanteId}/fechado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fecharRestaurante(@PathVariable @Positive Long restaruanteId) {
        service.fechar(restaruanteId);
    }


}
