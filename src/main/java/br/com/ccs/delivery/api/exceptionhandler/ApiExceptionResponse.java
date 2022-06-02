package br.com.ccs.delivery.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
@Getter
@Builder
public class ApiExceptionResponse {

    private OffsetDateTime dateTime;
    private String message;

}
