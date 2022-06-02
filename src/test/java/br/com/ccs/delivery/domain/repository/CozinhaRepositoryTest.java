package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Profile("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CozinhaRepositoryTest {

    @Autowired
    CozinhaRepository repository;
    private static Cozinha persisted;

    @Test
    @Order(0)
    public void save() {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Nova Cozinha");
        persisted = repository.save(cozinha);

        assertEquals(cozinha.getNome(), persisted.getNome());
        System.out.println(cozinha.getNome());

    }

    @Test
    @Order(1)
    public void update() {
        persisted.setNome(persisted.getId() + " Alterada");
        Cozinha updated = repository.save(persisted);

        assertEquals(persisted.getNome(), updated.getNome());
        System.out.println(updated.getNome());
        persisted = updated;
    }

    @Test
    @Order(2)
    public void getAll() {

        Collection<Cozinha> cozinhas = repository.findAll();
        cozinhas.forEach(cozinha -> System.out.printf("ID: %d Nome Cozinha: %s \n", cozinha.getId(), cozinha.getNome()));

    }

    @Test
    @Order(3)
    public void remove() {
        repository.deleteById(persisted.getId());
        Cozinha cozinha = null;

        Optional<Cozinha> cozinhaOptional = repository.findById(persisted.getId());

        if (cozinhaOptional.isPresent()) {
            cozinha = cozinhaOptional.get();

        }
        assertNull(cozinha);

    }

}