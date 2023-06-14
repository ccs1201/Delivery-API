package br.com.ccs.delivery.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SECRET = "QS1zyuuDIHklis01*n15%PLVLaR64@Xj";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .cors().and()
                .oauth2ResourceServer().jwt();
//                .oauth2ResourceServer().opaqueToken();
    }


//para uso com chaves simetricas
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        var secretkey = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(secretkey).build();
//    }
}
