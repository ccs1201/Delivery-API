package br.com.ccs.delivery.domain.service.exception;

import java.io.IOException;

public class StorageServiceException extends ServiceException {
    public StorageServiceException(String message, Exception cause) {
        super(message, cause);
    }

    public StorageServiceException(String message) {
        super(message);
    }
}
