package br.com.ccs.delivery.api.exceptionhandler;

import br.com.ccs.delivery.domain.model.util.exception.GenericEntityUpdateMergerUtilException;
import br.com.ccs.delivery.domain.service.exception.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@ControllerAdvice
public class ApiExceptionHandlerImpl extends ResponseEntityExceptionHandler implements ApiExceptionHandlerInterface {


    @ExceptionHandler(RepositoryEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "No record found for the given parameter")
    public ResponseEntity<?> entityNotFoundExceptionHandler(RepositoryEntityNotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Exception in business logic.")
    public ResponseEntity<?> businessLogicExceptionHandler(BusinessLogicException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(RepositoryEntityInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "Record in use and cannot be removed")
    public ResponseEntity<?> entityInUseExceptionHandler(RepositoryEntityInUseException e) {

        return buildResponseEntity(HttpStatus.CONFLICT, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(RepositoryEntityPersistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Record cannot be persisted")
    public ResponseEntity<?> entityPersistExceptionHandler(RepositoryEntityPersistException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(GenericEntityUpdateMergerUtilException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Cannot merge/update data to persist")
    public ResponseEntity<?> genericEntityUpdateMergerUtilExceptionHandler(GenericEntityUpdateMergerUtilException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(RepositoryEntityUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Cannot update record")
    public ResponseEntity<?> entityUpdateExceptionHandler(RepositoryEntityUpdateException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(RepositoryDataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "A constraint violation occurred")
    public ResponseEntity<?> repositoryDataIntegrityViolationExceptionHandler(RepositoryDataIntegrityViolationException e) {
        return buildResponseEntity(HttpStatus.CONFLICT, e, "Invalid value for one or more fields");
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "malformed data, check JSON syntax")
    public ResponseEntity<?> jsonParseExceptionHandler(JsonParseException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, "Invalid value for one or more fields");
    }

  /*  @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ApiResponse(responseCode = "412", description = "malformed data")
    public ResponseEntity<?> jsonParseExceptionHandler(NullPointerException e){
        return buildResponseEntity(HttpStatus.PRECONDITION_FAILED, e);
    }*/


    @Override
    protected @NotNull ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, @Nullable Object body, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {

        if (ex instanceof MethodArgumentNotValidException) {

            return new ResponseEntity<>(httpRequestMethodNotSupportedHandler((MethodArgumentNotValidException) ex, status), headers, status);

        } else if (body == null) {
            return buildResponseEntity(status, ex, "Invalid value for one or more fields");

        } else {
            return new ResponseEntity<>(body, headers, status);
        }
    }

    private ApiValidationErrorResponse httpRequestMethodNotSupportedHandler(MethodArgumentNotValidException ex, HttpStatus status) {

        ApiValidationErrorResponse apiValidationErrorResponse = ApiValidationErrorResponse.builder().status(status.value()).type(status.name()).build();

        ex.getFieldErrors().forEach(fieldError -> apiValidationErrorResponse.getDetails().add(apiValidationErrorResponse.new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage(), String.format("%s", fieldError.getRejectedValue()))));

        return apiValidationErrorResponse;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof JsonParseException) {
            return jsonParseExceptionHandler((JsonParseException) rootCause, status);
        }

     /*   if (rootCause instanceof InvalidFormatException) {
            return iInvalidFormatExceptionHandler((InvalidFormatException) rootCause, status);
        }*/

        if (rootCause instanceof JsonMappingException) {
            return jsonMappingExceptionHandler((JsonMappingException) rootCause, status);
        }


        return buildResponseEntity(status, new Exception("Malformed data. Check your data pattern to comply with JSON pattern."), "Invalid value for one or more fields");
    }

    private ResponseEntity iInvalidFormatExceptionHandler(InvalidFormatException e, HttpStatus status) {
        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        e.getPath().forEach(path -> {
            errorResponse
                    .getDetails()
                    .add(errorResponse.new
                            FieldValidationError(
                            path.getFieldName(),
                            String.format("Requires %s Found %s", e.getTargetType().getSimpleName(), e.getValue().getClass().getSimpleName()),
                            (String) e.getValue()));
        });
        return new ResponseEntity(errorResponse, status);
    }

    private ResponseEntity jsonParseExceptionHandler(JsonParseException e, HttpStatus status) {
        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        String message = e.getMessage().replace("\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 4, column: 21]", "");

        errorResponse.getDetails().add(errorResponse.new FieldValidationError(null, message, null));

        return new ResponseEntity(errorResponse, status);
    }


    public ResponseEntity jsonMappingExceptionHandler(JsonMappingException e, HttpStatus status) {

        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        e.getPath().forEach(path -> {
            errorResponse
                    .getDetails()
                    .add(errorResponse.new
                            FieldValidationError(
                            path.getFieldName(),
                            "" + e.getOriginalMessage(),//.replace("\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 4, column: 21]", ""),
                            null));
        });

        return new ResponseEntity<>(errorResponse, status);

    }
}
