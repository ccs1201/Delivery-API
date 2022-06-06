package br.com.ccs.delivery.domain.service.exception;


public class RepositoryEntityInUseException extends ServiceRepositoryException {
    public RepositoryEntityInUseException(String message, Throwable cause) {
        super(message,cause);
    }

    public RepositoryEntityInUseException(String message) {
        super(message);
    }
}
