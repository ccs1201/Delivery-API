package br.com.ccs.delivery.api.v1.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Schema(name = "FotoProduto")
public class FotoProdutoResponse extends RepresentationModel<FotoProdutoResponse> {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
