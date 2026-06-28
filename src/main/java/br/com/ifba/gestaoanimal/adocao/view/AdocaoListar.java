package br.com.ifba.gestaoanimal.adocao.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AdocaoListar extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdocaoListar.class.getName());
    private DefaultTableModel tableModel;
    private final AdocaoController adocaoController;
    private final AnimalController animalController;
    private final PessoaController pessoaController;

    public AdocaoListar(AdocaoController adocaoController, AnimalController animalController, PessoaController pessoaController) {
        this.adocaoController = adocaoController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
    }

    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Animal", "Adotante", "Responsável", "Data Abertura", "Data Conclusão", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblAdocoes.setModel(tableModel);
        tblAdocoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAdocoes.getTableHeader().setReorderingAllowed(false);
        tblAdocoes.setRowHeight(24);

        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);

        tblAdocoes.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
        tblAdocoes.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblAdocoes.getTableHeader().setFont(fonteBold);

        tblAdocoes.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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

        btnExcluir.setText(" Excluir ");
        btnExcluir.setForeground(new java.awt.Color(163, 45, 45));
        btnExcluir.setBackground(java.awt.Color.WHITE);
        btnExcluir.setFont(fonte);
        btnExcluir.setOpaque(true);
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 45, 45), 1, true));

        btnAtualizar.setText(" Atualizar ");
        btnAtualizar.setForeground(new java.awt.Color(80, 80, 80));
        btnAtualizar.setBackground(java.awt.Color.WHITE);
        btnAtualizar.setFont(fonte);
        btnAtualizar.setOpaque(true);
        btnAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));

        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        JLabel lblTitulo = new JLabel("SOSPatas - Gestão de Adoções");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);

        tblAdocoes.setFont(fonte);

        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
        btnNovo.setPreferredSize(tamanhoBotao);
        btnEditar.setPreferredSize(tamanhoBotao);
        btnExcluir.setPreferredSize(tamanhoBotao);
        btnAtualizar.setPreferredSize(tamanhoBotao);
    }

    public void carregarTabela() {
        tableModel.setRowCount(0);
        List<Adocao> adocoes = adocaoController.findAll();
        for (Adocao a : adocoes) {
            tableModel.addRow(new Object[]{
                a.getId(),
                a.getAnimal() != null ? a.getAnimal().getNome() : "",
                a.getAdotante() != null ? a.getAdotante().getNome() : "",
                a.getResponsavel() != null ? a.getResponsavel().getNome() : "",
                a.getDataAbertura() != null
                    ? a.getDataAbertura().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                a.getDataConclusao() != null
                    ? a.getDataConclusao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                a.getStatus() != null ? a.getStatus().toString() : ""
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdocoes = new javax.swing.JTable();
        painelBarra = new javax.swing.JPanel();
        painelBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblAdocoes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAdocoes);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbStatus, 0, 372, Short.MAX_VALUE)
                    .addComponent(txtBusca))
                .addGap(113, 113, 113)
                .addComponent(btnNovo)
                .addGap(49, 49, 49)
                .addComponent(btnEditar)
                .addGap(47, 47, 47)
                .addComponent(btnExcluir)
                .addGap(47, 47, 47)
                .addComponent(btnAtualizar)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnEditar)
                            .addComponent(btnExcluir)
                            .addComponent(btnAtualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBarraLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        AdocaoCadastrar cadastrar = new AdocaoCadastrar(adocaoController, animalController, pessoaController, this);
        cadastrar.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         int linha = tblAdocoes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma adoção para editar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        Adocao adocao = adocaoController.findById(id);
        AdocaoEditar editar = new AdocaoEditar(adocaoController, animalController, pessoaController, this, adocao);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha = tblAdocoes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma adoção para excluir.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        int ok = JOptionPane.showConfirmDialog(this,
            "Excluir a adoção ID: " + id + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            adocaoController.delete(id);
            carregarTabela();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        carregarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

   
    public static void main(String args[]) {}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tblAdocoes;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
