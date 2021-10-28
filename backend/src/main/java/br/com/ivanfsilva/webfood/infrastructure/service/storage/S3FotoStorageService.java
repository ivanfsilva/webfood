package br.com.ivanfsilva.webfood.infrastructure.service.storage;

import java.io.InputStream;

import br.com.ivanfsilva.webfood.domain.service.FotoStorageService;
import org.springframework.stereotype.Service;

@Service
public class S3FotoStorageService implements FotoStorageService {

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
