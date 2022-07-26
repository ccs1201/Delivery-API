package br.com.ccs.delivery.core.configurations;

import br.com.ccs.delivery.domain.service.storage.FotoStorageService;
import br.com.ccs.delivery.domain.service.storage.StorageProperties;
import br.com.ccs.delivery.domain.service.storage.local.LocalFotoStorageServiceImpl;
import br.com.ccs.delivery.domain.service.storage.s3.S3FotoStorageImpl;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Autowired
    private StorageProperties properties;


    @Bean
    @ConditionalOnProperty(name = "delivery-api.storage.storage-type", havingValue = "amazon_s3")
    public AmazonS3 amazonS3() {

        var credentials = new BasicAWSCredentials(
                properties.getS3().getIdChaveAcesso(),
                properties.getS3().getSecretAccessKey());

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(properties.getS3().getRegion())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService(){
        if(properties.getStorageType().equals(StorageProperties.StorageServiceType.AMAZON_S3)){
            return new S3FotoStorageImpl();
        } else {
            return new LocalFotoStorageServiceImpl();
        }
    }
}
