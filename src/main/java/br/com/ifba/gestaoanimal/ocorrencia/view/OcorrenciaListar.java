package br.com.ifba.gestaoanimal.ocorrencia.view;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import br.com.ifba.gestaoanimal.ocorrencia.controller.OcorrenciaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class OcorrenciaListar extends javax.swing.JFrame {
 private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OcorrenciaListar.class.getName());
    private DefaultTableModel tableModel;
    private List<Ocorrencia> ocorrencias;
    private final OcorrenciaIController ocorrenciaController;
    private final PessoaController pessoaController;
 
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
 
    public OcorrenciaListar(OcorrenciaIController ocorrenciaController,
            PessoaController pessoaController) {
        this.ocorrenciaController = ocorrenciaController;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
        aplicarPermissoes();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }
 
    private void configurarTabela() {
        setTitle("SOSPatas - Listagem de ocorrências");
 
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Descrição", "Tipo", "Urgência", "Status", "Data Registro"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTable1.setModel(tableModel);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setRowHeight(24);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
 
        jTable1.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
        jTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jTable1.getTableHeader().setFont(fonteBold);
 
        jTable1.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
 
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fonte);
                String status = value != null ? value.toString() : "";
                switch (status) {
                    case "Aberta"          -> { label.setBackground(new java.awt.Color(250, 238, 218)); label.setForeground(new java.awt.Color(133, 79, 11)); }
                    case "Em atendimento"  -> { label.setBackground(new java.awt.Color(225, 237, 251)); label.setForeground(new java.awt.Color(24, 95, 165)); }
                    case "Concluída"       -> { label.setBackground(new java.awt.Color(234, 243, 222)); label.setForeground(new java.awt.Color(59, 109, 17)); }
                    case "Cancelada"       -> { label.setBackground(new java.awt.Color(250, 224, 224)); label.setForeground(new java.awt.Color(163, 45, 45)); }
                    default                -> { label.setBackground(java.awt.Color.WHITE);              label.setForeground(java.awt.Color.BLACK); }
                }
                label.setOpaque(true);
                return label;
            }
        });
 
        painelBotoes.setBackground(Color.WHITE);
 
        btnCadastrar.setText(" Novo ");
        btnCadastrar.setForeground(new java.awt.Color(59, 109, 17));
        btnCadastrar.setBackground(java.awt.Color.WHITE);
        btnCadastrar.setFont(fonte);
        btnCadastrar.setOpaque(true);
        btnCadastrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
 
        btnEditar.setText(" Editar ");
        btnEditar.setForeground(new java.awt.Color(60, 52, 137));
        btnEditar.setBackground(java.awt.Color.WHITE);
        btnEditar.setFont(fonte);
        btnEditar.setOpaque(true);
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
        btnEditar.addActionListener(this::btnEditarActionPerformed);
 
        btnExcluir.setText(" Desativar ");
        btnExcluir.setForeground(new java.awt.Color(163, 45, 45));
        btnExcluir.setBackground(java.awt.Color.WHITE);
        btnExcluir.setFont(fonte);
        btnExcluir.setOpaque(true);
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 45, 45), 1, true));
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
 
        btnAtualizar.setText(" Atualizar ");
        btnAtualizar.setForeground(new java.awt.Color(80, 80, 80));
        btnAtualizar.setBackground(java.awt.Color.WHITE);
        btnAtualizar.setFont(fonte);
        btnAtualizar.setOpaque(true);
        btnAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);
 
        txtBusca.setFont(fonte);
        txtBusca.addActionListener(this::txtBuscaActionPerformed);
 
        cmbFiltrarPorStatus.setFont(fonte);
        cmbFiltrarPorStatus.setModel(new DefaultComboBoxModel<>(
                Stream.concat(Stream.of("Todos"), Arrays.stream(StatusOcorrenciaEnum.values()).map(Enum::toString))
                        .toArray(String[]::new)
        ));
        cmbFiltrarPorStatus.addActionListener(this::cmbFiltrarPorStatusActionPerformed);
 
        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));
 
        JLabel lblTitulo = new JLabel(" SOSPatas - Gestão de Ocorrências");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);
 
        jTable1.setFont(fonte);
 
        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
        btnCadastrar.setPreferredSize(tamanhoBotao);
        btnEditar.setPreferredSize(tamanhoBotao);
        btnExcluir.setPreferredSize(tamanhoBotao);
        btnAtualizar.setPreferredSize(tamanhoBotao);
    }
 
    public void carregarTabela() {
        tableModel.setRowCount(0);
        ocorrencias = ocorrenciaController.findAll();
        for (Ocorrencia o : ocorrencias) {
            tableModel.addRow(new Object[]{
                o.getId(), o.getDescricao(), o.getTipo(), o.getUrgencia(), o.getStatus(),
                o.getDataRegistro() != null ? o.getDataRegistro().format(FMT) : ""
            });
        }
    }
 
    private void aplicarPermissoes() {
        btnCadastrar.setVisible(SessaoUsuario.temPermissao("CADASTRAR_OCORRENCIA"));
        btnEditar.setVisible(SessaoUsuario.temPermissao("EDITAR_OCORRENCIA"));
        btnExcluir.setVisible(SessaoUsuario.temPermissao("DESATIVAR_OCORRENCIA"));
    }
 
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        OcorrenciaCadastrar cadastrar = new OcorrenciaCadastrar(ocorrenciaController, pessoaController, this);
        cadastrar.setVisible(true);
    }
 
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma ocorrência para editar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        Ocorrencia ocorrencia = ocorrenciaController.findById(id);
        OcorrenciaEditar editar = new OcorrenciaEditar(ocorrenciaController, pessoaController, this, ocorrencia);
        editar.setVisible(true);
    }
 
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma ocorrência para desativar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String descricao = (String) tableModel.getValueAt(linha, 1);
        Long id = (Long) tableModel.getValueAt(linha, 0);
        int ok = JOptionPane.showConfirmDialog(this,
            "Desativar ocorrência \"" + descricao + "\" (ID: " + id + ")?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            ocorrenciaController.delete(id);
            carregarTabela();
        }
    }
 
    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        txtBusca.setText("");
        cmbFiltrarPorStatus.setSelectedIndex(0);
        carregarTabela();
    }
 
    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {
        aplicarFiltros();
    }
 
    private void cmbFiltrarPorStatusActionPerformed(java.awt.event.ActionEvent evt) {
        aplicarFiltros();
    }
 
    private void aplicarFiltros() {
        if (ocorrencias == null) {
            return;
        }
        String termo = txtBusca.getText().trim().toLowerCase();
        Object statusSelecionado = cmbFiltrarPorStatus.getSelectedItem();
        boolean filtrarStatus = statusSelecionado != null && !"Todos".equals(statusSelecionado);
 
        tableModel.setRowCount(0);
        for (Ocorrencia o : ocorrencias) {
            boolean bateTermo = termo.isEmpty()
                    || (o.getDescricao() != null && o.getDescricao().toLowerCase().contains(termo));
            boolean bateStatus = !filtrarStatus
                    || (o.getStatus() != null && o.getStatus().toString().equals(statusSelecionado));
            if (bateTermo && bateStatus) {
                tableModel.addRow(new Object[]{
                    o.getId(), o.getDescricao(), o.getTipo(), o.getUrgencia(), o.getStatus(),
                    o.getDataRegistro() != null ? o.getDataRegistro().format(FMT) : ""
                });
            }
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBarra = new javax.swing.JPanel();
        painelBotoes = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        cmbFiltrarPorStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        btnCadastrar.setText("Novo");

        btnExcluir.setText("Excluir");

        btnEditar.setText("Editar");

        btnAtualizar.setText("Atualizar");

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBusca)
                    .addComponent(cmbFiltrarPorStatus, 0, 305, Short.MAX_VALUE))
                .addGap(162, 162, 162)
                .addComponent(btnCadastrar)
                .addGap(39, 39, 39)
                .addComponent(btnEditar)
                .addGap(34, 34, 34)
                .addComponent(btnExcluir)
                .addGap(39, 39, 39)
                .addComponent(btnAtualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbFiltrarPorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar)
                            .addComponent(btnExcluir)
                            .addComponent(btnAtualizar)
                            .addComponent(btnEditar))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<String> cmbFiltrarPorStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
