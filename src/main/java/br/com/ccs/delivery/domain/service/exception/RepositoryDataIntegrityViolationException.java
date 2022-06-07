package br.com.ccs.delivery.domain.service.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class RepositoryDataIntegrityViolationException extends ServiceRepositoryException {

    public RepositoryDataIntegrityViolationException(String message) {
        super(message);
    }

    public RepositoryDataIntegrityViolationException(String message, Throwable cause) {
        super(message, ExceptionUtils.getRootCause(cause));
    }
}
