package br.com.ivanfsilva.webfood.api.controller;

import br.com.ivanfsilva.webfood.api.assembler.PedidoInputDisassembler;
import br.com.ivanfsilva.webfood.api.assembler.PedidoModelAssembler;
import br.com.ivanfsilva.webfood.api.assembler.PedidoResumoModelAssembler;
import br.com.ivanfsilva.webfood.api.model.PedidoModel;
import br.com.ivanfsilva.webfood.api.model.PedidoResumoModel;
import br.com.ivanfsilva.webfood.api.model.input.PedidoInput;
import br.com.ivanfsilva.webfood.api.openapi.controller.PedidoControllerOpenApi;
import br.com.ivanfsilva.webfood.core.data.PageableTranslator;
import br.com.ivanfsilva.webfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.exception.NegocioException;
import br.com.ivanfsilva.webfood.domain.model.Pedido;
import br.com.ivanfsilva.webfood.domain.model.Usuario;
import br.com.ivanfsilva.webfood.domain.repository.PedidoRepository;
import br.com.ivanfsilva.webfood.domain.filter.PedidoFilter;
import br.com.ivanfsilva.webfood.domain.service.EmissaoPedidoService;
import br.com.ivanfsilva.webfood.infrastructure.repository.spec.PedidoSpecs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                             @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler
                .toCollectionModel(pedidosPage.getContent());

        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());

        return pedidosResumoModelPage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "cliente.nome", "cliente.nome"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
