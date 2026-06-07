package br.com.ifba.gestaoanimal.adocao.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "adocao")
public class Adocao extends PersistenceEntity {

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAdocaoEnum status;

    @Column(name = "observacoes_entrevista")
    private String observacoesEntrevista;

    @Column(name = "motivo_recusa")
    private String motivoRecusa;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "adotante_id", nullable = false)
    private Pessoa adotante;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Pessoa responsavel;

}