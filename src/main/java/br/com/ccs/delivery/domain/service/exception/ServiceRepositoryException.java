package br.com.ccs.delivery.domain.service.exception;

public class ServiceRepositoryException extends BusinessLogicException {

    public ServiceRepositoryException(String message, Throwable cause) {
        super(String.format("%s ,\nDetalhes:\n%s ", message, cause.getMessage()), cause);
    }

    public ServiceRepositoryException(String message) {
        super(message);
    }
}
