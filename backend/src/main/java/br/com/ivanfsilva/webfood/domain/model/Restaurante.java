package br.com.ivanfsilva.webfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;

    @ManyToOne
    private Cozinha cozinha;

}
