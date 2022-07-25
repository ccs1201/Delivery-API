package br.com.ccs.delivery.domain.service.storage;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    default String generateUuidFileName(String fileName) {
        return UUID.randomUUID() + "_" + fileName;
    }

    void store(InputStream fileStream, FotoProduto fotoProduto);

    void delete(String fileName);

    FotoResource getFileFromStorage(String fileName);

    @Getter
    @Builder
    class FotoResource {
        private InputStream inputStream;
        private String url;

        public boolean isUrlPresent() {
            return url != null;
        }

        public boolean isInputStreamPresent() {
            return inputStream != null;
        }
    }
}