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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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
                                             @Valid FotoProdutoInput fotoProdutoInput) {


        Produto produto = produtoService.findByRestauranteIdAndProdutoId(restauranteId,produtoId);

        var foto =  FotoProduto.builder()
                .produtoId(produto.getId())
                .produto(produto)
                .descricao(fotoProdutoInput.getDescricao())
                .tamanho(fotoProdutoInput.getFile().getSize())
                .nomeArquivo(fotoProdutoInput.getFile().getOriginalFilename())
                .contentType(fotoProdutoInput.getFile().getContentType())
                .build();


        foto = service.save(foto);

        return mapper.toResponseModel(foto);

//        var fileName = UUID.randomUUID() + "_" + fotoProdutoInput.getFile().getOriginalFilename();
//
//        var path = Path.of("/home/csouza/Pictures/delivery_API_Fotos_Produtos", fileName);
//
//        try {
//            fotoProdutoInput.getFile().transferTo(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
