package br.com.ccs.delivery.domain.service.exception;

public class EntityPersistException extends BusinessLogicException {
    public EntityPersistException(String message) {
        super(message);
    }

    public EntityPersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
