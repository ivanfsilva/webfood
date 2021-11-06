package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CozinhaInput {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nome;
}
