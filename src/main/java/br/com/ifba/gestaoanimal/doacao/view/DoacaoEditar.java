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
 
public class DoacaoEditar extends javax.swing.JFrame {
 
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    private final DoacaoIController doacaoController;
    private final DoacaoListar parent;
    private final PessoaIController pessoaController;
    private final Doacao doacao;
 
    public DoacaoEditar(DoacaoIController doacaoController, DoacaoListar parent,
            PessoaIController pessoaController, Doacao doacao) {
        this.doacaoController = doacaoController;
        this.parent = parent;
        this.pessoaController = pessoaController;
        this.doacao = doacao;
        initComponents();
        configurarTela();
        preencherCampos();
    }
 
    private void configurarTela() {
        setTitle("SOSPatas - Editar doação");
        setLocationRelativeTo(null);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
 
        cmbTipo.setModel(new DefaultComboBoxModel(TipoDoacaoEnum.values()));
 
        cmbDoador.setModel(new DefaultComboBoxModel<>(pessoaController.findAll().toArray(new Pessoa[0])));
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
 
        txtData.setToolTipText("dd/MM/yyyy");
 
        cmbTipo.setFont(fonte);
        cmbDoador.setFont(fonte);
        txtData.setFont(fonte);
        txtValor.setFont(fonte);
        txtQuantidade.setFont(fonte);
        txtUnidade.setFont(fonte);
        txtDescricao.setFont(fonte);
 
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setForeground(new java.awt.Color(60, 52, 137));
        btnAtualizar.setBackground(java.awt.Color.WHITE);
        btnAtualizar.setOpaque(true);
        btnAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
 
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
    }
 
    private void preencherCampos() {
        cmbTipo.setSelectedItem(doacao.getTipo());
        cmbDoador.setSelectedItem(doacao.getDoador());
        if (doacao.getData() != null) {
            txtData.setText(doacao.getData().format(FORMATO_DATA));
        }
        if (doacao.getValor() != null) {
            txtValor.setText(doacao.getValor().toString());
        }
        if (doacao.getQuantidade() != null) {
            txtQuantidade.setText(String.valueOf(doacao.getQuantidade()));
        }
        if (doacao.getUnidade() != null) {
            txtUnidade.setText(doacao.getUnidade());
        }
        txtDescricao.setText(doacao.getDescricao());
        atualizarCamposPorTipo();
    }
    
    
    private void atualizarCamposPorTipo() {
        TipoDoacaoEnum tipo = (TipoDoacaoEnum) cmbTipo.getSelectedItem();
        boolean financeira = tipo == TipoDoacaoEnum.FINANCEIRA;
        txtValor.setEnabled(financeira);
        txtQuantidade.setEnabled(!financeira);
        txtUnidade.setEnabled(!financeira);
    }
    
       private void atualizar() {
        if (cmbDoador.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o doador.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o tipo de doação.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        TipoDoacaoEnum tipo = (TipoDoacaoEnum) cmbTipo.getSelectedItem();
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
            doacaoController.atualizar(doacao);
            JOptionPane.showMessageDialog(this, "Doação atualizada com sucesso!");
            parent.carregarTabela();
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        Doador = new javax.swing.JLabel();
        cmbDoador = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUnidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnAtualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo");

        cmbTipo.addActionListener(this::cmbTipoActionPerformed);

        Doador.setText("Doador");

        jLabel2.setText("Data");

        txtData.addActionListener(this::txtDataActionPerformed);

        jLabel3.setText("Valor");

        jLabel4.setText("Quantidade");

        jLabel5.setText("Unidade");

        jLabel6.setText("Descrição");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

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
                                .addComponent(btnAtualizar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
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
                    .addComponent(btnAtualizar)
                    .addComponent(btnCancelar))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
           atualizarCamposPorTipo();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
           atualizar();
    }//GEN-LAST:event_btnAtualizarActionPerformed

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
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCancelar;
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
