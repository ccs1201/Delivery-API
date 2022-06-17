package br.com.ccs.delivery.core.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        return new ModelMapper();
    }
}
