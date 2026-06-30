package br.com.ifba.gestaoanimal.registrosaude.view;
 
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
 
public class RegistroSaudeListar extends javax.swing.JFrame {
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroSaudeListar.class.getName());
    private DefaultTableModel tableModel;
    private List<RegistroSaude> registros;
    private final PessoaController pessoaController;
    private final RegistroSaudeController registroSaudeController;
    private final AnimalController animalController;
 
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    public RegistroSaudeListar(RegistroSaudeController registroSaudeController,
                               AnimalController animalController,
                               PessoaController pessoaController) {
        this.registroSaudeController = registroSaudeController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
    }
 
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Animal", "Tipo", "Data Realização", "Próxima Dose", "Responsável"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
 
        tblRegistros.setModel(tableModel);
        tblRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.setRowHeight(24);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
 
        tblRegistros.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
        tblRegistros.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblRegistros.getTableHeader().setFont(fonteBold);
 
        tblRegistros.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
 
        tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setWidth(0);
 
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
 
        JLabel lblTitulo = new JLabel("SOSPatas - Registros de Saúde");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);
 
        tblRegistros.setFont(fonte);
 
        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
        btnNovo.setPreferredSize(tamanhoBotao);
        btnEditar.setPreferredSize(tamanhoBotao);
        btnExcluir.setPreferredSize(tamanhoBotao);
        btnAtualizar.setPreferredSize(tamanhoBotao);
 
        String[] opcoes = Stream.concat(
            Stream.of("Todos os tipos"),
            Arrays.stream(TipoProcedimentoEnum.values()).map(TipoProcedimentoEnum::toString)
        ).toArray(String[]::new);
        cmbFiltroTipo.setModel(new javax.swing.DefaultComboBoxModel<>(opcoes));
        cmbFiltroTipo.setFont(fonte);
 
        txtBusca.setFont(fonte);
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarTabela();
            }
        });
 
        cmbFiltroTipo.addActionListener(e -> filtrarTabela());
 
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        btnEditar.addActionListener(this::btnEditarActionPerformed);
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);
    }
 
    public void carregarTabela() {
        registros = registroSaudeController.findAll();
        filtrarTabela();
    }
 
        private void filtrarTabela() {
        String busca = txtBusca.getText().trim().toLowerCase();
        String tipoSelecionado = cmbFiltroTipo.getSelectedItem().toString();

        tableModel.setRowCount(0);

        for (RegistroSaude r : registros) {
            boolean atendeBusca = busca.isEmpty()
                    || (r.getAnimal() != null && r.getAnimal().getNome().toLowerCase().contains(busca))
                    || (r.getResponsavel() != null && r.getResponsavel().getNome().toLowerCase().contains(busca));

            boolean atendeTipo = "Todos os tipos".equals(tipoSelecionado)
                    || (r.getTipo() != null && r.getTipo().toString().equals(tipoSelecionado));

            if (atendeBusca && atendeTipo) {
                tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getAnimal() != null ? r.getAnimal().getNome() : "",
                    r.getTipo(),
                    r.getDataRealizacao() != null ? r.getDataRealizacao().format(FMT) : "",
                    r.getDataProximaDose() != null ? r.getDataProximaDose().format(FMT) : "",
                    r.getResponsavel() != null ? r.getResponsavel().getNome() : ""
                });
            }
        }
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
         new RegistroSaudeCadastrar(registroSaudeController, animalController, pessoaController, this).setVisible(true);
    }
 
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = tblRegistros.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para editar.",
                "Nenhum selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        RegistroSaude registro = registros.stream()
                .filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        if (registro != null)
            new RegistroSaudeEditar(registroSaudeController, animalController, this, registro).setVisible(true);
    }
 
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = tblRegistros.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir.",
                "Nenhum selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        int ok = JOptionPane.showConfirmDialog(this,
            "Excluir o registro ID: " + id + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                registroSaudeController.delete(id);
                JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        carregarTabela();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoes = new javax.swing.JPanel();
        painelBarra = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        cmbFiltroTipo = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        pane = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        cmbFiltroTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnNovo.setText("Novo");

        btnAtualizar.setText("Atualizar");

        btnEditar.setText("Editar");

        btnExcluir.setText("Excluir");

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBusca)
                    .addComponent(cmbFiltroTipo, 0, 309, Short.MAX_VALUE))
                .addGap(155, 155, 155)
                .addComponent(btnNovo)
                .addGap(41, 41, 41)
                .addComponent(btnEditar)
                .addGap(35, 35, 35)
                .addComponent(btnExcluir)
                .addGap(32, 32, 32)
                .addComponent(btnAtualizar)
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesLayout.createSequentialGroup()
                        .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnEditar)
                            .addComponent(btnExcluir)
                            .addComponent(btnAtualizar))
                        .addGap(1, 1, 1)))
                .addComponent(cmbFiltroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
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
        pane.setViewportView(tblRegistros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cmbFiltroTipo;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JScrollPane pane;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
