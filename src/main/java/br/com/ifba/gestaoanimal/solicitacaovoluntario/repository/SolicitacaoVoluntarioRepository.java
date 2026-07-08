package br.com.ifba.gestaoanimal.solicitacaovoluntario.repository;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoVoluntarioRepository extends JpaRepository<SolicitacaoVoluntario, Long> {
    List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status);
}