package br.com.ifba.gestaoanimal.logauditoria.view;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class LogAuditoriaListar extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LogAuditoriaListar.class.getName());
    private DefaultTableModel tableModel;
    private List<LogAuditoria> logs;
    private final LogAuditoriaIController logAuditoriaController;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LogAuditoriaListar(LogAuditoriaIController logAuditoriaController) {
        this.logAuditoriaController = logAuditoriaController;
        initComponents();
        configurarTabela();
        carregarTabela();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    private void configurarTabela() {
        setTitle("SOSPatas - Log de auditoria");

        tableModel = new DefaultTableModel(
            new String[]{"ID", "Usuário", "Ação", "Data/Hora"}, 0
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
        jTable1.setFont(fonte);

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

        painelBotoes.setBackground(Color.WHITE);

        btnAtualizar.setText(" Atualizar ");
        btnAtualizar.setForeground(new java.awt.Color(80, 80, 80));
        btnAtualizar.setBackground(java.awt.Color.WHITE);
        btnAtualizar.setFont(fonte);
        btnAtualizar.setOpaque(true);
        btnAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        txtBusca.setFont(fonte);
        txtBusca.addActionListener(this::txtBuscaActionPerformed);

        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        JLabel lblTitulo = new JLabel(" SOSPatas - Log de Auditoria");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);

        java.awt.Dimension tamanhoBotao = new java.awt.Dimension(110, 32);
        btnAtualizar.setPreferredSize(tamanhoBotao);
    }

    public void carregarTabela() {
        tableModel.setRowCount(0);
        logs = logAuditoriaController.findAll();
        for (LogAuditoria log : logs) {
            tableModel.addRow(new Object[]{
                log.getId(),
                log.getUsuario() != null ? log.getUsuario().getLogin() : "",
                log.getAcao(),
                log.getDataHora() != null ? log.getDataHora().format(FMT) : "",
               
            });
        }
    }

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        txtBusca.setText("");
        carregarTabela();
    }

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {
        aplicarFiltro();
    }

    private void aplicarFiltro() {
        if (logs == null) {
            return;
        }
        String termo = txtBusca.getText().trim().toLowerCase();
        tableModel.setRowCount(0);
        for (LogAuditoria log : logs) {
            boolean bateAcao = termo.isEmpty()
                    || (log.getAcao() != null && log.getAcao().toLowerCase().contains(termo));
            boolean bateUsuario = termo.isEmpty()
                    || (log.getUsuario() != null && log.getUsuario().getLogin() != null
                        && log.getUsuario().getLogin().toLowerCase().contains(termo));
            if (bateAcao || bateUsuario) {
                tableModel.addRow(new Object[]{
                    log.getId(),
                    log.getUsuario() != null ? log.getUsuario().getLogin() : "",
                    log.getAcao(),
                    log.getDataHora() != null ? log.getDataHora().format(FMT) : "",
                    log.getIp() != null ? log.getIp() : ""
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
            .addGap(0, 53, Short.MAX_VALUE)
        );

        btnAtualizar.setText("Atualizar");

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(btnAtualizar)
                .addGap(178, 178, 178))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtualizar)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
