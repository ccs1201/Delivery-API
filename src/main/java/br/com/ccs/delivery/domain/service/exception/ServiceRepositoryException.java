package br.com.ccs.delivery.domain.service.exception;

public abstract class ServiceRepositoryException extends ServiceException {

    public ServiceRepositoryException(String message, Throwable cause) {
        super(String.format("%s #Detalhes: %s ", message, cause.getMessage()), cause);
    }

    public ServiceRepositoryException(String message) {
        super(message);
    }
}
