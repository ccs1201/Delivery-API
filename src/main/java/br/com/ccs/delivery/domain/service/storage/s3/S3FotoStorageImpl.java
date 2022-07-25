package br.com.ccs.delivery.domain.service.storage.s3;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.service.exception.StorageServiceException;
import br.com.ccs.delivery.domain.service.storage.FotoStorageService;
import br.com.ccs.delivery.domain.service.storage.StorageProperties;
import br.com.ccs.delivery.domain.service.storage.annotation.StorageQualifier;
import br.com.ccs.delivery.domain.service.storage.annotation.StorageServiceQualifierType;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@StorageQualifier(StorageServiceQualifierType.AMAZON_S3)
@AllArgsConstructor
@Component
public class S3FotoStorageImpl implements FotoStorageService {

    private final StorageProperties properties;
    private final AmazonS3 s3;


    @Override
    public void store(InputStream fileStream, FotoProduto fotoProduto) {

        var objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(fotoProduto.getContentType());
        objectMetaData.setContentDisposition(String.format("attachment; filename=\"%s\"", fotoProduto.getNomeArquivo()));

        try {
            var putObjectRequest =
                    new PutObjectRequest(
                            properties.getS3().getBucket(),
                            String.format(this.getPath(fotoProduto.getNomeArquivo())),
                            fileStream,
                            objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);

        } catch (AmazonServiceException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            var deleteObjectRequest =
                    new DeleteObjectRequest(properties.getS3().getBucket(), this.getPath(fileName));
            s3.deleteObject(deleteObjectRequest);
        } catch (AmazonServiceException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public FotoResource getFileFromStorage(String fileName) {

        var url =s3.getUrl(properties.getS3().getBucket(), this.getPath(fileName));

        return FotoResource
                .builder()
                .url(url.toString())
                .build();
    }

    private String getPath(String fileName) {
        return properties.getS3().getFolder() + "/" + fileName;
    }
}
