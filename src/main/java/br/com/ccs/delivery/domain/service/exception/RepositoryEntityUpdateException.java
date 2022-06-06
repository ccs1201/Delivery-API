package br.com.ccs.delivery.domain.service.exception;

public class RepositoryEntityUpdateException extends ServiceRepositoryException {

    public RepositoryEntityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryEntityUpdateException(String message) {
        super(message);
    }
}
