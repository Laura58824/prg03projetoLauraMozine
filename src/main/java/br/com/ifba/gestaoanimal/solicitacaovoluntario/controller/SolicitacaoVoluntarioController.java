package br.com.ifba.gestaoanimal.solicitacaovoluntario.controller;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.service.SolicitacaoVoluntarioIService;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class SolicitacaoVoluntarioController implements SolicitacaoVoluntarioIController {

    @Autowired
    private SolicitacaoVoluntarioIService solicitacaoVoluntarioService;

    public SolicitacaoVoluntario save(SolicitacaoVoluntario solicitacao) {
        return solicitacaoVoluntarioService.save(solicitacao);
    }

    public SolicitacaoVoluntario update(SolicitacaoVoluntario solicitacao) {
        return solicitacaoVoluntarioService.update(solicitacao);
    }

    public List<SolicitacaoVoluntario> findAll() {
        return solicitacaoVoluntarioService.findAll();
    }

    public List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status) {
        return solicitacaoVoluntarioService.findByStatus(status);
    }

    public SolicitacaoVoluntario findById(Long id) {
        return solicitacaoVoluntarioService.findById(id);
    }

    @Override
    public SolicitacaoVoluntario aprovar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        return solicitacaoVoluntarioService.aprovar(id, analisadoPor, observacaoAdmin);
    }

    @Override
    public SolicitacaoVoluntario recusar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        return solicitacaoVoluntarioService.recusar(id, analisadoPor, observacaoAdmin);
    }
}