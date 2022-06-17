package br.com.ccs.delivery.api.model.representation.input.component;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonRootName("endereco")
@Getter
@Setter
public class EnderecoInput {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotNull
    @Size(min = 8, max = 8, message = "Cep deve conter 8 dígitos")
    private String cep;
    @NotNull
    @Valid
    private MunicipioIdInput municipio;
}
