package br.com.ivanfsilva.webfood;

import br.com.ivanfsilva.webfood.domain.model.Cozinha;
import br.com.ivanfsilva.webfood.domain.service.CadastroCozinhaService;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
        // cenario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // acao
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validacao
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        novaCozinha = cadastroCozinha.salvar(novaCozinha);
    }
}
