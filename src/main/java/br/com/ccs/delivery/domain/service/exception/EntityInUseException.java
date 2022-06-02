package br.com.ccs.delivery.domain.service.exception;

//@ResponseStatus(value = HttpStatus.CONFLICT)//, reason = "Registro não pode ser removido.")
public class EntityInUseException extends RuntimeException {
    public EntityInUseException(String reason, Throwable cause) {
        super(String.format("%s ,\nDetalhes:\n%s ", reason, cause.getMessage()), cause);
    }

}
