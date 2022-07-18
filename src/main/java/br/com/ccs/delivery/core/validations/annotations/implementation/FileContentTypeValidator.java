package br.com.ccs.delivery.core.validations.annotations.implementation;

import br.com.ccs.delivery.core.validations.annotations.FileContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    String[] allowed;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowed = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        return  value==null || Arrays.stream(allowed).anyMatch(allowed -> allowed.equals(value.getContentType()));

    }
}
