package br.com.ifba.gestaoanimal.solicitacaovoluntario.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "solicitacao_voluntario")
public class SolicitacaoVoluntario extends PersistenceEntity {

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao;

    @Column(name = "motivacao")
    private String motivacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacaoEnum status;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;

    @Column(name = "observacao_admin")
    private String observacaoAdmin;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Pessoa solicitante;

    @ManyToOne
    @JoinColumn(name = "analisado_por_id")
    private Pessoa analisadoPor;

}