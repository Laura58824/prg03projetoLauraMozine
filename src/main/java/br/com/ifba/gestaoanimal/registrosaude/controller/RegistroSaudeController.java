package br.com.ifba.gestaoanimal.registrosaude.controller;

import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.registrosaude.service.RegistroSaudeIService;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RegistroSaudeController implements RegistroSaudeIController {

    @Autowired
    private RegistroSaudeIService registroSaudeService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public RegistroSaude save(RegistroSaude registroSaude) {
        RegistroSaude salvo = registroSaudeService.save(registroSaude);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Cadastrou registro de saúde (ID " + salvo.getId() + ") - tipo: " + salvo.getTipo());
        return salvo;
    }

    @Override
    public RegistroSaude update(RegistroSaude registroSaude) {
        RegistroSaude atualizado = registroSaudeService.update(registroSaude);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou registro de saúde (ID " + atualizado.getId() + ")");
        return atualizado;
    }

    @Override
    public void delete(Long id) {
        registroSaudeService.delete(id);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Desativou registro de saúde (ID " + id + ")");
    }

    @Override
    public List<RegistroSaude> findAll() {
        return registroSaudeService.findAll();
    }

    @Override
    public RegistroSaude findById(Long id) {
        return registroSaudeService.findById(id);
    }

    @Override
    public List<RegistroSaude> findByAnimalId(Long animalId) {
        return registroSaudeService.findByAnimalId(animalId);
    }

    @Override
    public List<RegistroSaude> findByTipo(TipoProcedimentoEnum tipo) {
        return registroSaudeService.findByTipo(tipo);
    }
}