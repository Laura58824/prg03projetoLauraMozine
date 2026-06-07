package br.com.ifba.gestaoanimal.registrosaude.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "registro_saude")
public class RegistroSaude extends PersistenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProcedimentoEnum tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_realizacao", nullable = false)
    private LocalDate dataRealizacao;

    @Column(name = "data_proxima_dose")
    private LocalDate dataProximaDose;

    @Column(name = "responsavel")
    private String responsavel;

    @Column(name = "observacoes")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

}