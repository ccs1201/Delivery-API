package br.com.ccs.delivery.domain.service;

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

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class RestauranteService {

    public static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante ID: %d não encontrado.";
    public static final String ERRO_CADASTRAR_RESTAURANTE = "Erro ao cadastrar Restaurante.";
    public static final String ERRO_ATUALIZAR_RESTAURANTE = "Erro ao atualizar Restaurante.";
    public static final String RESTAURANTE_USO = "Não é possível remover o Restaurante ID: %d pois esta em uso";
    RestauranteRepository repository;
    GenericEntityUpdateMergerUtil entityUpdateMergerUtil;

    public Collection<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RepositoryEntityNotFoundException(
                String.format(RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void delete(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);

        } catch (DataIntegrityViolationException e) {
            throw new RepositoryEntityInUseException(
                    String.format(RESTAURANTE_USO, restauranteId), e);
        } catch (IllegalArgumentException e) {
            throw new RepositoryEntityNotFoundException(
                    String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId));
        }
    }


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
    public Restaurante update(Long id, Map<String, Object> updates) {
        try {

            Restaurante restaurante = this.findById(id);

            //BeanUtils.copyProperties(restaurante,oldRestaurante,"id", "tiposPagamento", "taxaEntrega");

            entityUpdateMergerUtil.updateModel(updates, restaurante, Restaurante.class);

            return repository.save(restaurante);

        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            throw new RepositoryEntityPersistException(
                    ERRO_ATUALIZAR_RESTAURANTE, e);
        }
    }

    @Transactional
    public Restaurante patchUpdate(Long id, Map<String, Object> updates) {

        try {

            Restaurante restaurante = this.findById(id);
            entityUpdateMergerUtil.updateModel(updates, restaurante, Restaurante.class);

            return repository.save(restaurante);

        } catch (IllegalArgumentException | DataIntegrityViolationException | EmptyResultDataAccessException e) {
            throw new RepositoryEntityUpdateException(ERRO_ATUALIZAR_RESTAURANTE, e);
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
}
