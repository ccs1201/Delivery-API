package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.component.Endereco;
import br.com.ccs.delivery.domain.model.entity.*;
import br.com.ccs.delivery.domain.repository.RestauranteRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
class RestauranteControllerTest {
    private static final String BASE_URI = "/api/restaurantes/";
    @LocalServerPort
    private int port;

    @Autowired
    RestauranteRepository repository;
    private long qtdRegistrosBanco;

    private Restaurante restaurante;

    @BeforeAll
    void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        basePath = BASE_URI;
        RestAssured.port = port;
        qtdRegistrosBanco = repository.count();

        Collection<TipoPagamento> tiposPagamento = new ArrayList<>();
        tiposPagamento.add(TipoPagamento.builder().id(1L).build());

        Collection<Produto> produtos = new ArrayList<>();
        produtos.add(Produto.builder().id(1L).build());

        restaurante = Restaurante
                .builder()
                .cozinha(Cozinha.builder()
                        .id(1L)
                        .build())
                .endereco(Endereco.builder()
                        .bairro("campinas")
                        .cep("88101350")
                        .complemento("CASA")
                        .logradouro("RUA DAS ANCHOVAS")
                        .numero("333A")
                        .municipio(Municipio.builder().id(1).build())
                        .build()
                )
                .nome("Teste Restaurante")
                .taxaEntrega(BigDecimal.valueOf(1.99))
                .tiposPagamento(tiposPagamento)
                .produtos(produtos)
                .build();
    }


    @Test
    @DisplayName("Testa save, deve retornar '201 CREATED' e ID != de Null")
    void save() {
        given()
                .contentType(ContentType.JSON)
                .body(restaurante)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Testa update, deve retornar 200 OK se atualizar com sucesso.")
    void update() {

        Restaurante restauranteFromBd = repository.findAll().stream().findFirst().get();

        restauranteFromBd.setNome(restauranteFromBd.getNome() + " Updated");

        restauranteFromBd.setTaxaEntrega(BigDecimal.valueOf(1.99));

        given()
                .pathParam("id", restauranteFromBd.getId())
                .contentType(ContentType.JSON)
                .body(restauranteFromBd)
                .when()
                .put("{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalToIgnoringCase(restauranteFromBd.getNome()));


    }

    @Test
    @DisplayName("Testa findAll deve retornar 200 OK se size for igual a qtdRegistrosBanco")
    void findAll() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("", hasSize((int) qtdRegistrosBanco));

    }

}