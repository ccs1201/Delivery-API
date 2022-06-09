package br.com.ccs.delivery.core.validations.annotations;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, PARAMETER, CONSTRUCTOR, TYPE_USE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotNull
@PositiveOrZero
public @interface TaxaEntregaValidator {

    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{TaxaEntrega.invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
