package br.com.ccs.delivery.domain.model.component;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.core.validations.validationgroups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class Endereco {

    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotNull
    @Size(min = 8, max = 8, message = "Cep deve conter 8 dígitos")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    @JsonIgnore
    @NotNull
    @Valid
    @ConvertGroup(to = ValidationGroups.MunicipioId.class)
    private Municipio municipio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) && Objects.equals(complemento, endereco.complemento) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cep, endereco.cep) && Objects.equals(municipio, endereco.municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, numero, complemento, bairro, cep, municipio);
    }
}
