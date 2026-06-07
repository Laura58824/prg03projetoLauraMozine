package br.com.ifba.gestaoanimal.doacao.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doacao")
public class Doacao extends PersistenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoDoacaoEnum tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "quantidade")
    private Double quantidade;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "doador_id", nullable = false)
    private Pessoa doador;

}