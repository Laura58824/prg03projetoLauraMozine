package br.com.ifba.gestaoanimal.registrosaude.controller;

import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.registrosaude.service.RegistroSaudeIService;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RegistroSaudeController implements RegistroSaudeIController {

    @Autowired
    private RegistroSaudeIService registroSaudeService;

    public RegistroSaude save(RegistroSaude registroSaude) {
        return registroSaudeService.save(registroSaude);
    }

    public RegistroSaude update(RegistroSaude registroSaude) {
        return registroSaudeService.update(registroSaude);
    }

    public void delete(Long id) {
        registroSaudeService.delete(id);
    }

    public List<RegistroSaude> findAll() {
        return registroSaudeService.findAll();
    }

    public RegistroSaude findById(Long id) {
        return registroSaudeService.findById(id);
    }

    public List<RegistroSaude> findByAnimalId(Long animalId) {
        return registroSaudeService.findByAnimalId(animalId);
    }

    public List<RegistroSaude> findByTipo(TipoProcedimentoEnum tipo) {
        return registroSaudeService.findByTipo(tipo);
    }
}