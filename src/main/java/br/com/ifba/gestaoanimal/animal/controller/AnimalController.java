package br.com.ifba.gestaoanimal.animal.controller;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.service.AnimalIService;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalIService animalService;

    public Animal save(Animal animal) {
        return animalService.save(animal);
    }

    public Animal update(Animal animal) {
        return animalService.update(animal);
    }

    public void delete(Long id) {
        animalService.delete(id);
    }

    public List<Animal> findAll() {
        return animalService.findAll();
    }

    public Animal findById(Long id) {
        return animalService.findById(id);
    }

    public List<Animal> findByNome(String nome) {
        return animalService.findByNome(nome);
    }

    public List<Animal> findByStatus(StatusAnimalEnum status) {
        return animalService.findByStatus(status);
    }

}