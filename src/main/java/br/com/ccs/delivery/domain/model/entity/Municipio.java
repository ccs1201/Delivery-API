package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.domain.model.util.validationgroups.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.MunicipioId.class)
    private Integer id;
    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    @Valid
    @ConvertGroup(to=ValidationGroups.EstadoId.class)
    private Estado estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipio municipio = (Municipio) o;
        return Objects.equals(id, municipio.id) && Objects.equals(nome, municipio.nome) && Objects.equals(estado, municipio.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, estado);
    }
}
