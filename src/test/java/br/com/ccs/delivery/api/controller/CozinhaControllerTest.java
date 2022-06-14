package br.com.ccs.delivery.api.controller;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CozinhaControllerTest {

    private static final String BASE_URI = "/api/cozinhas/";
    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Testa findAll deve retornar 200 OK")
    void findAll() {
        given()
                .basePath(BASE_URI)
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("size()", greaterThan(0));

    }

    @Test
    void findByNome() {

        enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath(BASE_URI+"nome")
                .port(port)
                .param("nome", "italiana")
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", containsStringIgnoringCase("italiana"));
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getFirst() {
    }
}