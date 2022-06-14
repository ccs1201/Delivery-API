package br.com.ccs.delivery.api.controller;


import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.repository.CozinhaRepository;
import br.com.ccs.delivery.domain.service.CozinhaService;
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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
class CozinhaControllerTest {

    private static final String BASE_URI = "/api/cozinhas/";
    @LocalServerPort
    private int port;

    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaRepository repository;
    private long qtdRegistrosNoBanco;


    @BeforeAll
        //requer @TestInstance(TestInstance.Lifecycle.PER_CLASS) alternativamente pode utilizar a anotação @BeforeEach
    void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        basePath = BASE_URI;
        RestAssured.port = port;
        qtdRegistrosNoBanco = repository.count();
    }

    @Test
    @DisplayName("Testa findAll, deve retornar 200 OK")
    void findAll() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("", hasSize(((int) qtdRegistrosNoBanco)));
    }

    @Test
    @DisplayName("Testa FindByNome, deve retornar '200 OK' quando a cozinha é encontrada.")
    void findByNome() {

        given()
                .param("nome", "Indiana")
                .accept(ContentType.JSON)
                .when()
                .get("nome")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", hasItem("Indiana"));
    }

    @Test
    @DisplayName("Testa findById, Deve retornar '200 OK' se a cozinha ID 1 Existir")
    void findById() {
        given()
                .param("cozinhaId", 1)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", hasItem(1));
    }

    @Test
    @DisplayName("Testa findByIdInexistente, Deve retornar '404 NOT_FOUND' se a cozinha NÃO Existir")
    void findByIdInexistente() {
        given()
                .pathParam("cozinhaId", 30000000L)
                .accept(ContentType.JSON)
                .when()
                .get("{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Testa FindByNomeInexistente, deve retornar '404 NOT_FOUND' quando a cozinha NÃO Existir")
    void findByNomeInexistente() {

        given()
                .param("nome", "beaba")
                .accept(ContentType.JSON)
                .when()
                .get("nome")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }


    @Test
    @DisplayName("Testa save, deve retornar '201 CREATED' quando cadastrar uma cozinha")
    void save() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"nome\" : \"legal\"}")
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Testa Update, deve retornar 200 OK se update foi executado com sucesso")
    void update() {

        Cozinha cozinha = cozinhaService.findByNomeContaining("italiana").stream().findFirst().get();

        cozinha.setNome("Italiana Updated");

        given()
                .contentType(ContentType.JSON)
                .body(cozinha)
                .when()
                .put("" + cozinha.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalToIgnoringCase("italiana Updated"));
    }

    @Test
    @DisplayName("Testa Delete, deve retornar '409 CONFLICT' pois todas as cozinhas estão em uso")
    void delete() {

        Cozinha cozinha = cozinhaService.findByNomeContaining("alienigena").stream().findFirst().get();
        given()
                .when()
                .delete("" + cozinha.getId())
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    @DisplayName("Testa getFirst, deve retornar '200 OK' e body deve ter a propriedade 'nome'")
    void getFirst() {
        given()
                .when()
                .get("first")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("", hasItems());
    }
}