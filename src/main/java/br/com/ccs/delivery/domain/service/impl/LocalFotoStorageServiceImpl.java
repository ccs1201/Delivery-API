package br.com.ccs.delivery.domain.service.impl;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.service.FotoStorageService;
import br.com.ccs.delivery.domain.service.exception.StorageServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageServiceImpl implements FotoStorageService {

    @Value("${delivery-api.storage.local.fotos}")
    private Path localFotosPath;

    @Override
    public void store(InputStream fileStream, FotoProduto fotoProduto) {


        var path = Path.of(localFotosPath.toString(), fotoProduto.getNomeArquivo());

        try {
            fileStream.transferTo(Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String fileName) {

        var path = Path.of(localFotosPath.toString(), fileName);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageServiceException("Não foi possível excluir a foto atual " + e.getMessage(), e);
        }
    }
}
