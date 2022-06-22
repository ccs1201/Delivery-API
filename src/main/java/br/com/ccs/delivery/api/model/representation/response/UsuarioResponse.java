package br.com.ccs.delivery.api.model.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private OffsetDateTime dataCadastro;

}
