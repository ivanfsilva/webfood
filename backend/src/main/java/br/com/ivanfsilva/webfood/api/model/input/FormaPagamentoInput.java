package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;

}