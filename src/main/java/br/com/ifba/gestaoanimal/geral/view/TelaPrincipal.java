package br.com.ifba.gestaoanimal.geral.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.adocao.view.AdocaoListar;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.animal.view.AnimalListar;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaIController;
import br.com.ifba.gestaoanimal.ocorrencia.view.OcorrenciaListar;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.view.PessoaListar;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.registrosaude.view.RegistroSaudeListar;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.view.SolicitacaoVoluntarioListar;
import org.springframework.stereotype.Component;

@Component
public class TelaPrincipal extends javax.swing.JFrame {

    private final AnimalIController animalController;
    private final PessoaController pessoaController;
    private final AdocaoController adocaoController;
    private final RegistroSaudeController registroSaudeController;
    private final OcorrenciaIController ocorrenciaController;
   private final SolicitacaoVoluntarioIController solicitacaoController;
   
    public TelaPrincipal(AnimalIController animalController,
            PessoaController pessoaController,
            AdocaoController adocaoController,
            RegistroSaudeController registroSaudeController,
            OcorrenciaIController ocorrenciaController, SolicitacaoVoluntarioIController solicitacaoController) {
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.adocaoController = adocaoController;
        this.registroSaudeController = registroSaudeController;
        this.ocorrenciaController = ocorrenciaController;
        this.solicitacaoController= solicitacaoController;
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
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
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(60, 52, 137));
        jPanel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1176, Short.MAX_VALUE)
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
                            .addComponent(btnSolicitação)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(painelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(856, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(painelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        // TODO add your handling code here:
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

    
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdocao;
    private javax.swing.JButton btnAnimais;
    private javax.swing.JButton btnDoações;
    private javax.swing.JButton btnOcorrencias;
    private javax.swing.JButton btnPessoas;
    private javax.swing.JButton btnRegistroSaude;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSolicitação;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel painelMenu;
    // End of variables declaration//GEN-END:variables
}
