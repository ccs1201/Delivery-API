package br.com.ccs.delivery.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
    private final String title = "One or more fields are not valid. Please check...";
    @Builder.Default
    private Collection<FieldValidationError> details = new LinkedList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class FieldValidationError {
        private String field;
        private String fieldValidationMessage;
        private String rejectedValue;
    }
}


