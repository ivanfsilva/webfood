package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Setter
@Getter
public class CidadeInput {

//    @ApiModelProperty(example = "Uberlândia", required = true)
    @ApiModelProperty(example = "Uberlândia")
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;

}
