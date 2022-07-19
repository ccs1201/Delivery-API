package br.com.ccs.delivery.domain.service.exception;

import java.util.function.Supplier;

public class RepositoryEntityNotFoundException extends ServiceRepositoryException {

    public RepositoryEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryEntityNotFoundException(String message) {
        super(message);
    }
}
