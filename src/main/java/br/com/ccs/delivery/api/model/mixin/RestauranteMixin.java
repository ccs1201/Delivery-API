package br.com.ccs.delivery.api.model.mixin;

import br.com.ccs.delivery.domain.model.component.Endereco;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.model.entity.Produto;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Collection;

public abstract class RestauranteMixin {

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataUltimaAtualizacao;

    @JsonIgnore
    Collection<TipoPagamento> tiposPagamento;

    @JsonIgnore
    private Collection<Produto> produtos;
}
