package br.com.ivanfsilva.webfood.infrastructure.service.storage;

import br.com.ivanfsilva.webfood.core.storage.StorageProperties;
import br.com.ivanfsilva.webfood.domain.service.FotoStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalFotoStorageService implements FotoStorageService {

    private StorageProperties storeStorageProperties;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            return Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storeStorageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
    }
}
