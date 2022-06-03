package br.com.ccs.delivery.domain.service.exception;

public class RepositoryEntityPersistException extends ServiceRepositoryException {
    public RepositoryEntityPersistException(String message) {
        super(message);
    }

    public RepositoryEntityPersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
