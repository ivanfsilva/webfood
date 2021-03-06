package br.com.ivanfsilva.webfood.api.model.mixin;

import br.com.ivanfsilva.webfood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}