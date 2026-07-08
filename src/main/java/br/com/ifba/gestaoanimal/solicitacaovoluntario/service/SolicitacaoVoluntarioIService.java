package br.com.ifba.gestaoanimal.solicitacaovoluntario.service;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import java.util.List;

public interface SolicitacaoVoluntarioIService {
    SolicitacaoVoluntario save(SolicitacaoVoluntario solicitacao);
    SolicitacaoVoluntario update(SolicitacaoVoluntario solicitacao);
    List<SolicitacaoVoluntario> findAll();
    List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status);
    SolicitacaoVoluntario findById(Long id);
}