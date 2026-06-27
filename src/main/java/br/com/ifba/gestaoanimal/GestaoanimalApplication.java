package br.com.ifba.gestaoanimal;

import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.view.AnimalListar;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.view.PessoaListar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javax.swing.*;

@SpringBootApplication
public class GestaoanimalApplication {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(GestaoanimalApplication.class, args);

        AnimalController animalController = context.getBean(AnimalController.class);
        PessoaController pessoaController = context.getBean(PessoaController.class);

        SwingUtilities.invokeLater(() -> {
            AnimalListar telaAnimal = new AnimalListar(animalController);
            telaAnimal.setVisible(true);

            PessoaListar telaPessoa = new PessoaListar(pessoaController);
            telaPessoa.setVisible(true);
        });
    }
}