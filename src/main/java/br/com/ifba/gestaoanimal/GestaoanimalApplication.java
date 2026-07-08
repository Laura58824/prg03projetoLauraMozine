package br.com.ifba.gestaoanimal;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.telaprincipal.view.TelaPrincipal;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.telaprincipal.view.TelaPrincipal;
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
        AdocaoController adocaoController = context.getBean(AdocaoController.class);
        RegistroSaudeController registroSaudeController = context.getBean(RegistroSaudeController.class);

        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal(
                animalController,
                pessoaController,
                adocaoController,
                registroSaudeController
            );
            telaPrincipal.setVisible(true);
        });
    }
}