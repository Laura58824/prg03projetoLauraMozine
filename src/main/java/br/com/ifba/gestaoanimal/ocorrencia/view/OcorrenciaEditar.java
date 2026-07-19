
package br.com.ifba.gestaoanimal.ocorrencia.view;

import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.UrgenciaEnum;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaController;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaIController;
import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
 

public class OcorrenciaEditar extends javax.swing.JFrame {
    
   
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OcorrenciaEditar.class.getName());
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
 
    private final OcorrenciaIController ocorrenciaController;
    private final PessoaController pessoaController;
    private final OcorrenciaListar parent;
    private final Ocorrencia ocorrencia;
 
    public OcorrenciaEditar(OcorrenciaIController ocorrenciaController, PessoaController pessoaController,
            OcorrenciaListar parent, Ocorrencia ocorrencia) {
        this.ocorrenciaController = ocorrenciaController;
        this.pessoaController = pessoaController;
        this.parent = parent;
        this.ocorrencia = ocorrencia;
        initComponents();
        configurarTela();
        preencherCampos();
    }
 
    private void configurarTela() {
        setTitle("SOSPatas — editar ocorrência");
        setLocationRelativeTo(null);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
 
        cmbTipo.setModel(new DefaultComboBoxModel<>(TipoOcorrenciaEnum.values()));
        cmbUrgencia.setModel(new DefaultComboBoxModel<>(UrgenciaEnum.values()));
        cmbStatus.setModel(new DefaultComboBoxModel<>(StatusOcorrenciaEnum.values()));
 
        List<Pessoa> pessoas = pessoaController.findAll();
        cmbRegistradaPor.setModel(new DefaultComboBoxModel<>(pessoas.toArray(new Pessoa[0])));
 
        DefaultComboBoxModel<Pessoa> modeloVoluntario = new DefaultComboBoxModel<>();
        modeloVoluntario.addElement(null);
        pessoas.forEach(modeloVoluntario::addElement);
        cmbVoluntario.setModel(modeloVoluntario);
 
        DefaultListCellRenderer rendererPessoa = new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                String texto = (value instanceof Pessoa p) ? p.getNome() : "— Nenhum —";
                return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
            }
        };
        cmbRegistradaPor.setRenderer(rendererPessoa);
        cmbVoluntario.setRenderer(rendererPessoa);
 
        for (JComponent campo : new JComponent[]{txtEndereco, txtBairro, cmbTipo, cmbUrgencia,
                cmbStatus, cmbRegistradaPor, cmbVoluntario, txtDescricao, txtDataAtendimento, btnSalvar, btnCancelar}) {
            campo.setFont(fonte);
        }
 
        btnSalvar.setText("Salvar alterações");
        btnSalvar.setForeground(new java.awt.Color(60, 52, 137));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
 
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
 
        txtDataAtendimento.setToolTipText("dd/MM/yyyy HH:mm");
    }
 
    private void preencherCampos() {
        txtDescricao.setText(ocorrencia.getDescricao() != null ? ocorrencia.getDescricao() : "");
        txtEndereco.setText(ocorrencia.getEndereco() != null ? ocorrencia.getEndereco() : "");
        txtBairro.setText(ocorrencia.getBairro() != null ? ocorrencia.getBairro() : "");
        cmbTipo.setSelectedItem(ocorrencia.getTipo());
        cmbUrgencia.setSelectedItem(ocorrencia.getUrgencia());
        cmbStatus.setSelectedItem(ocorrencia.getStatus());
        cmbRegistradaPor.setSelectedItem(ocorrencia.getRegistradaPor());
        cmbVoluntario.setSelectedItem(ocorrencia.getVoluntario());
        if (ocorrencia.getDataAtendimento() != null) {
            txtDataAtendimento.setText(ocorrencia.getDataAtendimento().format(FMT));
        }
    }
 
    private void salvar() {
        if (cmbTipo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o tipo da ocorrência.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbUrgencia.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a urgência.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbStatus.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o status.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbRegistradaPor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione quem registrou a ocorrência.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        String dataAtendStr = txtDataAtendimento.getText().trim();
        LocalDateTime dataAtendimento = null;
        if (!dataAtendStr.isEmpty()) {
            try {
                dataAtendimento = LocalDateTime.parse(dataAtendStr, FMT);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de atendimento inválida. Use dd/MM/yyyy HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
 
        ocorrencia.setDescricao(txtDescricao.getText().trim());
        ocorrencia.setEndereco(txtEndereco.getText().trim());
        ocorrencia.setBairro(txtBairro.getText().trim());
        ocorrencia.setTipo((TipoOcorrenciaEnum) cmbTipo.getSelectedItem());
        ocorrencia.setUrgencia((UrgenciaEnum) cmbUrgencia.getSelectedItem());
        ocorrencia.setStatus((StatusOcorrenciaEnum) cmbStatus.getSelectedItem());
        ocorrencia.setRegistradaPor((Pessoa) cmbRegistradaPor.getSelectedItem());
        ocorrencia.setVoluntario((Pessoa) cmbVoluntario.getSelectedItem());
        ocorrencia.setDataAtendimento(dataAtendimento);
 
        ocorrenciaController.update(ocorrencia);
        JOptionPane.showMessageDialog(this, "Ocorrência atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        parent.carregarTabela();
        dispose();
    }
 
    private void cancelar() {
        dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbRegistradaPor = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        cmbUrgencia = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        cmbTipo = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        cmbVoluntario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDataAtendimento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel1.setText("Tipo*");

        jLabel2.setText("Status*");

        jLabel3.setText("Voluntário");

        jLabel4.setText("Bairro");

        jLabel6.setText("Descrição");

        jLabel7.setText("Urgência*");

        jLabel8.setText("Registrado por");

        jLabel9.setText("Endereço");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSalvar.setText("Salvar Alterações");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        jLabel5.setText("Data de atendimento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(67, 67, 67)
                        .addComponent(btnSalvar)
                        .addGap(128, 128, 128))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addComponent(txtDataAtendimento))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(cmbTipo, 0, 216, Short.MAX_VALUE)
                                        .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cmbVoluntario, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7)
                                        .addComponent(cmbUrgencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbRegistradaPor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))))
                        .addGap(89, 89, 89))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRegistradaPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVoluntario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    
    public static void main(String args[]) {
  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoa> cmbRegistradaPor;
    private javax.swing.JComboBox<StatusOcorrenciaEnum> cmbStatus;
    private javax.swing.JComboBox<TipoOcorrenciaEnum> cmbTipo;
    private javax.swing.JComboBox<UrgenciaEnum> cmbUrgencia;
    private javax.swing.JComboBox<Pessoa> cmbVoluntario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtDataAtendimento;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtEndereco;
    // End of variables declaration//GEN-END:variables
}
