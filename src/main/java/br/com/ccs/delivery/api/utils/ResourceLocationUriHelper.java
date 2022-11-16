package br.com.ccs.delivery.api.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Classe utilitaria para adicionar ao {@code Response Header}
 * a tag {@code Location} contendo a {@code URI} para um recurso
 * recem criado.</p>
 *
 * @author Cleber Souza
 * @version 1.0
 * @since 14/11/2022
 */

@UtilityClass
public class ResourceLocationUriHelper {

    public static void addUriToResponseHeader(Object resourceID) {

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(resourceID.toString())
                .toUri();

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();

        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }

    /**
     * <p>Retorna uma string contento o URI completa da requisição
     * incluído o path e suas varáveis.</p>
     *
     * @return String representado a URI
     */
    public static String getUri() {

        return ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
    }

    /**
     * <p>Retorna uma string contendo o contexto da API.</p>
     *
     * <p> Ex.</p>
     * <p><b>Request para:</b>
     * <br>
     * <p><i>https://meudominio.com/api/user/1</i></p>
     * <br>
     * <p><b>Retorna:</b>
     * <br>
     * <p><i>https://meudominio.com</i></p>
     * </p>
     * <br>
     * <br>
     *
     * @return String representando a URI
     */
    public static String getServletContext() {
        var context = ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString();

        return context;
    }
}
