package br.com.ccs.delivery.core.validations.annotations;

import br.com.ccs.delivery.core.validations.implementation.ValorZeroIncluiDescricaoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {
    String fieldTaxa();

    String fieldName();

    String descricaoObrigatoria();

    String message() default "{ValorZeroIncluiDescricao}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
