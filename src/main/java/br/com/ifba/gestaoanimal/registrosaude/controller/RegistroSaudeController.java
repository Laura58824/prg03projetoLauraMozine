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

    @Override
    public RegistroSaude save(RegistroSaude registroSaude) {
        return registroSaudeService.save(registroSaude);
    }

    @Override
    public RegistroSaude update(RegistroSaude registroSaude) {
        return registroSaudeService.update(registroSaude);
    }

    @Override
    public void delete(Long id) {
        registroSaudeService.delete(id);
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