package br.com.ccs.delivery.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class TipoPagamentoServiceTest {

    @Autowired
    private TipoPagamentoService service;
    @Test
    void getLastUpdate() {
        System.out.println(service.getLastUpdate());
    }
}