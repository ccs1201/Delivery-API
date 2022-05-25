package br.com.ccs.delivery.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Restaurante extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 60)
    private String nome;
    @NotNull
    @Min(0)
    private BigDecimal taxaEntrega;

}
