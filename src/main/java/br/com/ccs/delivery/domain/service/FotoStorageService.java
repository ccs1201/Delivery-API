package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    default String generateUuidFileName(String fileName) {
        return UUID.randomUUID() + "_" + fileName;
    }

    void store(InputStream fileStream, FotoProduto fotoProduto);

    void delete(String fileName);

    InputStream getFileFromStorage(String fileName);
}