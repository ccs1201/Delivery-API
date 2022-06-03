package br.com.ccs.delivery.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ApiExceptionHandlerInterface {

    default ApiExceptionResponse buildResponse(Exception exception) {
        return ApiExceptionResponse.builder().message(exception.getMessage()).build();

    }

    default ApiExceptionResponse buildResponse(Exception e, HttpStatus httpStatus) {
        return ApiExceptionResponse.builder().message(e.getMessage())
                .status(String.format("%d %s", httpStatus.value(), httpStatus.getReasonPhrase())).build();

    }

    default ResponseEntity<?> buildResponseEntity(HttpStatus httpStatus, Exception exception) {
        return ResponseEntity.status(httpStatus).body(buildResponse(exception, httpStatus));

    }
}
