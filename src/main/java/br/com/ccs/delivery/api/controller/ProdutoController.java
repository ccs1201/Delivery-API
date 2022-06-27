package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.ProdutoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {
    ProdutoService service;
    @MapperQualifier(MapperQualifierType.PRODUTO)
    MapperInterface<ProdutoResponse, ProdutoInput, Produto> mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("/{ProdutoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponse getById(@PathVariable @Positive Long ProdutoId) {
        return mapper.toResponseModel(service.findById(ProdutoId));
    }

    @GetMapping("/nome/{nomeProduto}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getByNome(@PathVariable @NotBlank String nomeProduto) {
        return mapper.toCollection(service.findByName(nomeProduto));
    }

}
