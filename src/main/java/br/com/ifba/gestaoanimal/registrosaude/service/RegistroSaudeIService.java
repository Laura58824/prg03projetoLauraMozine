package br.com.ifba.gestaoanimal.registrosaude.service;

import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;

import java.util.List;

public interface RegistroSaudeIService {
    RegistroSaude save(RegistroSaude registroSaude);
    RegistroSaude update(RegistroSaude registroSaude);
    void delete(Long id);
    List<RegistroSaude> findAll();
    RegistroSaude findById(Long id);
    List<RegistroSaude> findByAnimalId(Long animalId);
    List<RegistroSaude> findByTipo(TipoProcedimentoEnum tipo);
}