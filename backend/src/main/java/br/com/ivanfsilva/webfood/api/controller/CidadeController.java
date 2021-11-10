package br.com.ivanfsilva.webfood.api.controller;

import br.com.ivanfsilva.webfood.api.ResourceUriHelper;
import br.com.ivanfsilva.webfood.api.assembler.CidadeInputDisassembler;
import br.com.ivanfsilva.webfood.api.assembler.CidadeModelAssembler;
import br.com.ivanfsilva.webfood.api.openapi.controller.CidadeControllerOpenApi;
import br.com.ivanfsilva.webfood.api.model.CidadeModel;
import br.com.ivanfsilva.webfood.api.model.input.CidadeInput;

import br.com.ivanfsilva.webfood.domain.exception.EstadoNaoEncontradoException;
import br.com.ivanfsilva.webfood.domain.exception.NegocioException;
import br.com.ivanfsilva.webfood.domain.model.Cidade;
import br.com.ivanfsilva.webfood.domain.repository.CidadeRepository;
import br.com.ivanfsilva.webfood.domain.service.CadastroCidadeService;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista as cidades")
    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @Override
    @GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

        cidadeModel.add(Link.of("http://localhost:8080/cidades/1"));
//		cidadeModel.add(Link.of("http://localhost:8080/cidades/1", IanaLinkRelations.SELF));

//		cidadeModel.add(Link.of("http://localhost:8080/cidades", IanaLinkRelations.COLLECTION));
        cidadeModel.add(Link.of("http://localhost:8080/cidades", "cidades"));

        cidadeModel.getEstado().add(Link.of("http://localhost:8080/estados/1"));

        return cidadeModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar( @RequestBody @Valid CidadeInput cidadeInput ) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar( @PathVariable Long cidadeId,
                                  @RequestBody @Valid CidadeInput cidadeInput ) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover( @PathVariable Long cidadeId ) {

        cadastroCidade.excluir(cidadeId);
    }

}
