package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.core.validations.validationgroups.ValidationGroups;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(optional = false)
    //@NotNull
    //@Valid
    //@ConvertGroup(to=ValidationGroups.EstadoId.class)
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
