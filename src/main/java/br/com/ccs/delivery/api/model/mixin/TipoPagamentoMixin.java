package br.com.ccs.delivery.api.model.mixin;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public abstract class TipoPagamentoMixin {

    @JsonIgnore
    private Collection<Restaurante> restaurantes;

}
