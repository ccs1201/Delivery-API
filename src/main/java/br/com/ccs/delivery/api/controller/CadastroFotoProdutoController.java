package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.FotoProdutoInput;
import br.com.ccs.delivery.api.model.representation.mapper.MapperInterface;
import br.com.ccs.delivery.api.model.representation.response.FotoProdutoResponse;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.service.CadastroFotoProdutoService;
import br.com.ccs.delivery.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
@AllArgsConstructor
public class CadastroFotoProdutoController {

    @MapperQualifier(MapperQualifierType.FOTOPRODUTO)
    MapperInterface<FotoProdutoResponse, FotoProdutoInput, FotoProduto> mapper;
    CadastroFotoProdutoService service;

    ProdutoService produtoService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FotoProdutoResponse atualizarFoto(@PathVariable @Positive Long restauranteId, @PathVariable @Positive Long produtoId,
                                             @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = produtoService.findByRestauranteIdAndProdutoId(restauranteId, produtoId);

        var foto = FotoProduto.builder()
                //.produtoId(produto.getId())
                .produto(produto)
                .descricao(fotoProdutoInput.getDescricao())
                .tamanho(fotoProdutoInput.getMultipartFile().getSize())
                .nomeArquivo(fotoProdutoInput.getMultipartFile().getOriginalFilename())
                .contentType(fotoProdutoInput.getMultipartFile().getContentType())
                .build();

        foto = service.save(foto, fotoProdutoInput.getMultipartFile().getInputStream());

        return mapper.toResponseModel(foto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FotoProdutoResponse getFotoProduto(@PathVariable @Positive Long restauranteId,
                                              @PathVariable @Positive Long produtoId) {
        return mapper.toResponseModel(service.findFotoProduto(restauranteId, produtoId));

    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getFotoFromStorage(@PathVariable @Positive Long restauranteId,
                                                                  @PathVariable @Positive Long produtoId) {


        var file = service.getFotoFromStorage(restauranteId, produtoId);

        return ResponseEntity.ok().body(new InputStreamResource(file));

    }

}
