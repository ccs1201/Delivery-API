package br.com.ccs.delivery.api.exceptionhandler;

import br.com.ccs.delivery.domain.service.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e) {

        ApiExceptionResponse response = ApiExceptionResponse.builder().message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> businessLogicExceptionHandler(BusinessLogicException e) {

        ApiExceptionResponse response = ApiExceptionResponse.builder().message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
