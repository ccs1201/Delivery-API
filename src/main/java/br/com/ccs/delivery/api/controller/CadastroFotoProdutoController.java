package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.FotoProdutoInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class CadastroFotoProdutoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarFoto(@PathVariable @Positive Long restauranteId, @PathVariable @Positive Long produtoId,
                              @Valid FotoProdutoInput fotoProdutoInput) {



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
