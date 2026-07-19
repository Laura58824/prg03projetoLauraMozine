package br.com.ifba.gestaoanimal.solicitacaovoluntario.view;
 
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
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
 
public class SolicitacaoVoluntarioListar extends javax.swing.JFrame {
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SolicitacaoVoluntarioListar.class.getName());
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
 
    private DefaultTableModel tableModel;
    private List<SolicitacaoVoluntario> solicitacoes;
    private final SolicitacaoVoluntarioIController solicitacaoVoluntarioController;
    private final PessoaController pessoaController;
 
    public SolicitacaoVoluntarioListar(SolicitacaoVoluntarioIController solicitacaoVoluntarioController,
            PessoaController pessoaController) {
        this.solicitacaoVoluntarioController = solicitacaoVoluntarioController;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
        aplicarPermissoes();
    }
 
    private void configurarTabela() {
        setTitle("SOSPatas - Solicitações de Voluntariado");
 
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Solicitante", "Data Solicitação", "Status", "Data Resposta"}, 0
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
 
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fonte);
                String status = value != null ? value.toString() : "";
                switch (status) {
                    case "Pendente" -> { label.setBackground(new java.awt.Color(250, 238, 218)); label.setForeground(new java.awt.Color(133, 79, 11)); }
                    case "Aprovada" -> { label.setBackground(new java.awt.Color(234, 243, 222)); label.setForeground(new java.awt.Color(59, 109, 17)); }
                    case "Recusada" -> { label.setBackground(new java.awt.Color(250, 224, 224)); label.setForeground(new java.awt.Color(163, 45, 45)); }
                    default         -> { label.setBackground(java.awt.Color.WHITE);              label.setForeground(java.awt.Color.BLACK); }
                }
                label.setOpaque(true);
                return label;
            }
        });
 
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    abrirAnalisar();
                }
            }
        });
 
        painelBotoes.setBackground(Color.WHITE);
 
        btnNovo.setText(" Nova Solicitação ");
        btnNovo.setForeground(new java.awt.Color(59, 109, 17));
        btnNovo.setBackground(java.awt.Color.WHITE);
        btnNovo.setFont(fonte);
        btnNovo.setOpaque(true);
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));
        btnNovo.addActionListener(this::btnNovaActionPerformed);
 
        btnAnalisar.setText(" Analisar ");
        btnAnalisar.setForeground(new java.awt.Color(60, 52, 137));
        btnAnalisar.setBackground(java.awt.Color.WHITE);
        btnAnalisar.setFont(fonte);
        btnAnalisar.setOpaque(true);
        btnAnalisar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
        btnAnalisar.addActionListener(this::btnAnalisarActionPerformed);
 
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
                Stream.concat(Stream.of("Todos"), Arrays.stream(StatusSolicitacaoEnum.values()).map(Enum::toString))
                        .toArray(String[]::new)
        ));
        cmbFiltrarPorStatus.addActionListener(this::cmbFiltrarPorStatusActionPerformed);
 
        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));
 
        JLabel lblTitulo = new JLabel(" SOSPatas - Solicitações de Voluntariado");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);
 
        jTable1.setFont(fonte);
 
        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(140, 32);
        btnNovo.setPreferredSize(tamanhoBotao);
        btnAnalisar.setPreferredSize(tamanhoBotao);
        btnAtualizar.setPreferredSize(tamanhoBotao);
    }
 
    public void carregarTabela() {
        tableModel.setRowCount(0);
        solicitacoes = solicitacaoVoluntarioController.findAll();
        for (SolicitacaoVoluntario s : solicitacoes) {
            tableModel.addRow(new Object[]{
                s.getId(),
                s.getSolicitante() != null ? s.getSolicitante().getNome() : "",
                s.getDataSolicitacao() != null ? s.getDataSolicitacao().format(FMT) : "",
                s.getStatus(),
                s.getDataResposta() != null ? s.getDataResposta().format(FMT) : ""
            });
        }
    }
 
    private void aplicarPermissoes() {
        btnNovo.setVisible(SessaoUsuario.temPermissao("CADASTRAR_SOLICITACAO_VOLUNTARIO"));
        btnAnalisar.setVisible(SessaoUsuario.temPermissao("ANALISAR_SOLICITACAO_VOLUNTARIO"));
    }
 
    private void btnNovaActionPerformed(java.awt.event.ActionEvent evt) {
        SolicitacaoVoluntarioCadastrar cadastrar = new SolicitacaoVoluntarioCadastrar(solicitacaoVoluntarioController, pessoaController, this);
        cadastrar.setVisible(true);
    }
 
    private void btnAnalisarActionPerformed(java.awt.event.ActionEvent evt) {
        abrirAnalisar();
    }
 
    private void abrirAnalisar() {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma solicitação para analisar.",
                "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        SolicitacaoVoluntario solicitacao = solicitacaoVoluntarioController.findById(id);
        SolicitacaoVoluntarioAnalisar analisar = new SolicitacaoVoluntarioAnalisar(solicitacaoVoluntarioController, solicitacao, this);
        analisar.setVisible(true);
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
        if (solicitacoes == null) {
            return;
        }
        String termo = txtBusca.getText().trim().toLowerCase();
        Object statusSelecionado = cmbFiltrarPorStatus.getSelectedItem();
        boolean filtrarStatus = statusSelecionado != null && !"Todos".equals(statusSelecionado);
 
        tableModel.setRowCount(0);
        for (SolicitacaoVoluntario s : solicitacoes) {
            String nome = s.getSolicitante() != null ? s.getSolicitante().getNome() : "";
            boolean bateTermo = termo.isEmpty() || nome.toLowerCase().contains(termo);
            boolean bateStatus = !filtrarStatus
                    || (s.getStatus() != null && s.getStatus().toString().equals(statusSelecionado));
            if (bateTermo && bateStatus) {
                tableModel.addRow(new Object[]{
                    s.getId(), nome,
                    s.getDataSolicitacao() != null ? s.getDataSolicitacao().format(FMT) : "",
                    s.getStatus(),
                    s.getDataResposta() != null ? s.getDataResposta().format(FMT) : ""
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
        cmbFiltrarPorStatus = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnAnalisar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        btnNovo.setText("Novo");

        btnAnalisar.setText("Analisar");

        btnAtualizar.setText("Atualizar");

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltrarPorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addGap(47, 47, 47)
                .addComponent(btnAnalisar)
                .addGap(44, 44, 44)
                .addComponent(btnAtualizar)
                .addGap(163, 163, 163))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbFiltrarPorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnAnalisar)
                            .addComponent(btnAtualizar))))
                .addContainerGap(30, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalisar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cmbFiltrarPorStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
