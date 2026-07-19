package br.com.ifba.gestaoanimal.solicitacaovoluntario.service;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import java.util.List;

public interface SolicitacaoVoluntarioIService {
    SolicitacaoVoluntario save(SolicitacaoVoluntario solicitacao);
    SolicitacaoVoluntario update(SolicitacaoVoluntario solicitacao);
    List<SolicitacaoVoluntario> findAll();
    List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status);
    SolicitacaoVoluntario findById(Long id);
    SolicitacaoVoluntario aprovar(Long id, Pessoa analisadoPor, String observacaoAdmin);
    SolicitacaoVoluntario recusar(Long id, Pessoa analisadoPor, String observacaoAdmin);
}