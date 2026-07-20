package br.com.ifba.gestaoanimal.ocorrencia.controller;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.ocorrencia.service.OcorrenciaIService;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OcorrenciaController implements OcorrenciaIController {

    @Autowired
    private OcorrenciaIService ocorrenciaService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public Ocorrencia save(Ocorrencia ocorrencia) {
        Ocorrencia salva = ocorrenciaService.save(ocorrencia);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Registrou ocorrência (ID " + salva.getId() + ") - tipo: " + salva.getTipo());
        return salva;
    }

    @Override
    public Ocorrencia update(Ocorrencia ocorrencia) {
        Ocorrencia atualizada = ocorrenciaService.update(ocorrencia);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou ocorrência (ID " + atualizada.getId() + ") - status: " + atualizada.getStatus());
        return atualizada;
    }

    @Override
    public void delete(Long id) {
        ocorrenciaService.delete(id);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Desativou ocorrência (ID " + id + ")");
    }

    @Override
    public List<Ocorrencia> findAll() {
        return ocorrenciaService.findAll();
    }

    @Override
    public Ocorrencia findById(Long id) {
        return ocorrenciaService.findById(id);
    }

    @Override
    public List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status) {
        return ocorrenciaService.findByStatus(status);
    }

    @Override
    public List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo) {
        return ocorrenciaService.findByTipo(tipo);
    }
}