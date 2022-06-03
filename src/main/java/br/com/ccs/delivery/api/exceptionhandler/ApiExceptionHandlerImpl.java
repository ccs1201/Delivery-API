package br.com.ccs.delivery.api.exceptionhandler;

import br.com.ccs.delivery.domain.service.exception.BusinessLogicException;
import br.com.ccs.delivery.domain.service.exception.EntityInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandlerImpl implements ApiExceptionHandlerInterface {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> businessLogicExceptionHandler(BusinessLogicException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> entityInUseExceptionHandler(EntityInUseException e) {

        return buildResponseEntity(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<?> httpMediaTypeNotAcceptableExceptionHandler() {
        return buildResponseEntity(HttpStatus.NOT_ACCEPTABLE, new HttpMediaTypeNotAcceptableException("Midia Type not acceptable."));
    }
}
