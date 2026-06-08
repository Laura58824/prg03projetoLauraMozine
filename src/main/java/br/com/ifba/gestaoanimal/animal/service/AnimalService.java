package br.com.ifba.gestaoanimal.animal.service;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.repository.AnimalRepository;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements AnimalIService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        if (!animalRepository.existsById(animal.getId())) {
            throw new RuntimeException("Animal não encontrado com id: " + animal.getId());
        }
        return animalRepository.save(animal);
    }

   @Override
    public void delete(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));
        animal.setAtivo(false);
        animalRepository.save(animal);
    }

    
    @Override
    public List<Animal> findAll() {
        return animalRepository.findByAtivoTrue();
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));
    }
    
    @Override
    public List<Animal> findByNome(String nome) {
        return animalRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Animal> findByStatus(StatusAnimalEnum status) {
        return animalRepository.findByStatusAndAtivoTrue(status);
    }

}