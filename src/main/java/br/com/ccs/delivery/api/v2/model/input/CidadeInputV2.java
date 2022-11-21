package br.com.ccs.delivery.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Schema(name = "Cidade Input v2")
public class CidadeInputV2 {
    @NotBlank
    private String nomeCidade;

    @NotNull
    @Positive
    private Integer idEstado;
}
