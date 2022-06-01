package br.com.ccs.delivery.domain.model.component;

import br.com.ccs.delivery.domain.model.entity.Municipio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
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
    private Integer cep;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
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
