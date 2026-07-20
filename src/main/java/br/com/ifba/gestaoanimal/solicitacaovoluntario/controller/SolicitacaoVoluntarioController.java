package br.com.ifba.gestaoanimal.solicitacaovoluntario.controller;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.service.SolicitacaoVoluntarioIService;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class SolicitacaoVoluntarioController implements SolicitacaoVoluntarioIController {

    @Autowired
    private SolicitacaoVoluntarioIService solicitacaoVoluntarioService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public SolicitacaoVoluntario save(SolicitacaoVoluntario solicitacao) {
        SolicitacaoVoluntario salva = solicitacaoVoluntarioService.save(solicitacao);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Registrou solicitação de voluntariado (ID " + salva.getId() + ")");
        return salva;
    }

    @Override
    public SolicitacaoVoluntario update(SolicitacaoVoluntario solicitacao) {
        SolicitacaoVoluntario atualizada = solicitacaoVoluntarioService.update(solicitacao);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou solicitação de voluntariado (ID " + atualizada.getId() + ")");
        return atualizada;
    }

    @Override
    public List<SolicitacaoVoluntario> findAll() {
        return solicitacaoVoluntarioService.findAll();
    }

    @Override
    public List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status) {
        return solicitacaoVoluntarioService.findByStatus(status);
    }

    @Override
    public SolicitacaoVoluntario findById(Long id) {
        return solicitacaoVoluntarioService.findById(id);
    }

    @Override
    public SolicitacaoVoluntario aprovar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        SolicitacaoVoluntario aprovada = solicitacaoVoluntarioService.aprovar(id, analisadoPor, observacaoAdmin);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Aprovou solicitação de voluntariado (ID " + id + ")");
        return aprovada;
    }

    @Override
    public SolicitacaoVoluntario recusar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        SolicitacaoVoluntario recusada = solicitacaoVoluntarioService.recusar(id, analisadoPor, observacaoAdmin);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Recusou solicitação de voluntariado (ID " + id + ")");
        return recusada;
    }
}