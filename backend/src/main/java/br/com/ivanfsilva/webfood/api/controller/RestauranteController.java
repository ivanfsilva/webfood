package br.com.ivanfsilva.webfood.api.controller;

import br.com.ivanfsilva.webfood.Groups;
import br.com.ivanfsilva.webfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.exception.NegocioException;
import br.com.ivanfsilva.webfood.domain.model.Restaurante;
import br.com.ivanfsilva.webfood.domain.repository.RestauranteRepository;
import br.com.ivanfsilva.webfood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

//    @GetMapping("/por-taxa-frete")
//    public List<Restaurante> restaurantesPorTaxaFrete(
//            BigDecimal taxaInicial, BigDecimal taxaFinal) {
//        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
//    }

//    @GetMapping("/por-nome-query")
//    public List<Restaurante> restaurantesPorNomeQuery(
//            String nome, Long cozinhaId) {
//        return restauranteRepository.consultarPorNome(nome, cozinhaId);
//    }

//    @GetMapping("/por-nome")
//    public List<Restaurante> restaurantesPorNome(
//            String nome, Long cozinhaId) {
//        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
//    }
//
//    @GetMapping("/primeiro-por-nome")
//    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
//        return restauranteRepository.findFirstByNomeContaining(nome);
//    }
//
//    @GetMapping("/top2-por-nome")
//    public List<Restaurante> restaurantesTop2PorNome(String nome) {
//        return restauranteRepository.findTop2ByNomeContaining(nome);
//    }
//
//    @GetMapping("/count-por-cozinha")
//    public int restaurantesCountPorCozinha(Long cozinhaId) {
//        return restauranteRepository.countByCozinhaId(cozinhaId);
//    }
//
//    @GetMapping("/restaurantes/com-frete-gratis")
//    public List<Restaurante> restaurantesComFreteGratis(String nome) {
//
//        return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
//                .and(RestauranteSpecs.comNomeSemelhante(nome)));
//    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return cadastroRestaurante.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar( @Valid @RequestBody Restaurante restaurante ) {
        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,
                                 @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return cadastroRestaurante.salvar(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial( @PathVariable Long restauranteId,
                                        @RequestBody Map<String, Object> campos, HttpServletRequest request ) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual, request);

        return atualizar(restauranteId, restauranteAtual);
    }

    private void merge( Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request ) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

    //			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
