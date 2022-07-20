package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroFotoProdutoService {

    private ProdutoRepository repository;

    @Transactional
    public FotoProduto save(FotoProduto fotoProduto) {

        var foto = repository
                .findFotoById(fotoProduto.getProduto().getRestaurante().getId(),
                        fotoProduto.getProduto().getId());

       foto.ifPresent(f -> repository.deleteFotoProduto(f));

        return repository.saveFotoProduto(fotoProduto);

    }
}


