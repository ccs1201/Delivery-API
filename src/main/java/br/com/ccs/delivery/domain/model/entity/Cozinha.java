package br.com.ccs.delivery.domain.model.entity;

import br.com.ccs.delivery.core.validations.validationgroups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@JsonRootName("cozinha")
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.CozinhaId.class)
    private Long id;

    //@JsonProperty(value = "Categoria Cozinha")
    @Column(unique = true, nullable = false)
    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "cozinha", fetch = FetchType.LAZY)
    private Collection<Restaurante> restaurantes;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return Objects.equals(id, cozinha.id) && Objects.equals(nome, cozinha.nome) && Objects.equals(restaurantes, cozinha.restaurantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, restaurantes);
    }
}
