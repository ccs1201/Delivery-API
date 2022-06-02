package br.com.ccs.delivery.domain.service.exception;

public class EntityPersistException extends RuntimeException {
    public EntityPersistException(String message) {
        super(message);
    }
}
