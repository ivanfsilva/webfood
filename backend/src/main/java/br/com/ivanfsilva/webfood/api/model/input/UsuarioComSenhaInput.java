package br.com.ivanfsilva.webfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModelProperty;

@Setter
@Getter
public class UsuarioComSenhaInput extends UsuarioInput {

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;
}
