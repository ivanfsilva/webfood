package br.com.ivanfsilva.webfood.domain.service;

import br.com.ivanfsilva.webfood.domain.exception.PermissaoNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.model.Permissao;
import br.com.ivanfsilva.webfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
