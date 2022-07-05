package br.com.ccs.delivery.domain.service.exception;

public class ProdutoNaoExisteNoCardapioDoRestauranteException extends ServiceException {

    public ProdutoNaoExisteNoCardapioDoRestauranteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProdutoNaoExisteNoCardapioDoRestauranteException(String message) {
        super(message);
    }
}
