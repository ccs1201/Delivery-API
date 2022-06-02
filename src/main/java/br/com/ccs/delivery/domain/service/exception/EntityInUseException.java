package br.com.ccs.delivery.domain.service.exception;

//@ResponseStatus(value = HttpStatus.CONFLICT)//, reason = "Registro não pode ser removido.")
public class EntityInUseException extends BusinessLogicException {
    public EntityInUseException(String reason, Throwable cause) {
        super(String.format("%s ,\nDetalhes:\n%s ", reason, cause.getMessage()), cause);
    }

    public EntityInUseException(String message) {
        super(message);
    }
}
