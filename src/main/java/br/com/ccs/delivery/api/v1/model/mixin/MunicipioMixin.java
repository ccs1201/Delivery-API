package br.com.ccs.delivery.api.v1.model.mixin;

import br.com.ccs.delivery.domain.model.entity.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class MunicipioMixin {

    @JsonIgnoreProperties(value = { "nome", "sigla"}, allowGetters = true)
    private Estado estado;
}
