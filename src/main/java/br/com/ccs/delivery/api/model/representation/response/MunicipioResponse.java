package br.com.ccs.delivery.api.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "municipios")
@Getter
@Setter
public class MunicipioResponse extends RepresentationModel<MunicipioResponse> {

    private Integer id;

    private String nome;
    @JsonIgnoreProperties({"id", "sigla"})
    private EstadoResponse estado;
}
