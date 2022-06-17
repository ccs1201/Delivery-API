package br.com.ccs.delivery.api.model.representation.input.component;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@JsonRootName("municipio")
public class MunicipioIdInput {
    @NotNull
    @Positive
    private Long id;
}
