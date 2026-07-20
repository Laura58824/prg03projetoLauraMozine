package br.com.ifba.gestaoanimal.animal.controller;

import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.animal.service.AnimalIService;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnimalController implements AnimalIController {

    @Autowired
    private AnimalIService animalService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public Animal save(Animal animal) {
        Animal salvo = animalService.save(animal);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Cadastrou animal: " + salvo.getNome() + " (ID " + salvo.getId() + ")");
        return salvo;
    }

    @Override
    public Animal update(Animal animal) {
        Animal atualizado = animalService.update(animal);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou animal: " + atualizado.getNome() + " (ID " + atualizado.getId() + ")");
        return atualizado;
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalService.findById(id);
        animalService.delete(id);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Desativou animal: " + (animal != null ? animal.getNome() : "") + " (ID " + id + ")");
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