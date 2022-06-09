package br.com.ccs.delivery.core.validations.implementation;

import br.com.ccs.delivery.core.validations.annotations.ExemploValidadorDeUmNumeroMultiplo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<ExemploValidadorDeUmNumeroMultiplo,Number> {

    private Number numeroMultiplo ;

    @Override
    public void initialize(ExemploValidadorDeUmNumeroMultiplo constraintAnnotation) {
        this.numeroMultiplo = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {

        if(value != null){
            var valorParaValidar = BigDecimal.valueOf(value.doubleValue());
            var multiplo = BigDecimal.valueOf(numeroMultiplo.longValue());
            var resto = valorParaValidar.remainder(multiplo);

            return BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return true;
    }
}
