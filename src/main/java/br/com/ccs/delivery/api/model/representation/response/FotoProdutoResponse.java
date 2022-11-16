package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class FotoProdutoResponse extends RepresentationModel<FotoProdutoResponse> {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
