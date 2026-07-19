package br.com.ifba.gestaoanimal.solicitacaovoluntario.view;
 
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
 
public class SolicitacaoVoluntarioCadastrar extends javax.swing.JFrame {
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SolicitacaoVoluntarioCadastrar.class.getName());
 
    private final SolicitacaoVoluntarioIController solicitacaoVoluntarioController;
    private final PessoaController pessoaController;
    private final SolicitacaoVoluntarioListar parent;
 
    public SolicitacaoVoluntarioCadastrar(SolicitacaoVoluntarioIController solicitacaoVoluntarioController,
            PessoaController pessoaController, SolicitacaoVoluntarioListar parent) {
        this.solicitacaoVoluntarioController = solicitacaoVoluntarioController;
        this.pessoaController = pessoaController;
        this.parent = parent;
        initComponents();
        configurarTela();
    }
 
    private void configurarTela() {
        setTitle("SOSPatas — nova solicitação de voluntariado");
        setLocationRelativeTo(null);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
 
        List<Pessoa> pessoas = pessoaController.findAll();
        cmbSolicitante.setModel(new DefaultComboBoxModel<>(pessoas.toArray(new Pessoa[0])));
        cmbSolicitante.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                String texto = (value instanceof Pessoa p) ? p.getNome() : "— Selecione —";
                return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
            }
        });
        cmbSolicitante.setSelectedIndex(-1);
 
        for (JComponent campo : new JComponent[]{cmbSolicitante, txtMotivacao, btnSalvar, btnCancelar}) {
            campo.setFont(fonte);
        }
 
        btnSalvar.setText("Salvar");
        btnSalvar.setForeground(new java.awt.Color(60, 52, 137));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
 
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
    }
 
    private void salvar() {
        if (cmbSolicitante.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o solicitante.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtMotivacao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a motivação da solicitação.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        SolicitacaoVoluntario solicitacao = new SolicitacaoVoluntario();
        solicitacao.setSolicitante((Pessoa) cmbSolicitante.getSelectedItem());
        solicitacao.setMotivacao(txtMotivacao.getText().trim());
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        solicitacao.setStatus(StatusSolicitacaoEnum.PENDENTE);
 
        solicitacaoVoluntarioController.save(solicitacao);
        JOptionPane.showMessageDialog(this, "Solicitação registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        parent.carregarTabela();
        dispose();
    }
 
    private void cancelar() {
        dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBarra = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSolicitante = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivacao = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jLabel1.setText("Solicitante*");

        jLabel2.setText("Motivação*");

        txtMotivacao.setColumns(20);
        txtMotivacao.setRows(5);
        jScrollPane1.setViewportView(txtMotivacao);

        btnSalvar.setText("Cadastrar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(cmbSolicitante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(74, 74, 74)
                .addComponent(btnSalvar)
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoa> cmbSolicitante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JTextArea txtMotivacao;
    // End of variables declaration//GEN-END:variables
}
