package br.com.ifba.gestaoanimal.geral.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.adocao.view.AdocaoListar;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.animal.view.AnimalListar;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.logauditoria.view.LogAuditoriaListar;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaIController;
import br.com.ifba.gestaoanimal.ocorrencia.view.OcorrenciaListar;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.view.PessoaListar;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.registrosaude.view.RegistroSaudeListar;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.view.SolicitacaoVoluntarioListar;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.stereotype.Component;

@Component
public class TelaPrincipal extends javax.swing.JFrame {

    private final AnimalIController animalController;
    private final PessoaController pessoaController;
    private final AdocaoController adocaoController;
    private final RegistroSaudeController registroSaudeController;
    private final OcorrenciaIController ocorrenciaController;
    private final SolicitacaoVoluntarioIController solicitacaoController;
    private final LogAuditoriaIController logAuditoriaController;

    public TelaPrincipal(AnimalIController animalController,
            PessoaController pessoaController,
            AdocaoController adocaoController,
            RegistroSaudeController registroSaudeController,
            OcorrenciaIController ocorrenciaController,
            SolicitacaoVoluntarioIController solicitacaoController,
            LogAuditoriaIController logAuditoriaController) {
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.adocaoController = adocaoController;
        this.registroSaudeController = registroSaudeController;
        this.ocorrenciaController = ocorrenciaController;
        this.solicitacaoController = solicitacaoController;
        this.logAuditoriaController = logAuditoriaController;

        initComponents();
        estilizarDashboard();
        aplicarPermissoes();
        carregarDashboard();
        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    private void aplicarPermissoes() {
        btnAuditoria.setVisible(SessaoUsuario.temPermissao("VISUALIZAR_LOG_AUDITORIA"));
    }

    private void estilizarDashboard() {
        java.awt.Color roxo = new java.awt.Color(60, 52, 137);
        java.awt.Color cinza = new java.awt.Color(100, 100, 100);
        java.awt.Color fundoCard = new java.awt.Color(248, 248, 252);

        lblBoasVindas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        lblBoasVindas.setForeground(roxo);

        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jLabel2.setForeground(cinza);

        javax.swing.JPanel[] paineis = {painelAnimaisDis, painelAdocoes, painelOcorrencias, painelSolicitacoes};
        for (javax.swing.JPanel painel : paineis) {
            painel.setBackground(fundoCard);
            painel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 235), 1, true));
        }

        javax.swing.JLabel[] titulos = {lblTituloAnimais, lblTituloAdocoes, lblTituloOcorrencias, lblTituloSolicitacoes};
        for (javax.swing.JLabel titulo : titulos) {
            titulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            titulo.setForeground(cinza);
        }

        javax.swing.JLabel[] numeros = {lblAnimais, lblAdocoes, lblOcorrencias, lblSolicitacoes};
        for (javax.swing.JLabel numero : numeros) {
            numero.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
            numero.setForeground(roxo);
        }

        lblTituloAnimais.setText("Animais disponíveis");
        lblTituloAdocoes.setText("Adoções concluídas");
        lblTituloOcorrencias.setText("Ocorrências em aberto");
        lblTituloSolicitacoes.setText("Solicitações pendentes");
    }

    private void carregarDashboard() {
        int animaisDisponiveis = animalController.findByStatus(StatusAnimalEnum.DISPONIVEL).size();
        int adocoesConcluidas = adocaoController.findByStatus(StatusAdocaoEnum.CONCLUIDA).size();
        int ocorrenciasAbertas = ocorrenciaController.findByStatus(StatusOcorrenciaEnum.ABERTA).size();
        int solicitacoesPendentes = solicitacaoController.findByStatus(StatusSolicitacaoEnum.PENDENTE).size();

        Usuario usuarioLogado = SessaoUsuario.getUsuarioLogado();
        String nome = usuarioLogado != null && usuarioLogado.getPessoa() != null
                ? usuarioLogado.getPessoa().getNome()
                : "usuário";

        lblBoasVindas.setText("Bem-vindo(a), " + nome);
        lblAnimais.setText(String.valueOf(animaisDisponiveis));
        lblAdocoes.setText(String.valueOf(adocoesConcluidas));
        lblOcorrencias.setText(String.valueOf(ocorrenciasAbertas));
        lblSolicitacoes.setText(String.valueOf(solicitacoesPendentes));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        painelMenu = new javax.swing.JPanel();
        btnAnimais = new javax.swing.JButton();
        btnPessoas = new javax.swing.JButton();
        btnRegistroSaude = new javax.swing.JButton();
        btnOcorrencias = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAdocao = new javax.swing.JButton();
        btnDoações = new javax.swing.JButton();
        btnSolicitação = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        painelConteudo = new javax.swing.JPanel();
        painelAnimaisDis = new javax.swing.JPanel();
        lblAnimais = new javax.swing.JLabel();
        lblTituloAnimais = new javax.swing.JLabel();
        painelAdocoes = new javax.swing.JPanel();
        lblAdocoes = new javax.swing.JLabel();
        lblTituloAdocoes = new javax.swing.JLabel();
        painelOcorrencias = new javax.swing.JPanel();
        lblOcorrencias = new javax.swing.JLabel();
        lblTituloOcorrencias = new javax.swing.JLabel();
        painelSolicitacoes = new javax.swing.JPanel();
        lblSolicitacoes = new javax.swing.JLabel();
        lblTituloSolicitacoes = new javax.swing.JLabel();
        lblBoasVindas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(60, 52, 137));
        jPanel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        painelMenu.setBackground(new java.awt.Color(60, 52, 137));
        painelMenu.setForeground(new java.awt.Color(255, 255, 255));
        painelMenu.setToolTipText("");

        btnAnimais.setBackground(new java.awt.Color(60, 52, 137));
        btnAnimais.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAnimais.setForeground(new java.awt.Color(255, 255, 255));
        btnAnimais.setText("Animais");
        btnAnimais.setBorderPainted(false);
        btnAnimais.setFocusPainted(false);
        btnAnimais.addActionListener(this::btnAnimaisActionPerformed);

        btnPessoas.setBackground(new java.awt.Color(60, 52, 137));
        btnPessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPessoas.setForeground(new java.awt.Color(255, 255, 255));
        btnPessoas.setText("Pessoas");
        btnPessoas.setBorderPainted(false);
        btnPessoas.setFocusPainted(false);
        btnPessoas.addActionListener(this::btnPessoasActionPerformed);

        btnRegistroSaude.setBackground(new java.awt.Color(60, 52, 137));
        btnRegistroSaude.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistroSaude.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroSaude.setText("Registro de saúde");
        btnRegistroSaude.setBorderPainted(false);
        btnRegistroSaude.setFocusPainted(false);
        btnRegistroSaude.addActionListener(this::btnRegistroSaudeActionPerformed);

        btnOcorrencias.setBackground(new java.awt.Color(60, 52, 137));
        btnOcorrencias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOcorrencias.setForeground(new java.awt.Color(255, 255, 255));
        btnOcorrencias.setText("Ocorrências");
        btnOcorrencias.setBorderPainted(false);
        btnOcorrencias.setFocusPainted(false);
        btnOcorrencias.addActionListener(this::btnOcorrenciasActionPerformed);

        btnSair.setBackground(new java.awt.Color(60, 52, 137));
        btnSair.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(this::btnSairActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SOSPatas- Menu Principal");

        btnAdocao.setBackground(new java.awt.Color(60, 52, 137));
        btnAdocao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdocao.setForeground(new java.awt.Color(255, 255, 255));
        btnAdocao.setText("Adoções");
        btnAdocao.setBorderPainted(false);
        btnAdocao.setFocusPainted(false);
        btnAdocao.addActionListener(this::btnAdocaoActionPerformed);

        btnDoações.setBackground(new java.awt.Color(60, 52, 137));
        btnDoações.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDoações.setForeground(new java.awt.Color(255, 255, 255));
        btnDoações.setText("Doações");
        btnDoações.setBorderPainted(false);
        btnDoações.setFocusPainted(false);
        btnDoações.addActionListener(this::btnDoaçõesActionPerformed);

        btnSolicitação.setBackground(new java.awt.Color(60, 52, 137));
        btnSolicitação.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSolicitação.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitação.setText("Solicitações de voluntários");
        btnSolicitação.setBorderPainted(false);
        btnSolicitação.setFocusPainted(false);
        btnSolicitação.addActionListener(this::btnSolicitaçãoActionPerformed);

        btnAuditoria.setBackground(new java.awt.Color(60, 52, 137));
        btnAuditoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAuditoria.setText("Log de auditoria");
        btnAuditoria.setBorderPainted(false);
        btnAuditoria.setFocusPainted(false);
        btnAuditoria.addActionListener(this::btnAuditoriaActionPerformed);

        javax.swing.GroupLayout painelMenuLayout = new javax.swing.GroupLayout(painelMenu);
        painelMenu.setLayout(painelMenuLayout);
        painelMenuLayout.setHorizontalGroup(
            painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelMenuLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelMenuLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdocao)
                            .addComponent(btnAnimais)
                            .addComponent(btnDoações)
                            .addComponent(btnOcorrencias)
                            .addComponent(btnPessoas)
                            .addComponent(btnRegistroSaude)
                            .addComponent(btnSolicitação)
                            .addComponent(btnAuditoria)))
                    .addComponent(jLabel1))
                .addGap(34, 34, 34))
            .addGroup(painelMenuLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelMenuLayout.setVerticalGroup(
            painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMenuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(btnAnimais)
                .addGap(28, 28, 28)
                .addComponent(btnAdocao)
                .addGap(33, 33, 33)
                .addComponent(btnDoações)
                .addGap(26, 26, 26)
                .addComponent(btnOcorrencias)
                .addGap(29, 29, 29)
                .addComponent(btnPessoas)
                .addGap(29, 29, 29)
                .addComponent(btnRegistroSaude)
                .addGap(29, 29, 29)
                .addComponent(btnSolicitação)
                .addGap(29, 29, 29)
                .addComponent(btnAuditoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(17, 17, 17))
        );

        jPanel3.setBackground(new java.awt.Color(60, 52, 137));
        jPanel3.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        painelAnimaisDis.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelAnimaisDisLayout = new javax.swing.GroupLayout(painelAnimaisDis);
        painelAnimaisDis.setLayout(painelAnimaisDisLayout);
        painelAnimaisDisLayout.setHorizontalGroup(
            painelAnimaisDisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAnimaisDisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloAnimais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelAnimaisDisLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblAnimais, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        painelAnimaisDisLayout.setVerticalGroup(
            painelAnimaisDisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimaisDisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloAnimais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblAnimais, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelAdocoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelAdocoesLayout = new javax.swing.GroupLayout(painelAdocoes);
        painelAdocoes.setLayout(painelAdocoesLayout);
        painelAdocoesLayout.setHorizontalGroup(
            painelAdocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAdocoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloAdocoes, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelAdocoesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblAdocoes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelAdocoesLayout.setVerticalGroup(
            painelAdocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAdocoesLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblTituloAdocoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAdocoes, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelOcorrencias.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelOcorrenciasLayout = new javax.swing.GroupLayout(painelOcorrencias);
        painelOcorrencias.setLayout(painelOcorrenciasLayout);
        painelOcorrenciasLayout.setHorizontalGroup(
            painelOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOcorrenciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelOcorrenciasLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelOcorrenciasLayout.setVerticalGroup(
            painelOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOcorrenciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(lblOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelSolicitacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelSolicitacoesLayout = new javax.swing.GroupLayout(painelSolicitacoes);
        painelSolicitacoes.setLayout(painelSolicitacoesLayout);
        painelSolicitacoesLayout.setHorizontalGroup(
            painelSolicitacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSolicitacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloSolicitacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelSolicitacoesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelSolicitacoesLayout.setVerticalGroup(
            painelSolicitacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSolicitacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloSolicitacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setText("Resumo geral do SOSPatas");

        javax.swing.GroupLayout painelConteudoLayout = new javax.swing.GroupLayout(painelConteudo);
        painelConteudo.setLayout(painelConteudoLayout);
        painelConteudoLayout.setHorizontalGroup(
            painelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConteudoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(painelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConteudoLayout.createSequentialGroup()
                        .addComponent(painelAnimaisDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(painelAdocoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(painelOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(painelSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(painelConteudoLayout.createSequentialGroup()
                        .addGroup(painelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        painelConteudoLayout.setVerticalGroup(
            painelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConteudoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addGroup(painelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelAnimaisDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelSolicitacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelAdocoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(painelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimaisActionPerformed
        this.setVisible(false);
        AnimalListar tela = new AnimalListar(animalController, ocorrenciaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnAnimaisActionPerformed

    private void btnPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoasActionPerformed
        this.setVisible(false);
        PessoaListar tela = new PessoaListar(pessoaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnPessoasActionPerformed

    private void btnRegistroSaudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroSaudeActionPerformed
        this.setVisible(false);
        RegistroSaudeListar tela = new RegistroSaudeListar(registroSaudeController, animalController, pessoaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnRegistroSaudeActionPerformed

    private void btnOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcorrenciasActionPerformed
        this.setVisible(false);
        OcorrenciaListar tela = new OcorrenciaListar(ocorrenciaController, pessoaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnOcorrenciasActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAdocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdocaoActionPerformed
        this.setVisible(false);
        AdocaoListar tela = new AdocaoListar(adocaoController, animalController, pessoaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
                carregarDashboard();
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnAdocaoActionPerformed

    private void btnDoaçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoaçõesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoaçõesActionPerformed

    private void btnSolicitaçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitaçãoActionPerformed
        this.setVisible(false);
        SolicitacaoVoluntarioListar tela = new SolicitacaoVoluntarioListar(solicitacaoController, pessoaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnSolicitaçãoActionPerformed

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
        this.setVisible(false);
        LogAuditoriaListar tela = new LogAuditoriaListar(logAuditoriaController);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TelaPrincipal.this.setVisible(true);
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_btnAuditoriaActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdocao;
    private javax.swing.JButton btnAnimais;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnDoações;
    private javax.swing.JButton btnOcorrencias;
    private javax.swing.JButton btnPessoas;
    private javax.swing.JButton btnRegistroSaude;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSolicitação;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAdocoes;
    private javax.swing.JLabel lblAnimais;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblOcorrencias;
    private javax.swing.JLabel lblSolicitacoes;
    private javax.swing.JLabel lblTituloAdocoes;
    private javax.swing.JLabel lblTituloAnimais;
    private javax.swing.JLabel lblTituloOcorrencias;
    private javax.swing.JLabel lblTituloSolicitacoes;
    private javax.swing.JPanel painelAdocoes;
    private javax.swing.JPanel painelAnimaisDis;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelMenu;
    private javax.swing.JPanel painelOcorrencias;
    private javax.swing.JPanel painelSolicitacoes;
    // End of variables declaration//GEN-END:variables
}
