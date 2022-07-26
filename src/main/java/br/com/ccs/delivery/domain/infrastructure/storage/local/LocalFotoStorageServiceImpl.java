package br.com.ccs.delivery.domain.infrastructure.storage.local;

import br.com.ccs.delivery.domain.infrastructure.storage.StorageProperties;
import br.com.ccs.delivery.domain.service.FotoStorageService;
import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.service.exception.StorageServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class LocalFotoStorageServiceImpl implements FotoStorageService {

    @Autowired
    private StorageProperties storageProperties;
//    @Value("${delivery-api.storage.local.diretorio_fotos}")
//    private Path localFotosPath;


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
    public FotoResource getFileFromStorage(String fileName) {

        var path = Path.of(storageProperties.getLocal().getDiretorio_fotos().toString(), fileName);

        try {
            return FotoResource
                    .builder()
                    .inputStream(Files.newInputStream(path))
                    .build();
        } catch (IOException e) {
            throw new StorageServiceException("Não foi possível recuperar a foto do produto.", e);
        }
    }
}
