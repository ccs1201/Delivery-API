package br.com.ccs.delivery.domain.service.exception;

public class RepositoryEntityRemoveException extends ServiceRepositoryException {
    public RepositoryEntityRemoveException(String message) {
        super(message);
    }

    public RepositoryEntityRemoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
