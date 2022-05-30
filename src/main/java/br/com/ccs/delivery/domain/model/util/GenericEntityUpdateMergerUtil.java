package br.com.ccs.delivery.domain.model.util;

import br.com.ccs.delivery.domain.util.exception.GenericEntityUpdateMergerUtilException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Map;

/**
 * Está classe é responsável por converter
 * os atributos atualizados na view
 * e seta-los nos atributos das nossas entities(Object Models)
 *
 * @author Cleber Souza
 * @version 0.1
 * @since 30/05/2022
 */

@Component
@AllArgsConstructor
public class GenericEntityUpdateMergerUtil {

    ObjectMapper mapper;

    /**
     * Converte os valores do Map (capturado na request da api)
     * nos tipos específicos dos atributos da Entity. Depois
     * faz o update/merge dos valores convertidos na Entity
     * recebida como parâmetro, atualizando nosso model Entity
     * e devolvendo-o atualizado com os valores.
     *
     * @param valuesFromView Map com os atributos e valores vindos da view.
     * @param entity Entidade que terá seus atributos atualizados.
     * @param entityClass Classe da entidade a ser atualizada.
     * @param <ENTITY> Tipo da entidade que será retornada, o mesmo de @param entityClass.
     */
    public <ENTITY> ENTITY updateModel(Map<String, Object> valuesFromView, ENTITY entity, Class<ENTITY> entityClass) {

        //converte os valores da view para os tipos corretos da entity
        Object entityMapper = mapper.convertValue(valuesFromView, entityClass);

        //percorre os valore do map um de cada vez
        valuesFromView.forEach((attribute, value) -> {

            /*
             *  Encontra o atributo na entity de destino
             *  e muda sua acessibilidade para "publica"
             */
            Field entityAttribute = ReflectionUtils.findField(entityClass, attribute);

            try {
                entityAttribute.setAccessible(true);
            } catch (InaccessibleObjectException | SecurityException | NullPointerException e){
                throw new GenericEntityUpdateMergerUtilException(
                        String.format("impossível modificar ou acessar o atributo %s, da classe %s. \nDetalhes:\n %s ",
                                entityAttribute.getName(), entityClass.getSimpleName(), e.getMessage()));
            }

            /*
             * Pega o valor do atributo no mapper
             *      Exemplo:
             *          se o mapper tem um atributo "nome" : "ABCDE";
             *          valueFromView recebe "ABCDE".
             *
             */
            Object valueFromMapper = ReflectionUtils.getField(entityAttribute, entityMapper);

            /*
             * Encontra o atributo na entity de destino
             * declarado em "Field entityAttribute" e
             * seta o valor do "valueFromMapper" na entity.
             *      Exemplo:
             *          Supondo que o field/atributo seja
             *          "nome" e o valor do mapper "ABCDE"
             *          pega o atributo "nome" na entity
             *          se seta o valor "ABCDE".
             */
            ReflectionUtils.setField(entityAttribute, entity, valueFromMapper);
        });

        return entity;
    }
}
