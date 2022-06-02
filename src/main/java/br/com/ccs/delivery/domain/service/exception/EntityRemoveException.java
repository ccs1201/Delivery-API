package br.com.ccs.delivery.domain.service.exception;

public class EntityRemoveException extends BusinessLogicException {
    public EntityRemoveException(String message) {
        super(message);
    }

    public EntityRemoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
