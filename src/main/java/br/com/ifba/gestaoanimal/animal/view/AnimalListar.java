
package br.com.ifba.gestaoanimal.animal.view;

import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class AnimalListar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AnimalListar.class.getName());
    private DefaultTableModel tableModel;
    private final AnimalController animalController;
    
    public AnimalListar(AnimalController animalController) {
        this.animalController = animalController;
        initComponents();
        configurarTabela();
        carregarTabela();
    }
   
private void configurarTabela() {
    tableModel = new DefaultTableModel(
        new String[]{"ID", "Nome", "Espécie", "Raça", "Sexo", "Porte", "Status", "Data Entrada"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };
    tblAnimais.setModel(tableModel);
    tblAnimais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tblAnimais.getTableHeader().setReorderingAllowed(false);
    tblAnimais.setRowHeight(24);

    // ── Fontes ──
    java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
    java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);

    // ── Cabeçalho da tabela ──
    tblAnimais.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
    tblAnimais.getTableHeader().setForeground(java.awt.Color.WHITE);
    tblAnimais.getTableHeader().setFont(fonteBold);

    // ── Linhas alternadas + seleção roxa ──
    tblAnimais.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
    @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);  // ← centraliza
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

    // ── Badge colorido na coluna Status ──
    tblAnimais.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(fonte);
            String status = value != null ? value.toString() : "";
            switch (status) {
                case "DISPONIVEL"    -> { label.setBackground(new java.awt.Color(234, 243, 222)); label.setForeground(new java.awt.Color(59, 109, 17)); }
                case "EM_TRATAMENTO" -> { label.setBackground(new java.awt.Color(250, 238, 218)); label.setForeground(new java.awt.Color(133, 79, 11)); }
                case "EM_ADOCAO"     -> { label.setBackground(new java.awt.Color(225, 237, 251)); label.setForeground(new java.awt.Color(24, 95, 165)); }
                case "ADOTADO"       -> { label.setBackground(new java.awt.Color(209, 231, 221)); label.setForeground(new java.awt.Color(20, 108, 67)); }
                default              -> { label.setBackground(java.awt.Color.WHITE);              label.setForeground(java.awt.Color.BLACK); }
            }
            label.setOpaque(true);
            return label;
        }
    });

    // ── Painel de botões ──
    painelBotoes.setBackground(new java.awt.Color(245, 245, 250));

    // ── Botões com borda colorida ──
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

    // ── Campo de busca ──
   
    lblBusca.setFont(fonte);
    txtBusca.setFont(fonte);

    // ── Título roxo no topo ──
    JPanel painelTitulo = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    painelTitulo.setBackground(new java.awt.Color(60, 52, 137));
   
    add(painelTitulo, java.awt.BorderLayout.NORTH);

    // ── Fonte da tabela ──
    tblAnimais.setFont(fonte);
    
    java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
    btnNovo.setPreferredSize(tamanhoBotao);
    btnEditar.setPreferredSize(tamanhoBotao);
    btnDesativar.setPreferredSize(tamanhoBotao);
    btnAtualizar.setPreferredSize(tamanhoBotao);
}

    public void carregarTabela() {
        tableModel.setRowCount(0);
        List<Animal> animais = animalController.findAll();
        for (Animal a : animais) {
            tableModel.addRow(new Object[]{
                a.getId(), a.getNome(), a.getEspecie(), a.getRaca(),
                a.getSexo(), a.getPorte(), a.getStatus(),  a.getDataEntrada() != null 
        ? a.getDataEntrada().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        : ""
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesativar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        lblBusca = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnimais = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOSPatas - Listagem de animais");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDesativar.setText("Deletar");
        btnDesativar.addActionListener(this::btnDesativarActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        lblBusca.setText("Buscar:");

        txtBusca.addActionListener(this::txtBuscaActionPerformed);

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnNovo)
                .addGap(62, 62, 62)
                .addComponent(btnEditar)
                .addGap(68, 68, 68)
                .addComponent(btnDesativar)
                .addGap(57, 57, 57)
                .addComponent(btnAtualizar)
                .addGap(53, 53, 53)
                .addComponent(lblBusca)
                .addGap(18, 18, 18)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnDesativar)
                    .addComponent(btnAtualizar)
                    .addComponent(lblBusca)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(painelBotoes, java.awt.BorderLayout.PAGE_START);

        tblAnimais.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAnimais);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        AnimalCadastrar cadastrar = new AnimalCadastrar(animalController, this);
        cadastrar.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       int linha = tblAnimais.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um animal para editar.",
                "Nenhum selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        Animal animal = animalController.findById(id);
        AnimalEditar editar = new AnimalEditar(animalController, this, animal);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarActionPerformed
        int linha = tblAnimais.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um animal para desativar.",
                "Nenhum selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nome = (String) tableModel.getValueAt(linha, 1);
        Long id = (Long) tableModel.getValueAt(linha, 0);
        int ok = JOptionPane.showConfirmDialog(this,
            "Desativar " + nome + " (ID: " + id + ")?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            animalController.delete(id);
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
        List<Animal> animais = animalController.findByNome(nome);
        for (Animal a : animais) {
            tableModel.addRow(new Object[]{
                a.getId(), a.getNome(), a.getEspecie(), a.getRaca(),
                a.getSexo(), a.getPorte(), a.getStatus(), a.getDataEntrada()
            });
        }
    }//GEN-LAST:event_txtBuscaActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AnimalListar(new AnimalController()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDesativar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tblAnimais;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
