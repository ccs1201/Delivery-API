package br.com.ccs.delivery.domain.service.storage.local;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.service.exception.StorageServiceException;
import br.com.ccs.delivery.domain.service.storage.FotoStorageService;
import br.com.ccs.delivery.domain.service.storage.StorageProperties;
import br.com.ccs.delivery.domain.service.storage.StorageServiceQualifierType;
import br.com.ccs.delivery.domain.service.storage.annotation.StorageQualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@StorageQualifier(StorageServiceQualifierType.LOCAL)
public class LocalFotoStorageServiceImpl implements FotoStorageService {

    private final StorageProperties storageProperties;
//    @Value("${delivery-api.storage.local.diretorio_fotos}")
//    private Path localFotosPath;

    public LocalFotoStorageServiceImpl(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void store(InputStream fileStream, FotoProduto fotoProduto) {

        var path = Path.of(storageProperties.getLocal().getDiretorio_fotos().toString(), fotoProduto.getNomeArquivo());

        try {
            fileStream.transferTo(Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String fileName) {

        var path = Path.of(storageProperties.getLocal().getDiretorio_fotos().toString(), fileName);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageServiceException("Não foi possível excluir a foto atual " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream getFileFromStorage(String fileName) {

        var path = Path.of(storageProperties.getLocal().getDiretorio_fotos().toString(), fileName);

        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new StorageServiceException("Não foi possível recuperar a foto do produto.", e);
        }
    }
}
