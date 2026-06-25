package br.com.ifba.gestaoanimal.animal.service;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.repository.AnimalRepository;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService implements AnimalIService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
   public Animal save(Animal animal) {
       if (animal == null)
           throw new IllegalArgumentException("Animal não pode ser nulo.");
       if (animal.getId() != null)
           throw new IllegalArgumentException("Animal novo não deve ter ID.");

       animal.setAtivo(true);

       return animalRepository.save(animal); 
   }
   
        @Override
    public Animal update(Animal animal) {
         if (animal == null)
             throw new IllegalArgumentException("Animal não pode ser nulo.");
         if (animal.getId() == null)
             throw new IllegalArgumentException("ID é obrigatório para atualização.");
         if (!animalRepository.existsById(animal.getId()))
             throw new RuntimeException("Animal não encontrado com id: " + animal.getId());

         return animalRepository.save(animal);
    }

        @Override
     public void delete(Long id) {
         if (id == null)
             throw new IllegalArgumentException("ID não pode ser nulo.");

         Animal animal = animalRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));

         if (Boolean.FALSE.equals(animal.getAtivo()))
             throw new RuntimeException("Animal já está inativo.");

         animal.setAtivo(false);
         animalRepository.save(animal);
     }



        @Override
     public List<Animal> findAll() {
         return animalRepository.findByAtivoTrue();
     }
     
    @Override
    public Animal findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID não pode ser nulo.");

        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));
    }

    @Override
    public List<Animal> findByNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome para busca não pode ser vazio.");

        return animalRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Animal> findByStatus(StatusAnimalEnum status) {
        if (status == null)
            throw new IllegalArgumentException("Status não pode ser nulo.");

        return animalRepository.findByStatusAndAtivoTrue(status);
    }

}