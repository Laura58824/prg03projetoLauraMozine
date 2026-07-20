package br.com.ifba.gestaoanimal.doacao.view;
 
import br.com.ifba.gestaoanimal.doacao.controller.DoacaoIController;
import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaIController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
 
public class DoacaoCadastrar extends javax.swing.JFrame {
 
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    private final DoacaoIController doacaoController;
    private final DoacaoListar parent;
    private final PessoaIController pessoaController;
 
    public DoacaoCadastrar(DoacaoIController doacaoController, DoacaoListar parent, PessoaIController pessoaController) {
        this.doacaoController = doacaoController;
        this.parent = parent;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTela();
    }
 
    private void configurarTela() {
        setTitle("SOSPatas - Cadastrar doação");
        setLocationRelativeTo(null);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
 
        cmbTipo.setModel(new DefaultComboBoxModel(TipoDoacaoEnum.values()));
        cmbTipo.setSelectedIndex(-1);
 
        cmbDoador.setModel(new DefaultComboBoxModel<>(pessoaController.findAll().toArray(new Pessoa[0])));
        cmbDoador.setSelectedIndex(-1);
        cmbDoador.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pessoa p) {
                    setText(p.getNome());
                } else {
                    setText("Selecione o doador");
                }
                return this;
            }
        });
 
        txtData.setText(LocalDate.now().format(FORMATO_DATA));
        txtData.setToolTipText("dd/MM/yyyy");
 
        cmbTipo.setFont(fonte);
        cmbDoador.setFont(fonte);
        txtData.setFont(fonte);
        txtValor.setFont(fonte);
        txtQuantidade.setFont(fonte);
        txtUnidade.setFont(fonte);
        txtDescricao.setFont(fonte);
 
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
 
        atualizarCamposPorTipo();
    }
    
      private void atualizarCamposPorTipo() {
        TipoDoacaoEnum tipo = (TipoDoacaoEnum) cmbTipo.getSelectedItem();
        boolean financeira = tipo == TipoDoacaoEnum.FINANCEIRA;
 
        txtValor.setEnabled(financeira);
        txtQuantidade.setEnabled(!financeira);
        txtUnidade.setEnabled(!financeira);
 
        if (financeira) {
            txtQuantidade.setText("");
            txtUnidade.setText("");
        } else {
            txtValor.setText("");
        }
    }
     private void salvar() {
        if (cmbDoador.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o doador.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o tipo de doação.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        TipoDoacaoEnum tipo = (TipoDoacaoEnum) cmbTipo.getSelectedItem();
        Doacao doacao = new Doacao();
        doacao.setDoador((Pessoa) cmbDoador.getSelectedItem());
        doacao.setTipo(tipo);
        doacao.setDescricao(txtDescricao.getText().trim());
 
        try {
            String dataStr = txtData.getText().trim();
            doacao.setData(dataStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dataStr, FORMATO_DATA));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        if (tipo == TipoDoacaoEnum.FINANCEIRA) {
            try {
                doacao.setValor(new BigDecimal(txtValor.getText().trim().replace(",", ".")));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            try {
                doacao.setQuantidade(Double.parseDouble(txtQuantidade.getText().trim().replace(",", ".")));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            doacao.setUnidade(txtUnidade.getText().trim());
        }
 
        try {
            doacaoController.salvar(doacao);
            JOptionPane.showMessageDialog(this, "Doação cadastrada com sucesso!");
            parent.carregarTabela();
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtQuantidade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        txtUnidade = new javax.swing.JTextField();
        Doador = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        cmbDoador = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo");

        jLabel5.setText("Unidade");

        cmbTipo.addActionListener(this::cmbTipoActionPerformed);

        Doador.setText("Doador");

        jLabel6.setText("Descrição");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel2.setText("Data");

        txtData.addActionListener(this::txtDataActionPerformed);

        btnSalvar.setText("Atualizar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        jLabel3.setText("Valor");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        jLabel4.setText("Quantidade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addGap(49, 49, 49)
                                .addComponent(btnSalvar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtQuantidade, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 222, Short.MAX_VALUE)
                                        .addComponent(txtData, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Doador)
                                    .addComponent(cmbDoador, 0, 222, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtValor)
                                    .addComponent(jLabel5)
                                    .addComponent(txtUnidade))))
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Doador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDoador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
          atualizarCamposPorTipo();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
          salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
          dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Doador;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoa> cmbDoador;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtUnidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
