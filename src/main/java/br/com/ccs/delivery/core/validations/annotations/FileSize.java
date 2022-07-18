package br.com.ccs.delivery.core.validations.annotations;

import br.com.ccs.delivery.core.validations.annotations.implementation.FileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {

    String message() default "Tamanho do arquivo inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String max();
}
