package br.com.ccs.delivery.domain.service.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("delivery-api.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();

    @Getter
    @Setter
    public final class Local {

        private Path diretorio_fotos;
    }

    @Getter
    @Setter
    public final class S3 {

        private String bucket;
        private String idChaveAcesso;
        private String secretAccessKey;
        private Regions region;
        private String folder;

    }

}

