package br.com.ccs.delivery.domain.service.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityIdNotFoundException extends RuntimeException {

    public EntityIdNotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
