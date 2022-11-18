package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("tiposPagamento")
@Getter
@Setter
public class TipoPagamentoResponse extends RepresentationModel<TipoPagamentoResponse> {

    private Long id;
    private String nome;
}
