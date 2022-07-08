package br.com.ccs.delivery.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.LinkedList;

@Getter
@Setter
@Builder
public final class ApiValidationErrorResponse {

    @Setter(AccessLevel.PRIVATE)
    @Builder.Default
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private int status;
    private String type;
    @Setter(AccessLevel.NONE)
    private final String title = "Um ou mais campos não são válidos, por favor verifique...";
    @Builder.Default
    private Collection<FieldValidationError> details = new LinkedList<>();

    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FieldValidationError {
        private String field;
        private String fieldValidationMessage;
        private String rejectedValue;
    }
}


