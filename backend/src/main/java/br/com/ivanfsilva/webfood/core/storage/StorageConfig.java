package br.com.ivanfsilva.webfood.core.storage;

import br.com.ivanfsilva.webfood.domain.service.FotoStorageService;
import br.com.ivanfsilva.webfood.infrastructure.service.storage.LocalFotoStorageService;
import br.com.ivanfsilva.webfood.infrastructure.service.storage.S3FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "webfood.storage.tipo", havingValue = "s3")
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getIdChaveAcesso(),
                storageProperties.getS3().getChaveAcessoSecreta());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }

}
