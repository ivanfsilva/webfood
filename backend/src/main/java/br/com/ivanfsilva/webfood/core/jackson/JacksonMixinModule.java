package br.com.ivanfsilva.webfood.core.jackson;

import br.com.ivanfsilva.webfood.api.model.mixin.RestauranteMixin;
import br.com.ivanfsilva.webfood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
