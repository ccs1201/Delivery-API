package br.com.ccs.delivery.domain.response;

public class RestauranteResponse {
    String nome;
    String nomeCozinha;

    public RestauranteResponse(String nome, String nomeCozinha) {
        this.nome = nome;
        this.nomeCozinha = nomeCozinha;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeCozinha() {
        return nomeCozinha;
    }
}
