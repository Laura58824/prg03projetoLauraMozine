package br.com.ifba.gestaoanimal.ocorrencia.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ocorrencia")

public class Ocorrencia extends PersistenceEntity {

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoOcorrenciaEnum tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "urgencia", nullable = false)
    private UrgenciaEnum urgencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOcorrenciaEnum status;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(name = "data_atendimento")
    private LocalDateTime dataAtendimento;

    @ManyToOne
    @JoinColumn(name = "registrada_por_id", nullable = false)
    private Pessoa registradaPor;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Pessoa voluntario;

}