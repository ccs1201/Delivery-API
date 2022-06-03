package br.com.ccs.delivery.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
@Getter
@Builder
public class ApiExceptionResponse {

    @Builder.Default
    private OffsetDateTime dateTime = OffsetDateTime.now();
    private String status;
    private String message;

}
