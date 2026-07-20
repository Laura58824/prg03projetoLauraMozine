package br.com.ifba.gestaoanimal.adocao.controller;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.adocao.service.AdocaoIService;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class AdocaoController implements AdocaoIController {

    @Autowired
    private AdocaoIService adocaoService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public Adocao save(Adocao adocao) {
        Adocao salva = adocaoService.save(adocao);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Cadastrou adoção (ID " + salva.getId() + ")");
        return salva;
    }

    @Override
    public Adocao update(Adocao adocao) {
        Adocao atualizada = adocaoService.update(adocao);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou adoção (ID " + atualizada.getId() + ") - status: " + atualizada.getStatus());
        return atualizada;
    }

    @Override
    public void delete(Long id) {
        adocaoService.delete(id);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Desativou adoção (ID " + id + ")");
    }

    @Override
    public List<Adocao> findAll() {
        return adocaoService.findAll();
    }

    @Override
    public Adocao findById(Long id) {
        return adocaoService.findById(id);
    }

    @Override
    public List<Adocao> findByStatus(StatusAdocaoEnum status) {
        return adocaoService.findByStatus(status);
    }
}