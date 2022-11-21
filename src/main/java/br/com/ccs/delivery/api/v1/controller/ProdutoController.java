package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.response.ProdutoResponse;
import br.com.ccs.delivery.api.v1.mapper.ProdutoMapper;
import br.com.ccs.delivery.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;
    private final ProdutoMapper mapper;

//    @MapperQualifier(MapperQualifierType.PRODUTO)
//    MapperInterface<ProdutoResponse, ProdutoInput, Produto> mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getAll() {
        return mapper.toCollection(service.findAll());
    }

    @GetMapping("/{ProdutoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponse getById(@PathVariable @Positive Long ProdutoId) {
        return mapper.toModel(service.findById(ProdutoId));
    }

    @GetMapping("/nome/{nomeProduto}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProdutoResponse> getByNome(@PathVariable @NotBlank String nomeProduto) {
        return mapper.toCollection(service.findByName(nomeProduto));
    }

}
