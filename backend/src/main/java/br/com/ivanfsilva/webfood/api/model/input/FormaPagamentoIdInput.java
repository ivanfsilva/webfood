package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;

@Setter
@Getter
public class FormaPagamentoIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
