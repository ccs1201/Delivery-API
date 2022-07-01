package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.repository.PedidoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository repository;

    public Collection<Pedido> findAllEager() {
        return repository.findAllEager();
    }

    public Pedido findById(Long id) {
        return repository.findByIdEager(id).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Pedido ID: %d não encontrado.", id)
                )
        );
    }
}
