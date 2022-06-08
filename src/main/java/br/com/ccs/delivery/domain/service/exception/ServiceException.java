package br.com.ccs.delivery.domain.service.exception;

public class ServiceException extends BusinessLogicException {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}