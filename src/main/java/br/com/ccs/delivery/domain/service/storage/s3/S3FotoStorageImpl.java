package br.com.ccs.delivery.domain.service.storage.s3;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.service.exception.StorageServiceException;
import br.com.ccs.delivery.domain.service.storage.FotoStorageService;
import br.com.ccs.delivery.domain.service.storage.StorageProperties;
import br.com.ccs.delivery.domain.service.storage.annotation.StorageQualifier;
import br.com.ccs.delivery.domain.service.storage.annotation.StorageServiceQualifierType;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@StorageQualifier(StorageServiceQualifierType.AMAZON_S3)
@AllArgsConstructor
@Component
public class S3FotoStorageImpl implements FotoStorageService {

    private final StorageProperties properties;
    private final AmazonS3 s3;


    private String getPath(String fileName) {
        return properties.getS3().getFolder() + "/" + fileName;
    }

    @Override
    public void store(InputStream fileStream, FotoProduto fotoProduto) {

        var objectMetaData = new ObjectMetadata();

        try {
            s3.putObject(
                    properties.getS3().getBucket(),
                    String.format(getPath(fotoProduto.getNomeArquivo())),
                    fileStream,
                    objectMetaData);

        } catch (AmazonServiceException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String fileName) {

    }

    @Override
    public InputStream getFileFromStorage(String fileName) {
        return null;
    }
}
