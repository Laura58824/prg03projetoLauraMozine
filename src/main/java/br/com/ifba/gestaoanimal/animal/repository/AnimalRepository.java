package br.com.ifba.gestaoanimal.animal.repository;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAtivoTrue();

    List<Animal> findByStatus(StatusAnimalEnum status);

    List<Animal> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);

    List<Animal> findByStatusAndAtivoTrue(StatusAnimalEnum status);

    public List<Animal> findByNomeContainingIgnoreCase(String nome);

}