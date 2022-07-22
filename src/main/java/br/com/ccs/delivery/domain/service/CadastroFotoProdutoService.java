package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.entity.FotoProduto;
import br.com.ccs.delivery.domain.repository.ProdutoRepository;
import br.com.ccs.delivery.domain.service.exception.RepositoryEntityNotFoundException;
import br.com.ccs.delivery.domain.service.storage.s3.S3FotoStorageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
public class CadastroFotoProdutoService {

    private ProdutoRepository repository;
    //    @StorageQualifier(StorageServiceQualifierType.AMAZON_S3)
    //    private FotoStorageService fotoStorageService;

    private S3FotoStorageImpl fotoStorageService;

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

        //Se existir uma foto então temos que remover o
        //registro do banco de dados e o arquivo da foto anterior
        //do armazenamento
        this.checkIfExists(fotoProduto);

        //seta o nome do arquivo com UUID para armazenar
        fotoProduto.setNomeArquivo(fotoStorageService.generateUuidFileName(fotoProduto.getNomeArquivo()));

        //salva a entidade no banco de dados
        fotoProduto = repository.saveFotoProduto(fotoProduto);
        repository.flush();

        //grava o arquivo da foto no armazenamento
        fotoStorageService.store(fileStream, fotoProduto);

        return fotoProduto;
    }

    /**
     * Verifica se uma entidade {@link FotoProduto} já esta cadastrada
     * no banco de dados.
     * <p>
     * Caso exista remove o arquivo da foto do armazenamento
     * e seta o id da {@link FotoProduto} existente no fotoProduto
     * recebido como parâmetro.
     *
     * @param fotoProduto {@link FotoProduto} entidade que será verificada
     */
    private void checkIfExists(FotoProduto fotoProduto) {

        //Busca no banco a foto já cadastrada caso exista
        var foto = repository.findFotoById(fotoProduto.getProduto().getRestaurante().getId(), fotoProduto.getProduto().getId());

        //caso exista seta o id na nova entidade a ser persistida
        //para fazer um update
        foto.ifPresent(f -> {
            fotoProduto.setProdutoId(f.getProdutoId());
            fotoStorageService.delete(f.getNomeArquivo());
        });
    }

    public FotoProduto findFotoProduto(Long restauranteId, Long produtoId) {

        return repository.findFotoById(restauranteId, produtoId).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Nenhuma foto encontrada para o Produto ID: %d", produtoId)));
    }

    public InputStream getFotoFromStorage(FotoProduto fotoProduto) {

        return fotoStorageService.getFileFromStorage(fotoProduto.getNomeArquivo());
    }

    @Transactional
    public void deleteFotoProduto(Long restauranteId, Long produtoId) {

        var foto = this.findFotoProduto(restauranteId, produtoId);
        repository.deleteFotoProduto(foto);
        fotoStorageService.delete(foto.getNomeArquivo());

    }
}


