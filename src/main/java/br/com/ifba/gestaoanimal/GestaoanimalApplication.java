package br.com.ifba.gestaoanimal;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.geral.view.TelaLogin;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaController;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.usuario.controller.UsuarioIController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javax.swing.*;

@SpringBootApplication
public class GestaoanimalApplication {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(GestaoanimalApplication.class, args);

        UsuarioIController usuarioController = context.getBean(UsuarioIController.class);
        AnimalIController animalController = context.getBean(AnimalIController.class);
        PessoaController pessoaController = context.getBean(PessoaController.class);
        AdocaoController adocaoController = context.getBean(AdocaoController.class);
        RegistroSaudeController registroSaudeController = context.getBean(RegistroSaudeController.class);
        OcorrenciaController ocorrenciaController = context.getBean(OcorrenciaController.class);
        SolicitacaoVoluntarioIController solicitacaoController = context.getBean(SolicitacaoVoluntarioIController.class);
        LogAuditoriaIController logauditoriaController = context.getBean(LogAuditoriaIController.class);
        
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin(
                usuarioController,
                animalController,
                pessoaController,
                adocaoController,
                registroSaudeController,
                ocorrenciaController,
                solicitacaoController,
                logauditoriaController
            );
            telaLogin.setVisible(true);
        });
    }
}