package br.com.ccs.delivery.api.v1.model.mixin;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public abstract class EstadoMixin {

    @JsonIgnore
    private Collection<Municipio> municipios;
}
