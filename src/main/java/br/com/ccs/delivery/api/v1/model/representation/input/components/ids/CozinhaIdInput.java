package br.com.ccs.delivery.api.v1.model.representation.input.components.ids;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@JsonRootName("cozinha")
public class CozinhaIdInput {

    @NotNull
    @Positive
    private Long id;
}
