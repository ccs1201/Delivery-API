package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.core.validations.exceptions.EntityValidationException;
import br.com.ccs.delivery.domain.model.entity.Restaurante;
import br.com.ccs.delivery.domain.model.util.GenericEntityUpdateMergerUtil;
import br.com.ccs.delivery.domain.repository.RestauranteRepository;
import br.com.ccs.delivery.domain.repository.specification.RestauranteComFreteGratisSpec;
import br.com.ccs.delivery.domain.repository.specification.RestauranteNomeLikeSpec;
import br.com.ccs.delivery.domain.service.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class RestauranteService {
    private static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante ID: %d não encontrado.";
    private static final String ERRO_CADASTRAR_RESTAURANTE = "Erro ao cadastrar Restaurante.";
    private static final String ERRO_ATUALIZAR_RESTAURANTE = "Erro ao atualizar Restaurante. ";
    private static final String RESTAURANTE_USO = "Não é possível remover o Restaurante ID: %d pois esta em uso";
    private static final String TIPO_PAGAMENTO_NAO_ENCONTRADO = "Tipo de Pagamento ID: %d não encontrado para o Restaurante ID: %d";
    private final RestauranteRepository repository;

    private final MunicipioService municipioService;

    private final CozinhaService cozinhaService;
    private final GenericEntityUpdateMergerUtil entityUpdateMergerUtil;
    private final SmartValidator smartValidator;


    public Collection<Restaurante> findAll() {
        return repository.findAllEager();
    }

    public Restaurante findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RepositoryEntityNotFoundException(
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
            return repository.save(restaurante);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryDataIntegrityViolationException(
                    ERRO_CADASTRAR_RESTAURANTE, e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityPersistException(
                    ERRO_CADASTRAR_RESTAURANTE + e.getMessage());
        }
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

    public Restaurante findTiposPagamentoRestaurante(Long restauranteId) {

        return repository.findComTiposPagamento(restauranteId);
    }

    @Transactional
    public void deleteTipoPagamento(Long restauranteId, Long tipoPagamentoId) {
        Restaurante restaurante = repository.findByTiposPagamentoIs(restauranteId, tipoPagamentoId);
        if (restaurante == null) {
            throw new ServiceException(
                    String.format(TIPO_PAGAMENTO_NAO_ENCONTRADO, tipoPagamentoId, restauranteId));
        }
        repository.deleteTipoPagamentoByIdFromRestauranteId(restauranteId, tipoPagamentoId);
    }
}
