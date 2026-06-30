package br.com.ifba.gestaoanimal.registrosaude.service;

import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import br.com.ifba.gestaoanimal.registrosaude.repository.RegistroSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroSaudeService implements RegistroSaudeIService {

    @Autowired
    private RegistroSaudeRepository registroSaudeRepository;

    @Override
    public RegistroSaude save(RegistroSaude registroSaude) {
        if (registroSaude == null)
            throw new IllegalArgumentException("RegistroSaude nao pode ser nulo.");
        if (registroSaude.getId() != null)
            throw new IllegalArgumentException("RegistroSaude novo nao deve ter ID.");
        if (registroSaude.getAnimal() == null)
            throw new IllegalArgumentException("Animal e obrigatorio.");
        if (registroSaude.getTipo() == null)
            throw new IllegalArgumentException("Tipo de procedimento e obrigatorio.");
        if (registroSaude.getDataRealizacao() == null)
            registroSaude.setDataRealizacao(LocalDate.now());
        if (registroSaude.getDataProximaDose() != null
                && registroSaude.getDataProximaDose().isBefore(registroSaude.getDataRealizacao()))
            throw new IllegalArgumentException("Data da proxima dose nao pode ser anterior a data de realizacao.");
        return registroSaudeRepository.save(registroSaude);
    }

    @Override
    public RegistroSaude update(RegistroSaude registroSaude) {
        if (registroSaude == null)
            throw new IllegalArgumentException("RegistroSaude nao pode ser nulo.");
        if (registroSaude.getId() == null)
            throw new IllegalArgumentException("ID e obrigatorio para atualizacao.");
        if (!registroSaudeRepository.existsById(registroSaude.getId()))
            throw new RuntimeException("RegistroSaude nao encontrado com id: " + registroSaude.getId());
        if (registroSaude.getAnimal() == null)
            throw new IllegalArgumentException("Animal e obrigatorio.");
        if (registroSaude.getTipo() == null)
            throw new IllegalArgumentException("Tipo de procedimento e obrigatorio.");
        if (registroSaude.getDataRealizacao() == null)
            throw new IllegalArgumentException("Data de realizacao e obrigatoria.");
        if (registroSaude.getDataProximaDose() != null
                && registroSaude.getDataProximaDose().isBefore(registroSaude.getDataRealizacao()))
            throw new IllegalArgumentException("Data da proxima dose nao pode ser anterior a data de realizacao.");
        return registroSaudeRepository.save(registroSaude);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        if (!registroSaudeRepository.existsById(id))
            throw new RuntimeException("RegistroSaude nao encontrado com id: " + id);
        registroSaudeRepository.deleteById(id);
    }

    @Override
    public List<RegistroSaude> findAll() {
        return registroSaudeRepository.findAll();
    }

    @Override
    public RegistroSaude findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        return registroSaudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistroSaude nao encontrado com id: " + id));
    }

    @Override
    public List<RegistroSaude> findByAnimalId(Long animalId) {
        if (animalId == null)
            throw new IllegalArgumentException("ID do animal nao pode ser nulo.");
        return registroSaudeRepository.findByAnimalId(animalId);
    }

    @Override
    public List<RegistroSaude> findByTipo(TipoProcedimentoEnum tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("Tipo nao pode ser nulo.");
        return registroSaudeRepository.findByTipo(tipo);
    }
}