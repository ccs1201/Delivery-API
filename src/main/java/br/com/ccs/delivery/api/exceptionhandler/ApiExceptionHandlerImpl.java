package br.com.ccs.delivery.api.exceptionhandler;

import br.com.ccs.delivery.domain.service.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandlerImpl extends ResponseEntityExceptionHandler implements ApiExceptionHandlerInterface {


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

    @ExceptionHandler(RepositoryEntityPersistException.class)
    public ResponseEntity<?> entityPersistExceptionHandler(RepositoryEntityPersistException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(RepositoryEntityRemoveException.class)
    public ResponseEntity<?> entityRemoveExceptionHandler(RepositoryEntityRemoveException e) {
        return buildResponseEntity(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(EntityUpdateException.class)
    public ResponseEntity<?> entityUpdateExceptionHandler(EntityUpdateException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(body, headers, status);
    }
}
