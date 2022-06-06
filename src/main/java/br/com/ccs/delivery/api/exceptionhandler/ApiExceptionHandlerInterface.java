package br.com.ccs.delivery.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

public interface ApiExceptionHandlerInterface {

    private ApiExceptionResponse buildResponse(Exception e, HttpStatus httpStatus) {
        return ApiExceptionResponse.builder()
                .detail(e.getMessage())
                .status(httpStatus.value())
                .type(httpStatus.name())
                .title(httpStatus.getReasonPhrase())
                .build();
    }

    private ApiExceptionResponse buildResponse(Exception e, HttpStatus httpStatus, String title) {
        return ApiExceptionResponse.builder()
                .detail(e.getMessage())
                .status(httpStatus.value())
                .type(httpStatus.name())
                .title(title)
                .build();
    }

    default ResponseEntity<Object> buildResponseEntity(@NotNull HttpStatus httpStatus, @NotNull Exception exception, @NotNull String title) {
        return ResponseEntity.status(httpStatus).body(buildResponse(exception, httpStatus, title));

    }
    default ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exception) {
        return ResponseEntity.status(httpStatus).body(buildResponse(exception, httpStatus));

    }
}
