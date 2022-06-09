package br.com.ccs.delivery.core.validations.annotations;

import br.com.ccs.delivery.core.validations.implementation.MultiploValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, PARAMETER, CONSTRUCTOR, TYPE_USE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface ExemploValidadorDeUmNumeroMultiplo {

    int numero ();

    String message() default "{Multiplo.Invalido}"; //Carrega a msg do messages bundle (messages.properties)

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
