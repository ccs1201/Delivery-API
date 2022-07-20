package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class CadastroFotoProdutoService {

    private ProdutoRepository repository;
    private FotoStorageService fotoStorageService;

    /**
     * Salva os dados da foto produto no banco de dados
     * e grava o arquivo da foto no local de armazenamento
     * definido no application.properties em
     * delivery-api.storage.local.fotos
     *
     * @param fotoProduto {@link FotoProduto} entidade que será persistida
     * @param fileStream  o {@link InputStream} da foto recebida no PUT
     * @return {@link FotoProduto}
     */
    @Transactional
    public FotoProduto save(FotoProduto fotoProduto, InputStream fileStream) {

        //Busca no banco a foto já cadastrada caso exista
        var foto = repository
                .findFotoById(fotoProduto.getProduto().getRestaurante().getId(),
                        fotoProduto.getProduto().getId());

        //Se existir uma foto então temos que remover o
        //registro do banco de dados e o arquivo da foto anterior
        //do armazenamento
        foto.ifPresent(f -> {
            repository.deleteFotoProduto(f);
            fotoStorageService.delete(f.getNomeArquivo());
        });

        //seta o nome do arquivo com UUID para armazenar
        fotoProduto.setNomeArquivo(
                fotoStorageService.generateUuidFileName(
                        fotoProduto.getNomeArquivo()));

        //salva a entidade no banco de dados
        fotoProduto = repository.saveFotoProduto(fotoProduto);
        repository.flush();

        //grava o arquivo da foto no armazenamento
        fotoStorageService.store(fileStream, fotoProduto);

        return fotoProduto;
    }
}


