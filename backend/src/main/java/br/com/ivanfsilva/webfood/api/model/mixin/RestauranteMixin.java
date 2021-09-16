package br.com.ivanfsilva.webfood.api.model.mixin;

import br.com.ivanfsilva.webfood.domain.model.Cozinha;
import br.com.ivanfsilva.webfood.domain.model.Endereco;
import br.com.ivanfsilva.webfood.domain.model.FormaPagamento;
import br.com.ivanfsilva.webfood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
