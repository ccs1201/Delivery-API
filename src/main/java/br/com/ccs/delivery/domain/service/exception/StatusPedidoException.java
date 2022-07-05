package br.com.ccs.delivery.domain.service.exception;

public class StatusPedidoException extends ServiceException {
    public StatusPedidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusPedidoException(String message) {
        super(message);
    }
}
