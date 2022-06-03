package br.com.ccs.delivery.domain.service.exception;


public class EntityInUseException extends ServiceRepositoryException {
    public EntityInUseException(String message, Throwable cause) {
        super(message,cause);
    }

    public EntityInUseException(String message) {
        super(message);
    }
}
