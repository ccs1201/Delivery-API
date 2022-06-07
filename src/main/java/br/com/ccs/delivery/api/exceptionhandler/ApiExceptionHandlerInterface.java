package br.com.ccs.delivery.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

public interface ApiExceptionHandlerInterface {

    private ApiExceptionResponse buildApiExceptionResponse(Exception e, HttpStatus httpStatus) {
        return ApiExceptionResponse.builder()
                .detail(e.getMessage())
                .status(httpStatus.value())
                .type(httpStatus.getReasonPhrase())
                .title(httpStatus.getReasonPhrase())
                .build();
    }

    private ApiExceptionResponse buildApiExceptionResponse(Exception e, HttpStatus httpStatus, @NotNull String title) {
        return ApiExceptionResponse.builder()
                .detail(e.getMessage())
                .status(httpStatus.value())
                .type(httpStatus.getReasonPhrase())
                .title(title)
                .build();
    }

    private ApiExceptionResponse buildApiExceptionResponse(HttpStatus httpStatus, @NotNull String detail, @NotNull String title) {
        return ApiExceptionResponse.builder()
                .detail(detail)
                .status(httpStatus.value())
                .type(httpStatus.getReasonPhrase())
                .title(title)
                .build();
    }

    default ApiValidationErrorResponse buildApiValidationErrorResponse(HttpStatus status) {
        return ApiValidationErrorResponse.builder()
                .status(status.value())
                .type(status.getReasonPhrase())
                .build();
    }

    default ResponseEntity<Object> buildResponseEntity(@NotNull HttpStatus httpStatus, @NotNull Exception exception, @NotNull String title) {
        return ResponseEntity.status(httpStatus).body(buildApiExceptionResponse(exception, httpStatus, title));

    }

    default ResponseEntity<Object> buildResponseEntity(@NotNull HttpStatus httpStatus, @NotNull Exception exception) {
        return ResponseEntity.status(httpStatus).body(buildApiExceptionResponse(exception, httpStatus));

    }

    default ResponseEntity<Object> buildResponseEntity(@NotNull HttpStatus httpStatus, @NotNull String detail, @NotNull String title) {
        return ResponseEntity.status(httpStatus).body(buildApiExceptionResponse(httpStatus, detail, title));
    }
}
