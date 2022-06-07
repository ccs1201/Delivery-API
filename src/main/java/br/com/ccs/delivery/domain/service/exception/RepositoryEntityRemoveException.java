package br.com.ccs.delivery.domain.service.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class RepositoryEntityRemoveException extends ServiceRepositoryException {
    public RepositoryEntityRemoveException(String message) {
        super(message);
    }

    public RepositoryEntityRemoveException(String message, Throwable cause) {
        super(message, ExceptionUtils.getRootCause(cause));
    }
}
