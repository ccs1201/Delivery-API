package br.com.ccs.delivery.domain.service.exception;

public class EntityUpdateException extends RuntimeException {

    public EntityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityUpdateException(String message) {
        super(message);
    }
}
