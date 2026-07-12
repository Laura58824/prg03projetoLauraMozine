/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.gestaoanimal.geral.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.adocao.view.AdocaoListar;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.animal.view.AnimalListar;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.view.PessoaListar;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.registrosaude.view.RegistroSaudeListar;
import org.springframework.stereotype.Component;



@Component
public class TelaPrincipal extends javax.swing.JFrame {
    
private final AnimalIController animalController;
private final PessoaController pessoaController;
private final AdocaoController adocaoController;
private final RegistroSaudeController registroSaudeController;

public TelaPrincipal(AnimalIController animalController,
                      PessoaController pessoaController,
                      AdocaoController adocaoController,
                      RegistroSaudeController registroSaudeController) {
    this.animalController = animalController;
    this.pessoaController = pessoaController;
    this.adocaoController = adocaoController;
    this.registroSaudeController = registroSaudeController;

    initComponents();
    this.setLocationRelativeTo(null);
    this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelMenu = new javax.swing.JPanel();
        btnAnimais = new javax.swing.JButton();
        btnPessoas = new javax.swing.JButton();
        btnRegistroSaude = new javax.swing.JButton();
        btnOcorrencias = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelMenu.setBackground(new java.awt.Color(60, 52, 137));
        painelMenu.setToolTipText("");

        btnAnimais.setBackground(new java.awt.Color(60, 52, 137));
        btnAnimais.setForeground(new java.awt.Color(255, 255, 255));
        btnAnimais.setText("Animais");
        btnAnimais.setBorderPainted(false);
        btnAnimais.setFocusPainted(false);
        btnAnimais.addActionListener(this::btnAnimaisActionPerformed);

        btnPessoas.setBackground(new java.awt.Color(60, 52, 137));
        btnPessoas.setForeground(new java.awt.Color(255, 255, 255));
        btnPessoas.setText("Pessoas");
        btnPessoas.setBorderPainted(false);
        btnPessoas.setFocusPainted(false);
        btnPessoas.addActionListener(this::btnPessoasActionPerformed);

        btnRegistroSaude.setBackground(new java.awt.Color(60, 52, 137));
        btnRegistroSaude.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroSaude.setText("Registro de saúde");
        btnRegistroSaude.setBorderPainted(false);
        btnRegistroSaude.setFocusPainted(false);
        btnRegistroSaude.addActionListener(this::btnRegistroSaudeActionPerformed);

        btnOcorrencias.setBackground(new java.awt.Color(60, 52, 137));
        btnOcorrencias.setForeground(new java.awt.Color(255, 255, 255));
        btnOcorrencias.setText("Ocorrências");
        btnOcorrencias.setBorderPainted(false);
        btnOcorrencias.setFocusPainted(false);
        btnOcorrencias.addActionListener(this::btnOcorrenciasActionPerformed);

        btnSair.setBackground(new java.awt.Color(60, 52, 137));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(this::btnSairActionPerformed);

        jLabel1.setText("SOSPatas- Menu Principal");

        javax.swing.GroupLayout painelMenuLayout = new javax.swing.GroupLayout(painelMenu);
        painelMenu.setLayout(painelMenuLayout);
        painelMenuLayout.setHorizontalGroup(
            painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMenuLayout.createSequentialGroup()
                .addGroup(painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelMenuLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSair)
                            .addComponent(btnOcorrencias)
                            .addComponent(btnRegistroSaude)
                            .addComponent(btnPessoas)
                            .addComponent(btnAnimais)))
                    .addGroup(painelMenuLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        painelMenuLayout.setVerticalGroup(
            painelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMenuLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(btnAnimais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPessoas)
                .addGap(48, 48, 48)
                .addComponent(btnRegistroSaude)
                .addGap(48, 48, 48)
                .addComponent(btnOcorrencias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimaisActionPerformed
     this.setVisible(false);
    new AnimalListar(animalController).setVisible(true);
    }//GEN-LAST:event_btnAnimaisActionPerformed

    private void btnPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoasActionPerformed
    this.setVisible(false);
    new PessoaListar(pessoaController).setVisible(true);
    }//GEN-LAST:event_btnPessoasActionPerformed

    private void btnRegistroSaudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroSaudeActionPerformed
        this.setVisible(false);
    new RegistroSaudeListar(registroSaudeController, animalController, pessoaController).setVisible(true);
    }//GEN-LAST:event_btnRegistroSaudeActionPerformed

    private void btnOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcorrenciasActionPerformed
        this.setVisible(false);
    new AdocaoListar(adocaoController, animalController, pessoaController).setVisible(true);
    }//GEN-LAST:event_btnOcorrenciasActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnimais;
    private javax.swing.JButton btnOcorrencias;
    private javax.swing.JButton btnPessoas;
    private javax.swing.JButton btnRegistroSaude;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel painelMenu;
    // End of variables declaration//GEN-END:variables
}
