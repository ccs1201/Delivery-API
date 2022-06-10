package br.com.ccs.delivery.domain.repository;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Profile("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CozinhaRepositoryIT {

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
    @DisplayName("digo o que meu teste vai fazer e qual deveria ser o resultado")
    public void remove() {
        repository.deleteById(persisted.getId());

        Optional<Cozinha> cozinhaOptional = repository.findById(persisted.getId());

        assertThrows(NoSuchElementException.class, cozinhaOptional::get);
    }
}