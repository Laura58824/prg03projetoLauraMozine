
package br.com.ifba.gestaoanimal.pessoa.view;
 
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
 
public class PessoaListar extends javax.swing.JFrame {
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PessoaListar.class.getName());
    private DefaultTableModel tableModel;
    private final PessoaController pessoaController;
 
    public PessoaListar(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
    }
 
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Nome", "CPF", "Email", "Telefone", "Endereço", "Data Nascimento", "Data Cadastro"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPessoas.setModel(tableModel);
        tblPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPessoas.getTableHeader().setReorderingAllowed(false);
        tblPessoas.setRowHeight(24);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
 
        tblPessoas.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
        tblPessoas.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblPessoas.getTableHeader().setFont(fonteBold);
 
        tblPessoas.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    setBackground(new java.awt.Color(238, 237, 254));
                    setForeground(new java.awt.Color(60, 52, 137));
                } else if (row % 2 == 0) {
                    setBackground(java.awt.Color.WHITE);
                    setForeground(java.awt.Color.BLACK);
                } else {
                    setBackground(new java.awt.Color(248, 248, 252));
                    setForeground(java.awt.Color.BLACK);
                }
                setOpaque(true);
                return this;
            }
        });
 
        painelBotoes.setBackground(Color.WHITE);
 
        btnNovo.setText(" Novo ");
        btnNovo.setForeground(new java.awt.Color(59, 109, 17));
        btnNovo.setBackground(java.awt.Color.WHITE);
        btnNovo.setFont(fonte);
        btnNovo.setOpaque(true);
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));
 
        btnEditar.setText(" Editar ");
        btnEditar.setForeground(new java.awt.Color(60, 52, 137));
        btnEditar.setBackground(java.awt.Color.WHITE);
        btnEditar.setFont(fonte);
        btnEditar.setOpaque(true);
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
 
        btnDesativar.setText(" Desativar ");
        btnDesativar.setForeground(new java.awt.Color(163, 45, 45));
        btnDesativar.setBackground(java.awt.Color.WHITE);
        btnDesativar.setFont(fonte);
        btnDesativar.setOpaque(true);
        btnDesativar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 45, 45), 1, true));
 
        btnAtualizar.setText(" Atualizar ");
        btnAtualizar.setForeground(new java.awt.Color(80, 80, 80));
        btnAtualizar.setBackground(java.awt.Color.WHITE);
        btnAtualizar.setFont(fonte);
        btnAtualizar.setOpaque(true);
        btnAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
 
        txtBusca.setFont(fonte);
 
        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));
 
        JLabel lblTitulo = new JLabel("SOSPatas - Gestão de Pessoas");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);
 
        tblPessoas.setFont(fonte);
 
        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
        btnNovo.setPreferredSize(tamanhoBotao);
        btnEditar.setPreferredSize(tamanhoBotao);
        btnDesativar.setPreferredSize(tamanhoBotao);
        btnAtualizar.setPreferredSize(tamanhoBotao);
    }
 
    public void carregarTabela() {
        tableModel.setRowCount(0);
        List<Pessoa> pessoas = pessoaController.findAll();
        for (Pessoa p : pessoas) {
            tableModel.addRow(new Object[]{
                p.getId(), p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone(),
                p.getEndereco(),
                p.getDataNascimento() != null
                    ? p.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                p.getDataCadastro() != null
                    ? p.getDataCadastro().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
            });
        }
    }
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        painelBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesativar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        painelBarra = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPessoas);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDesativar.setText("Deletar");
        btnDesativar.addActionListener(this::btnDesativarActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        txtBusca.addActionListener(this::txtBuscaActionPerformed);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnNovo)
                .addGap(42, 42, 42)
                .addComponent(btnEditar)
                .addGap(35, 35, 35)
                .addComponent(btnDesativar)
                .addGap(38, 38, 38)
                .addComponent(btnAtualizar)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnDesativar)
                    .addComponent(btnAtualizar))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1069, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
         PessoaCadastrar cadastrar = new PessoaCadastrar(pessoaController, this);
        cadastrar.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         int linha = tblPessoas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para editar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        Pessoa pessoa = pessoaController.findById(id);
        PessoaEditar editar = new PessoaEditar(pessoaController, this, pessoa);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarActionPerformed
         int linha = tblPessoas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para desativar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nome = (String) tableModel.getValueAt(linha, 1);
        Long id = (Long) tableModel.getValueAt(linha, 0);
        int ok = JOptionPane.showConfirmDialog(this,
            "Desativar " + nome + " (ID: " + id + ")?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            pessoaController.delete(id);
            carregarTabela();
        }
    }//GEN-LAST:event_btnDesativarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        carregarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
         String nome = txtBusca.getText().trim();
        if (nome.isEmpty()) { carregarTabela(); return; }
        tableModel.setRowCount(0);
        List<Pessoa> pessoas = pessoaController.findByNome(nome);
        for (Pessoa p : pessoas) {
            tableModel.addRow(new Object[]{
                p.getId(), p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone(),
                p.getEndereco(),
                p.getDataNascimento() != null
                    ? p.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                p.getDataCadastro() != null
                    ? p.getDataCadastro().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
            });
        }
    }//GEN-LAST:event_txtBuscaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new PessoaListar(new PessoaController()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDesativar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
