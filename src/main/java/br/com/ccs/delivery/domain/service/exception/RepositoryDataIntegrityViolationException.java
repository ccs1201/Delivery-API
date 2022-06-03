package br.com.ccs.delivery.domain.service.exception;

public class RepositoryDataIntegrityViolationException extends ServiceRepositoryException {

    public RepositoryDataIntegrityViolationException(String message) {
        super(message);
    }

    public RepositoryDataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
