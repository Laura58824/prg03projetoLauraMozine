package br.com.ifba.gestaoanimal.animal.controller;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import java.util.List;

public interface AnimalIController {
    Animal save(Animal animal);
    Animal update(Animal animal);
    void delete(Long id);
    List<Animal> findAll();
    Animal findById(Long id);
    List<Animal> findByNome(String nome);
    List<Animal> findByStatus(StatusAnimalEnum status);
}