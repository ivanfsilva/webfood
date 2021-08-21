package br.com.ivanfsilva.webfood.api.controller;

import br.com.ivanfsilva.webfood.api.model.CozinhasXmlWrapper;
import br.com.ivanfsilva.webfood.domain.exception.EntidadeEmUsoException;
import br.com.ivanfsilva.webfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.model.Cozinha;
import br.com.ivanfsilva.webfood.domain.repository.CozinhaRepository;
import br.com.ivanfsilva.webfood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping("/por-nome")
    public List<Cozinha> cozinhaPorNome( String nome ) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if ( cozinha.isPresent() ) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar( @RequestBody Cozinha cozinha ) {
        return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

            Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
