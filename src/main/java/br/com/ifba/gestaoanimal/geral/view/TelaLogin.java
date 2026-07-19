package br.com.ifba.gestaoanimal.geral.view;
 
import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalIController;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaController;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.usuario.controller.UsuarioIController;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
 
 
public class TelaLogin extends javax.swing.JFrame {
    
    private final UsuarioIController usuarioController;
    private final AnimalIController animalController;
    private final PessoaController pessoaController;
    private final AdocaoController adocaoController;
    private final RegistroSaudeController registroSaudeController;
    private final OcorrenciaController ocorrenciaController;
    private final SolicitacaoVoluntarioIController solicitacaoController;
    
    public TelaLogin(UsuarioIController usuarioController,
                      AnimalIController animalController,
                      PessoaController pessoaController,
                      AdocaoController adocaoController,
                      RegistroSaudeController registroSaudeController,
                      OcorrenciaController ocorrenciaController, SolicitacaoVoluntarioIController solicitacaoController) {
 
        this.usuarioController = usuarioController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.adocaoController = adocaoController;
        this.registroSaudeController = registroSaudeController;
        this.ocorrenciaController = ocorrenciaController;
        this.solicitacaoController= solicitacaoController;
 
        initComponents();
        this.setLocationRelativeTo(null);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblErro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOSPatas - Login");

        jLabel1.setText("Usuário:");

        jLabel2.setText("Senha:");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);

        lblErro.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(379, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin)
                    .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(lblErro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(290, 290, 290))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblErro)
                .addGap(63, 63, 63)
                .addComponent(btnEntrar)
                .addContainerGap(253, Short.MAX_VALUE))
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
                solicitacaoController
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
    private javax.swing.JLabel lblErro;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
