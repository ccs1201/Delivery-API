package br.com.ccs.delivery.core.jackson;

import br.com.ccs.delivery.api.model.mixin.*;
import br.com.ccs.delivery.domain.model.entity.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Municipio.class, MunicipioMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Estado.class, EstadoMixin.class);
        setMixInAnnotation(TipoPagamento.class, TipoPagamentoMixin.class);
    }
}
