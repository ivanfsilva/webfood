package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

}
