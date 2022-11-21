package br.com.ccs.delivery.api.v1.model.representation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "municipios")
@Getter
@Setter
@Schema(name = "Municipio")
public class MunicipioResponse extends RepresentationModel<MunicipioResponse> {

    private Integer id;

    private String nome;
    @JsonIgnoreProperties({"id", "sigla"})
    private EstadoResponse estado;
}
