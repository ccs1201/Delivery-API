package br.com.ccs.delivery.domain.model.util;

import br.com.ccs.delivery.domain.model.util.exception.GenericEntityUpdateMergerUtilException;
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
 * e seta-los nos atributos das nossas Entities(Object Models).
 *
 * @author Cleber Souza
 * @version 0.2
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
     * e devolvendo-o atualizado com os novos valores.
     *
     * @param valuesFromRequest Map com os atributos e valores vindos da view.
     * @param entityToUpdate    Entidade que terá seus atributos atualizados.
     * @param entityClass       Classe da entidade a ser atualizada.
     * @param <ENTITY>          Tipo da entidade que será retornada, o mesmo de @param entityClass.
     */
    public <ENTITY> ENTITY updateModel(Map<String, Object> valuesFromRequest, ENTITY entityToUpdate, Class<ENTITY> entityClass){



            //converte os valores da view para os tipos corretos da entity
            Object entityMapper = mapper.convertValue(valuesFromRequest, entityClass);

            //percorre os valore do map um de cada vez
            valuesFromRequest.forEach((field, value) -> {

                /*
                 *  Encontra o atributo na entityToUpdate
                 *  e muda sua acessibilidade para "publica".
                 */
                Field entityField = ReflectionUtils.findField(entityClass, field);

                /*
                 * Garantimos o tratamento de exceções caso aconteça
                 * alguma ao tentarmos mudar a acessibilidade da field.
                 */
                try {
                    entityField.setAccessible(true);
                } catch (InaccessibleObjectException | SecurityException | NullPointerException e) {
                        throw new GenericEntityUpdateMergerUtilException(
                                String.format("impossível modificar ou acessar o atributo %s, da classe %s. \nDetalhes:\n %s ",
                                        field, entityClass.getSimpleName(), e.getMessage()));

                }

                /*
                 * Pega o valor do atributo no Map valuesFromRequest
                 *      Exemplo:
                 *          se o Map tem um atributo "nome" : "ABCDE";
                 *          valueFromMap recebe "ABCDE".
                 *
                 */
                Object valueFromMap = ReflectionUtils.getField(entityField, entityMapper);

                /*
                 * Encontra o atributo na entity de destino
                 * declarado em "Field entityField" e
                 * seta o valor do "valueFromMap" na entity.
                 *      Exemplo:
                 *          Supondo que o field/atributo seja
                 *          "nome" e o valor do valueFromMap "ABCDE"
                 *          pega o atributo "nome" na entityToUpdate
                 *          e seta o valor "ABCDE".
                 */
                ReflectionUtils.setField(entityField, entityToUpdate, valueFromMap);
            });

        return entityToUpdate;
    }
}
