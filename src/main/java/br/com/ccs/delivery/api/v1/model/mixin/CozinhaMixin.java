package br.com.ccs.delivery.api.v1.model.mixin;

import br.com.ccs.delivery.domain.model.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public abstract class CozinhaMixin {

    @JsonIgnore
    private Collection<Restaurante> restaurantes;
}
