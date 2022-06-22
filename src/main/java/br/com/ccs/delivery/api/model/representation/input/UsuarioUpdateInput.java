package br.com.ccs.delivery.api.model.representation.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioUpdateInput {

    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
}
