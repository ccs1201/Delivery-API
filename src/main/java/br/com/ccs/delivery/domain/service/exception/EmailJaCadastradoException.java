package br.com.ccs.delivery.domain.service.exception;

import org.hibernate.exception.ConstraintViolationException;

public class EmailJaCadastradoException extends ServiceException {
    public EmailJaCadastradoException(String message, Throwable e) {
        super(message, e);
    }
}
