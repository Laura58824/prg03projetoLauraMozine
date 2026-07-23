package br.com.ifba.gestaoanimal.adocao.service;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.adocao.repository.AdocaoRepository;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.repository.AnimalRepository;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdocaoService implements AdocaoIService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Adocao save(Adocao adocao) {
        if (adocao == null)
            throw new IllegalArgumentException("Adocao nao pode ser nula.");
        if (adocao.getId() != null)
            throw new IllegalArgumentException("Adocao nova nao deve ter ID.");
        if (adocao.getAnimal() == null)
            throw new IllegalArgumentException("Animal e obrigatorio.");
        if (adocao.getAdotante() == null)
            throw new IllegalArgumentException("Adotante e obrigatorio.");
        if (adocao.getResponsavel() == null)
            throw new IllegalArgumentException("Responsavel e obrigatorio.");

        Animal animal = animalRepository.findById(adocao.getAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Animal nao encontrado."));
        if (animal.getStatus() == StatusAnimalEnum.ADOTADO)
            throw new IllegalStateException("Este animal ja foi adotado e ainda nao foi devolvido.");

        if (adocao.getStatus() == null)
            adocao.setStatus(StatusAdocaoEnum.PENDENTE);
        if (adocao.getDataAbertura() == null)
            adocao.setDataAbertura(LocalDate.now());
        return adocaoRepository.save(adocao);
    }

    @Override
    public Adocao update(Adocao adocao) {
        if (adocao == null)
            throw new IllegalArgumentException("Adocao nao pode ser nula.");
        if (adocao.getId() == null)
            throw new IllegalArgumentException("ID e obrigatorio para atualizacao.");

        Adocao original = adocaoRepository.findById(adocao.getId())
                .orElseThrow(() -> new RuntimeException("Adocao nao encontrada com id: " + adocao.getId()));

       
        adocao.setDataAbertura(original.getDataAbertura());
        adocao.setAnimal(original.getAnimal());

     
        if (adocao.getAdotante() == null)
            throw new IllegalArgumentException("Adotante e obrigatorio.");
        if (adocao.getResponsavel() == null)
            throw new IllegalArgumentException("Responsavel e obrigatorio.");
        if (adocao.getStatus() == null)
            throw new IllegalArgumentException("Status e obrigatorio.");

        if (adocao.getStatus() == StatusAdocaoEnum.CONCLUIDA) {
            Animal animal = adocao.getAnimal();
            animal.setStatus(StatusAnimalEnum.ADOTADO);
            animalRepository.save(animal);
        }

        return adocaoRepository.save(adocao);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        if (!adocaoRepository.existsById(id))
            throw new RuntimeException("Adocao nao encontrada com id: " + id);
        adocaoRepository.deleteById(id);
    }

    @Override
    public List<Adocao> findAll() {
        return adocaoRepository.findAll();
    }

    @Override
    public Adocao findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        return adocaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adocao nao encontrada com id: " + id));
    }

    @Override
    public List<Adocao> findByStatus(StatusAdocaoEnum status) {
        if (status == null)
            throw new IllegalArgumentException("Status nao pode ser nulo.");
        return adocaoRepository.findByStatus(status);
    }
}