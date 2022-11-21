package br.com.ccs.delivery.api.v2.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
@Schema(name = "Cidade Response v2")
public class CidadeResponseV2 extends RepresentationModel<CidadeResponseV2> {

    private Integer idCidade;

    private String nomeCidade;

    private Integer idEstado;

    private String nomeEstado;

    private String siglaEstado;
}
