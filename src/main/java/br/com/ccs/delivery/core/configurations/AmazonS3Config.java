package br.com.ccs.delivery.core.configurations;

import br.com.ccs.delivery.domain.service.storage.StorageProperties;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {
    private final StorageProperties properties;

    public AmazonS3Config(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
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
}
