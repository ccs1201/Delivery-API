package br.com.ccs.delivery.domain.service.exception;

public abstract class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessLogicException(String message) {
        super(message);

    }
}
