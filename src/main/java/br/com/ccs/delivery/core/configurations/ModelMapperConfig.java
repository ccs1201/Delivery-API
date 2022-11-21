package br.com.ccs.delivery.core.configurations;

import br.com.ccs.delivery.api.v2.model.input.CidadeInputV2;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Municipio.class)
                .addMappings( mapping -> mapping.skip(Municipio::setId));

        return modelMapper;
    }
}
