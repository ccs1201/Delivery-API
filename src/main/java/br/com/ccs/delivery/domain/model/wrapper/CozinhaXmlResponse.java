package br.com.ccs.delivery.domain.model.wrapper;

import br.com.ccs.delivery.domain.model.entity.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NonNull;

import java.util.Collection;

@JsonRootName("cozinhas")
@Data
public class CozinhaXmlResponse {

    @NonNull
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Collection<Cozinha> cozinhas;





}
