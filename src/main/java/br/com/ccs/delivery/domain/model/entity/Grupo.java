package br.com.ccs.delivery.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Collection<Permissao> permissoes = new HashSet<>();

    public boolean removerPermissao(Permissao permissao) {
        return getPermissoes().remove(permissao);
    }

    public boolean addPermissao(Permissao permissao) {
        return getPermissoes().add(permissao);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return id.equals(grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, permissoes);
    }
}
