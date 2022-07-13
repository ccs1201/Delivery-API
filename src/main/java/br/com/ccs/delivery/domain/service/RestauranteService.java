package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.core.validations.exceptions.EntityValidationException;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.entity.TipoPagamento;
import br.com.ccs.delivery.domain.model.entity.Usuario;
import br.com.ccs.delivery.domain.model.util.GenericEntityUpdateMergerUtil;
import br.com.ccs.delivery.domain.repository.RestauranteRepository;
import br.com.ccs.delivery.domain.model.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.model.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RestauranteService {
    private static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante ID: %d não encontrado.";
    private static final String ERRO_CADASTRAR_RESTAURANTE = "Erro ao cadastrar Restaurante.";
    private static final String ERRO_ATUALIZAR_RESTAURANTE = "Erro ao atualizar Restaurante. ";
    private static final String RESTAURANTE_USO = "Não é possível remover o Restaurante ID: %d pois esta em uso";
    private static final String TIPO_PAGAMENTO_NAO_ENCONTRADO = "Tipo de Pagamento: %s não encontrado para o Restaurante: %s";
    private static final String TIPO_PAGAMENTO_JA_CADASTRADO = "Tipo de Pagamento: %s, já cadastrado para o Restaurante: %s";
    private final RestauranteRepository repository;
    private final MunicipioService municipioService;
    private final TipoPagamentoService tipoPagamentoService;
    private final CozinhaService cozinhaService;
    private final GenericEntityUpdateMergerUtil entityUpdateMergerUtil;
    private final SmartValidator smartValidator;

    private final UsuarioService usuarioService;


    public Collection<Restaurante> findAll() {
        return repository.findAllEager();
    }

    public Page<Restaurante> findAll(Pageable pageable) {
        return repository.findAllEagerPageable(pageable);
    }

    public Restaurante findById(Long id) {

        return repository.findByIdEager(id).orElseThrow(() -> new RepositoryEntityNotFoundException(
                String.format(RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void delete(Long restauranteId) {

        try {
            repository.deleteById(restauranteId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(
                    String.format(RESTAURANTE_USO, restauranteId), e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityNotFoundException(
                    String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId));
        }
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {

        try {
            this.validarDadosRestaurante(restaurante);
            return repository.save(restaurante);

        } catch (RepositoryEntityNotFoundException | DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    ERRO_CADASTRAR_RESTAURANTE, e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(
                    ERRO_CADASTRAR_RESTAURANTE + e.getMessage());
        }
    }

    private void validarDadosRestaurante(Restaurante restaurante) {
        this.validarCozinha(restaurante);
        this.validarMunicipio(restaurante);

    }

    private void validarMunicipio(Restaurante restaurante) {
        restaurante.getEndereco().setMunicipio(
                municipioService.findById(restaurante.getEndereco().getMunicipio().getId())
        );
    }

    private void validarCozinha(Restaurante restaurante) {
        restaurante.setCozinha(cozinhaService
                .findById(restaurante.getCozinha().getId()));
    }

    @Transactional
    public Restaurante update(Restaurante restaurante) {
        try {

            cozinhaService.findById(restaurante.getCozinha().getId());
            municipioService.findById(restaurante.getEndereco().getMunicipio().getId());


            return repository.saveAndFlush(restaurante);

        } catch (RepositoryEntityNotFoundException e) {
            throw new ServiceException(ERRO_ATUALIZAR_RESTAURANTE + e.getMessage(), e);

        } catch (IllegalArgumentException | DataIntegrityViolationException | ConstraintViolationException e) {
            throw new RepositoryEntityPersistException(
                    ERRO_ATUALIZAR_RESTAURANTE, e);
        }
    }

    @Transactional
    public Restaurante patchUpdate(Long id, Map<String, Object> updates) {

        try {

            Restaurante restaurante = this.findById(id);
            entityUpdateMergerUtil.updateModel(updates, restaurante, Restaurante.class);

            Validate(restaurante);

            return repository.saveAndFlush(restaurante);

        } catch (IllegalArgumentException | DataIntegrityViolationException | EmptyResultDataAccessException e) {
            throw new RepositoryEntityUpdateException(ERRO_ATUALIZAR_RESTAURANTE, e);
        }
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = this.findById(restauranteId);
        restaurante.ativar();
        repository.saveAndFlush(restaurante);
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = this.findById(restauranteId);
        restaurante.inativar();
        repository.saveAndFlush(restaurante);
    }

    @Transactional
    public void ativar(List<Long> restauranteIds) {
        try {
            restauranteIds.forEach(id -> this.findById(id).ativar());
            repository.flush();
        } catch (RepositoryEntityNotFoundException e) {
            throw new RepositoryEntityUpdateException(e.getMessage());
        }
    }

    @Transactional
    public void inativar(List<Long> restauranteIds) {
        try {
            restauranteIds.forEach(id -> this.findById(id).inativar());
            repository.flush();
        } catch (RepositoryEntityNotFoundException e) {
            throw new RepositoryEntityUpdateException(e.getMessage());
        }
    }

    private void Validate(Restaurante restaurante) {

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, restaurante.getClass().getSimpleName());

        smartValidator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new EntityValidationException(bindingResult);
        }

    }

    public Collection<Restaurante> findByNomeCozinha(String nomeCozinha) {
        return repository.findByNomeCozinha(nomeCozinha);
    }

    public Collection<Restaurante> anyCriteria(String nome, BigDecimal taxaEntregaMin, BigDecimal taxaEntregaMax, String nomeCozinha) {
        return repository.anyCriteria(nome, taxaEntregaMin, taxaEntregaMax, nomeCozinha);
    }

    public Collection<Restaurante> findAll(RestauranteComFreteGratisSpec comFreteGratis, RestauranteNomeLikeSpec comNomeSemelhante) {

        return repository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    public Collection<Restaurante> findAll(String nomeRestaurante, String nomeCozinha) {
        return repository.comFreteGratisAndNomeAndCozinhaNome(nomeRestaurante, nomeCozinha);
    }

    public Restaurante getFirst() {
        return repository
                .getFirstOccurrence()
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível localizar a primeira ocorrência de restaurante."));
    }

    public Restaurante findComTiposPagamento(Long restauranteId) {

        checkIfRestauranteExists(restauranteId);

        return repository.findComTiposPagamento(restauranteId).orElseThrow(
                () ->
                        new RestauranteTipoPagamentoNaoEncontradoException(
                                String.format("Nenhum tipo de pagamento cadastrado para o restaurante: %d", restauranteId)));
    }

    @Transactional
    public void deleteTipoPagamento(Restaurante restaurante, Long tipoPagamentoId) {

        TipoPagamento tipoPagamento = tipoPagamentoService.findById(tipoPagamentoId);

        if (!restaurante.getTiposPagamento().contains(tipoPagamento)) {
            throw new RestauranteTipoPagamentoNaoEncontradoException(
                    String.format(TIPO_PAGAMENTO_NAO_ENCONTRADO, tipoPagamento.getNome(), restaurante.getNome()));
        }
        repository.deleteTipoPagamentoByIdFromRestauranteId(restaurante.getId(), tipoPagamentoId);
        repository.flush();
    }

    @Transactional
    public Restaurante addTipoPagamento(Restaurante restaurante, Long tipoPagamentoId) {
        TipoPagamento tipoPagamento = tipoPagamentoService.findById(tipoPagamentoId);

        restaurante.getTiposPagamento().add(tipoPagamento);

        try {
            return repository.saveAndFlush(restaurante);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format(TIPO_PAGAMENTO_JA_CADASTRADO, tipoPagamento.getNome(), restaurante.getNome()));
        }
    }

    public Restaurante findProdutos(Long restauranteId, Boolean ativos) {

        checkIfRestauranteExists(restauranteId);

        return repository.findProdutos(restauranteId, ativos)
                .orElseThrow(() -> new RepositoryEntityNotFoundException(
                        String.format("Nenhum Produto encontrado para o restaurante: %d.", restauranteId)));

    }

    public Restaurante findProdutos(Long restauranteId) {

        return repository.findProdutos(restauranteId)
                .orElseThrow(() -> new RepositoryEntityNotFoundException(
                        String.format("Nenhum Produto encontrado para o restaurante: %d.", restauranteId)));

    }

    private void checkIfRestauranteExists(Long restauranteId) {
        repository.findById(restauranteId)
                .orElseThrow(() -> new RepositoryEntityNotFoundException(
                        String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId)
                ));
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = this.findById(restauranteId);
        restaurante.abrir();
        repository.saveAndFlush(restaurante);
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = this.findById(restauranteId);
        restaurante.fechar();
        repository.saveAndFlush(restaurante);
    }

    public Restaurante findUsuarios(Long restauranteId) {

        this.findById(restauranteId);

        return repository.findUsuarios(restauranteId).orElseThrow(() ->
                new RepositoryEntityNotFoundException(
                        String.format("Nenhum Usuário encontrado para o Restaurante ID: %d", restauranteId)
                ));
    }

    @Transactional
    public void addUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = this.findById(restauranteId);

        Usuario usuario = usuarioService.findaById(usuarioId);

        restaurante.addUsuario(usuario);
        try {
            repository.saveAndFlush(restaurante);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    String.format("Usuario: %d-%s já cadastrado para o Restaurante: %d-%s.",
                            usuario.getId(), usuario.getNome(), restaurante.getId(), restaurante.getNome())
            );
        }
    }

    @Transactional
    public void removeUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = this.findById(restauranteId);

        Usuario usuario = usuarioService.findaById(usuarioId);

        restaurante.removeUsuario(usuario);
        repository.saveAndFlush(restaurante);
    }
}
