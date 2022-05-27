package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Estado;
import br.com.ccs.delivery.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class EstadoService {

    EstadoRepository repository;


    public Collection<Estado> findAll() {

        return repository.findAll();

    }

    public Estado findById(int estadoId) {
        return repository.findById(estadoId).get();
    }
}
