package br.com.ccs.delivery.core.configurations;

import br.com.ccs.delivery.api.v2.model.input.CidadeInputV2;
import br.com.ccs.delivery.api.v2.model.response.CidadeResponseV2;
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

        //ignora o ID do municipio na conversão
        modelMapper.createTypeMap(CidadeInputV2.class, Municipio.class)
                .addMappings( mapping -> mapping.skip(Municipio::setId));

        //mapeia os atributos de Municipio para CidadeResponseV2
        modelMapper.createTypeMap(Municipio.class, CidadeResponseV2.class)
                .addMappings(mapping -> {
                    mapping.map(Municipio::getId, CidadeResponseV2::setIdCidade);
                    mapping.map(Municipio::getNome, CidadeResponseV2::setNomeCidade);
                        });


        return modelMapper;
    }
}