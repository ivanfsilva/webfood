package br.com.ivanfsilva.webfood.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CidadeModel", description = "Representa uma cidade") // não é necessário por ser muito óbvio. Deixei apenas para referênia
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(example = "Uberlândia")
    private String nome;
    private EstadoModel estado;

}
