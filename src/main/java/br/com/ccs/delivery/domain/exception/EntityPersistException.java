package br.com.ccs.delivery.domain.exception;

public class EntityPersistException extends RuntimeException {
    public EntityPersistException(String message) {
        super(message);
    }
}
