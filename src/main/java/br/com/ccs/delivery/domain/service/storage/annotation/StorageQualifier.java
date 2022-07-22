package br.com.ccs.delivery.domain.service.storage.annotation;

import br.com.ccs.delivery.domain.service.storage.StorageServiceQualifierType;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface StorageQualifier {
    StorageServiceQualifierType value();
}