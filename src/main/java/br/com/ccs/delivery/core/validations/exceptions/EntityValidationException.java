package br.com.ccs.delivery.core.validations.exceptions;

import br.com.ccs.delivery.domain.service.exception.BusinessLogicException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class EntityValidationException extends BusinessLogicException {

    private final BindingResult bindingResult;

}
