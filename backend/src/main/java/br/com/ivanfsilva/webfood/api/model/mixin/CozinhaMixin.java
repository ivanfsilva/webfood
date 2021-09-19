package br.com.ivanfsilva.webfood.api.model.mixin;

import br.com.ivanfsilva.webfood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
