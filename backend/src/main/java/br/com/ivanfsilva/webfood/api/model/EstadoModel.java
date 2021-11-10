package br.com.ivanfsilva.webfood.api.model;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class EstadoModel extends RepresentationModel<EstadoModel> {


    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Minas Gerais")
    private String nome;

}
