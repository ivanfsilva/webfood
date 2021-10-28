package br.com.ivanfsilva.webfood.infrastructure.service.storage;

import java.io.InputStream;

import br.com.ivanfsilva.webfood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
    }

    @Override
    public void remover(String nomeArquivo) {
    }

}
