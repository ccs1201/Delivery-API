package br.com.ccs.delivery.domain.model.entity;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado"),
    ENTREGUE("Entregue");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
