package br.com.ccs.delivery.api.model.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("tiposPagamento")
@Getter
@Setter
@Schema(name = "TipoPagamento")
public class TipoPagamentoResponse extends RepresentationModel<TipoPagamentoResponse> {

    private Long id;
    private String nome;
}
