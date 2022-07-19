package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoResponse {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
