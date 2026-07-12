package br.com.ifba.gestaoanimal.animal.controller;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.service.AnimalIService;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnimalController implements AnimalIController {

    @Autowired
    private AnimalIService animalService;

    @Override
    public Animal save(Animal animal) {
        return animalService.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        return animalService.update(animal);
    }

    @Override
    public void delete(Long id) {
        animalService.delete(id);
    }

    @Override
    public List<Animal> findAll() {
        return animalService.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return animalService.findById(id);
    }

    @Override
    public List<Animal> findByNome(String nome) {
        return animalService.findByNome(nome);
    }

    @Override
    public List<Animal> findByStatus(StatusAnimalEnum status) {
        return animalService.findByStatus(status);
    }

}