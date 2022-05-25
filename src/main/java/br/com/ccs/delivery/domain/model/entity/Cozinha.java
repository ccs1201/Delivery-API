package br.com.ccs.delivery.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
@Getter
@Setter
@Entity
public class Cozinha extends AbstractEntity {

    @EqualsAndHashCode.Include
    private String nome;
}
