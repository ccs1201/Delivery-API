package br.com.ccs.delivery.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EntityIdNotFoundException extends RuntimeException {
    public EntityIdNotFoundException(String message) {
        super(message);
    }
}
