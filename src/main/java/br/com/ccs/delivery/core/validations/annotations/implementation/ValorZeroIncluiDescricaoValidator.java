package br.com.ccs.delivery.core.validations.annotations.implementation;

import br.com.ccs.delivery.core.validations.annotations.ValorZeroIncluiDescricao;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String fieldTaxa;

    private String fieldName;

    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.fieldTaxa = constraintAnnotation.fieldTaxa();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {
            BigDecimal valor = (BigDecimal) BeanUtils
                    .getPropertyDescriptor(value.getClass(), fieldTaxa).getReadMethod().invoke(value, null);
            String name = (String) BeanUtils
                    .getPropertyDescriptor(value.getClass(), fieldName).getReadMethod().invoke(value, null);


            if(valor != null && BigDecimal.ZERO.compareTo(valor)==0 && descricaoObrigatoria !=null){

                return name.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return true;
    }
}
