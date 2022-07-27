package br.com.ccs.delivery.api.exceptionhandler;

import br.com.ccs.delivery.core.exception.FieldValidationException;
import br.com.ccs.delivery.core.validations.exceptions.EntityValidationException;
import br.com.ccs.delivery.domain.model.util.exception.GenericEntityUpdateMergerUtilException;
import br.com.ccs.delivery.domain.service.exception.*;
import br.com.ccs.delivery.domain.service.reports.exception.RelatorioJasperException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandlerImpl extends ResponseEntityExceptionHandler implements ApiExceptionHandlerInterface {

    @Autowired
    private MessageSource messageSource;

    private static final String INVALID_FIELD_VALUES = "Valor inválido para um ou mais campos, verifique";


    /**
     * Handler Genérico para capturar
     * exceções não tratadas ou não
     * previstas pela nossa classe Handler
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "An unexpected error occur.")
    public ResponseEntity<Object> unCaughtHandler(Exception e) {
        // e.printStackTrace();
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                String.format("Uncaught error, please contact SYS Admin. Details: %s", e.getMessage()), "An unexpected error occur.");
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "Data integrity violation.")
    ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        return buildResponseEntity(HttpStatus.CONFLICT, rootCause.getMessage(), "Data integrity violation. Check Detail:");
    }

    @ExceptionHandler(StorageServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Fail to store file")
    ResponseEntity<Object> storageServiceExceptionHandler(StorageServiceException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, "Falha ao gravar arquivos.");
    }

    @ExceptionHandler(EntityValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = INVALID_FIELD_VALUES)
    public ResponseEntity<Object> entityValidationExceptionHandler(EntityValidationException e) {

        ApiValidationErrorResponse apiValidationErrorResponse = ApiValidationErrorResponse.builder()
                .type(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        getBindingResults(e.getBindingResult().getAllErrors(), apiValidationErrorResponse);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiValidationErrorResponse);
    }

    @ExceptionHandler(FieldValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = INVALID_FIELD_VALUES)
    public ResponseEntity<Object> fieldValidationExceptionHandler(FieldValidationException e) {
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }


    @ExceptionHandler(RepositoryEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "No record found for the given parameter")
    public ResponseEntity<?> entityNotFoundExceptionHandler(RepositoryEntityNotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e, "No record found for the given parameter");
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Exception in business logic.")
    public ResponseEntity<?> businessLogicExceptionHandler(BusinessLogicException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Exception in business logic.")
    public ResponseEntity<Object> serviceExceptionHandler(ServiceException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(RepositoryEntityInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "Record in use and cannot be removed")
    public ResponseEntity<?> entityInUseExceptionHandler(RepositoryEntityInUseException e) {

        return buildResponseEntity(HttpStatus.CONFLICT, e,
                "Registro em uso, não pode ser removido, considere inativar para que não seja mais exibido");
    }

    @ExceptionHandler(RepositoryEntityPersistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Record cannot be persisted")
    public ResponseEntity<?> entityPersistExceptionHandler(RepositoryEntityPersistException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(GenericEntityUpdateMergerUtilException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Cannot merge/update data to persist")
    public ResponseEntity<?> genericEntityUpdateMergerUtilExceptionHandler(GenericEntityUpdateMergerUtilException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(RepositoryEntityUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Cannot update record")
    public ResponseEntity<?> entityUpdateExceptionHandler(RepositoryEntityUpdateException e) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof PropertyBindingException) {

            return propertyBindingExceptionHandler((PropertyBindingException) rootCause, HttpStatus.BAD_REQUEST);

        } else if (rootCause instanceof InvalidFormatException) {

            return iInvalidFormatExceptionHandler((InvalidFormatException) rootCause, HttpStatus.BAD_REQUEST);
        }

        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(RepositoryDataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "A constraint violation occurred")
    public ResponseEntity<?> repositoryDataIntegrityViolationExceptionHandler(RepositoryDataIntegrityViolationException e) {

        //   Throwable rootCause = ExceptionUtils.getRootCause(e);

        return buildResponseEntity(HttpStatus.CONFLICT, e.getMessage(), INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Malformed data, check JSON syntax")
    public ResponseEntity<?> jsonParseExceptionHandler(JsonParseException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e, INVALID_FIELD_VALUES);
    }

    @ExceptionHandler(RelatorioJasperException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> relatorioJasperExceptionHandler(RelatorioJasperException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, "Erro ao gerar relatório");
    }

  /*  @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ApiResponse(responseCode = "412", description = "malformed data")
    public ResponseEntity<?> jsonParseExceptionHandler(NullPointerException e){
        return buildResponseEntity(HttpStatus.PRECONDITION_FAILED, e);
    }*/


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, @Nullable Object body, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {


        if (body == null) {
            return buildResponseEntity(status, ex, INVALID_FIELD_VALUES);

        } else {
            return new ResponseEntity<>(body, headers, status);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).body(validationErrorsHandler(ex, status));
    }

    private ApiValidationErrorResponse validationErrorsHandler(MethodArgumentNotValidException ex, HttpStatus status) {

        ApiValidationErrorResponse apiValidationErrorResponse
                = ApiValidationErrorResponse
                .builder()
                .status(status.value())
                .type(status.getReasonPhrase())
                .build();

        getBindingResults(ex.getAllErrors(), apiValidationErrorResponse);

        return apiValidationErrorResponse;
    }

    /*
     * Do not touch in if() sequences, the order matters. ;)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof JsonParseException) {
            return jsonParseExceptionHandler((JsonParseException) rootCause, status);
        }

        if (rootCause instanceof InvalidFormatException) {
            return iInvalidFormatExceptionHandler((InvalidFormatException) rootCause, status);
        }

        if (rootCause instanceof PropertyBindingException) {
            return propertyBindingExceptionHandler((PropertyBindingException) rootCause, status);
        }

//        if (rootCause instanceof JsonMappingException) {
//            return jsonMappingExceptionHandler((JsonMappingException) rootCause, status);
//        }


        return buildResponseEntity(status,
                "Malformed data. Check your data pattern to comply with JSON pattern.",
                "Invalid value for one or more fields");
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return this.methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex, status);
        }

        return ResponseEntity.status(status).body(buildResponseEntity(status, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("The requested resource %s does not exists.", ex.getRequestURL());

        return buildResponseEntity(status, detail, "Resource not found.");

    }

    private ResponseEntity<Object> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e, HttpStatus status) {
        String detail =
                String.format("Invalid URL parameter %s. Requires %s found %s",
                        e.getName(), Objects.requireNonNull(e.getRequiredType()).getSimpleName(),
                        Objects.requireNonNull(e.getValue()).getClass().getSimpleName());

        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        errorResponse.getDetails().add(
                ApiValidationErrorResponse.FieldValidationError
                        .builder()
                        .fieldValidationMessage(detail)
                        .rejectedValue(e.getValue().toString())
                        .build()
        );


        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> jsonParseExceptionHandler(JsonParseException e, HttpStatus status) {
        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        String message = e.getMessage().replace("\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 4, column: 21]", "");

        errorResponse.getDetails().add(ApiValidationErrorResponse.FieldValidationError
                .builder()
                .fieldValidationMessage(message)
                .build());

        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> propertyBindingExceptionHandler(PropertyBindingException e, HttpStatus status) {

        return buildResponseEntity(status,
                String.format("Property %s does not exists.", e.getPropertyName()), INVALID_FIELD_VALUES);
    }

    private ResponseEntity<Object> iInvalidFormatExceptionHandler(InvalidFormatException e, HttpStatus status) {
        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        e.getPath().forEach(path -> errorResponse
                .getDetails()
                .add(ApiValidationErrorResponse.FieldValidationError
                        .builder()
                        .field(path.getFieldName())
                        .fieldValidationMessage(String.format("Requires %s Found %s", e.getTargetType().getSimpleName(), e.getValue().getClass().getSimpleName()))
                        .rejectedValue((String) e.getValue())
                        .build()));

        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> jsonMappingExceptionHandler(JsonMappingException e, HttpStatus status) {

        ApiValidationErrorResponse errorResponse = buildApiValidationErrorResponse(status);

        e.getPath().forEach(path -> errorResponse
                .getDetails()
                .add(ApiValidationErrorResponse.FieldValidationError
                        .builder()
                        .field(path.getFieldName())
                        .fieldValidationMessage(e.getOriginalMessage())
                        .build()));

        return ResponseEntity.status(status).body(errorResponse);
    }


    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private void getBindingResults(List<ObjectError> e, ApiValidationErrorResponse apiValidationErrorResponse) {
        e.forEach(error -> {

            if (error instanceof FieldError fieldError) {

                apiValidationErrorResponse.getDetails().add(
                        ApiValidationErrorResponse.FieldValidationError
                                .builder()
                                .field(fieldError.getField())
                                .fieldValidationMessage(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()))
                                .rejectedValue(String.format("%s", fieldError.getRejectedValue()))
                                .build());

            } else {
                apiValidationErrorResponse.getDetails().add(
                        ApiValidationErrorResponse.FieldValidationError
                                .builder()
                                .field(error.getObjectName())
                                .fieldValidationMessage(messageSource.getMessage(error, LocaleContextHolder.getLocale()))
                                .build());
            }
        });
    }


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.bindExceptionHandler(ex);
    }

    private ResponseEntity<Object> bindExceptionHandler(BindException e) {

        ApiValidationErrorResponse apiValidationErrorResponse = ApiValidationErrorResponse.builder()
                .type(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        getBindingResults(e.getBindingResult().getAllErrors(), apiValidationErrorResponse);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiValidationErrorResponse);
    }
}
