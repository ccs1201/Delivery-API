package br.com.ccs.delivery.core.validations.exceptions;

import br.com.ccs.delivery.domain.service.exception.BusinessLogicException;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class EntityValidationException extends BusinessLogicException {

    private final BindingResult bindingResult;

    public EntityValidationException(BindingResult bindingResult) {

        this.bindingResult = bindingResult;
    }
}
