package br.com.ccs.delivery.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)//, reason = "Registro não pode ser removido.")
public class EntityInUseException extends RuntimeException {
    public EntityInUseException(String message, Throwable cause) {

        super(String.format("%s ,\nDetalhes:\n%s ", message, cause.getMessage()), cause);
    }
}
