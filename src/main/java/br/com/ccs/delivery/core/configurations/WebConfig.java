package br.com.ccs.delivery.core.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //Libera tudo
        //registry.addMapping("/**");

        //Libera origens especificas
        registry.addMapping("/**")
                .allowedMethods("*");
                //.allowedOrigins("http://localhost:8081/")
                //.maxAge()
    }


    /**
     * <p>Configura o default MediaType quando estivermos utilizando
     * versionamento do API por custom MediaType</p>
     *
     * @param configurer
     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(ApiMediaTypes.V2_APPLICATION_JSON);
//    }
}
