package br.com.ccs.delivery.domain.service.exception;

public class RestauranteTipoPagamentoNaoEncontradoException extends ServiceException {
    public RestauranteTipoPagamentoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestauranteTipoPagamentoNaoEncontradoException(String message) {
        super(message);
    }
}
