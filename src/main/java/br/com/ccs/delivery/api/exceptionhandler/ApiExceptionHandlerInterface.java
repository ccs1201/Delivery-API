package br.com.ccs.delivery.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ApiExceptionHandlerInterface {

    private ApiExceptionResponse buildApiExceptionResponse(Exception e) {
        return ApiExceptionResponse.builder().message(e.getMessage()).build();

    }

    default ResponseEntity<?> buildResponseEntity(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(buildApiExceptionResponse(e));

    }
}
