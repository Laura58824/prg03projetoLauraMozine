package br.com.ifba.gestaoanimal.geral.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.doacao.controller.DoacaoIController;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaController;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.usuario.controller.UsuarioIController;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import javax.swing.JFrame;


public class TelaLogin extends javax.swing.JFrame {
    
    private final UsuarioIController usuarioController;
    private final AnimalIController animalController;
    private final PessoaController pessoaController;
    private final AdocaoController adocaoController;
    private final RegistroSaudeController registroSaudeController;
    private final OcorrenciaController ocorrenciaController;
    private final SolicitacaoVoluntarioIController solicitacaoController;
    private final LogAuditoriaIController logAuditoriaController;
    private final DoacaoIController doacaoController;
    
    public TelaLogin(UsuarioIController usuarioController,
                      AnimalIController animalController,
                      PessoaController pessoaController,
                      AdocaoController adocaoController,
                      RegistroSaudeController registroSaudeController,
                      OcorrenciaController ocorrenciaController,
                      SolicitacaoVoluntarioIController solicitacaoController,
                      LogAuditoriaIController logAuditoriaController,
                      DoacaoIController doacaoController) {

        this.usuarioController = usuarioController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.adocaoController = adocaoController;
        this.registroSaudeController = registroSaudeController;
        this.ocorrenciaController = ocorrenciaController;
        this.solicitacaoController = solicitacaoController;
        this.logAuditoriaController = logAuditoriaController;
        this.doacaoController= doacaoController;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblErro = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOSPatas - Login");
        setBackground(new java.awt.Color(255, 255, 255));

        lblErro.setForeground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(60, 52, 137));
        jPanel1.setForeground(new java.awt.Color(60, 52, 137));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/static/icons8-test-account-96.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuário:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Senha:");

        btnEntrar.setBackground(new java.awt.Color(153, 153, 255));
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnEntrar)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErro, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(lblErro))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
       String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Preencha login e senha.");
            return;
        }

        Usuario usuario = usuarioController.autenticar(login, senha);

        if (usuario == null) {
            lblErro.setText("Login ou senha inválidos.");
            txtSenha.setText("");
            return;
        }

        SessaoUsuario.login(usuario);

        TelaPrincipal telaPrincipal = new TelaPrincipal(
                animalController,
                pessoaController,
                adocaoController,
                registroSaudeController,
                ocorrenciaController,
                solicitacaoController,
                logAuditoriaController,
                doacaoController
        );
        telaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEntrarActionPerformed

   
    public static void main(String args[]) {
     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErro;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
