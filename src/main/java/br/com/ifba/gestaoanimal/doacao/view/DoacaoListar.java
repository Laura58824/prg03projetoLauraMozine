package br.com.ifba.gestaoanimal.doacao.view;
 
import br.com.ifba.gestaoanimal.doacao.controller.DoacaoIController;
import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.springframework.stereotype.Component;
 
@Component
public class DoacaoListar extends javax.swing.JFrame {
 
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    private DefaultTableModel tableModel;
    private final DoacaoIController doacaoController;
    private final PessoaIController pessoaController;
 
    public DoacaoListar(DoacaoIController doacaoController, PessoaIController pessoaController) {
        this.doacaoController = doacaoController;
        this.pessoaController = pessoaController;
        initComponents();
        configurarTabela();
        carregarTabela();
        aplicarPermissoes();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }
 
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Doador", "Tipo", "Valor/Quantidade", "Data"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDoacoes.setModel(tableModel);
        tblDoacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDoacoes.getTableHeader().setReorderingAllowed(false);
        tblDoacoes.setRowHeight(24);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
        tblDoacoes.setFont(fonte);
 
        tblDoacoes.getTableHeader().setBackground(new java.awt.Color(60, 52, 137));
        tblDoacoes.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblDoacoes.getTableHeader().setFont(fonteBold);
 
        tblDoacoes.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
 
        painelBotoes.setBackground(java.awt.Color.WHITE);
 
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
 
        txtBusca.setFont(fonte);
 
        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));
        JLabel lblTitulo = new JLabel(" SOSPatas - Gestão de Doações");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);
    }
 
    public void carregarTabela() {
        preencherTabela(doacaoController.findAll());
    }
 
    private void preencherTabela(List<Doacao> doacoes) {
        tableModel.setRowCount(0);
        for (Doacao d : doacoes) {
            String valorOuQtd = d.getValor() != null
                    ? "R$ " + d.getValor().setScale(2, BigDecimal.ROUND_HALF_UP)
                    : (d.getQuantidade() != null ? d.getQuantidade() + " " + d.getUnidade() : "-");
            tableModel.addRow(new Object[]{
                d.getId(),
                d.getDoador() != null ? d.getDoador().getNome() : "-",
                d.getTipo(),
                valorOuQtd,
                d.getData() != null ? d.getData().format(FORMATO_DATA) : ""
            });
        }
    }
 
    private void aplicarPermissoes() {
        btnNovo.setVisible(SessaoUsuario.temPermissao("CADASTRAR_DOACAO"));
        btnEditar.setVisible(SessaoUsuario.temPermissao("EDITAR_DOACAO"));
        btnExcluir.setVisible(SessaoUsuario.temPermissao("DESATIVAR_DOACAO"));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoacoes = new javax.swing.JTable();
        painelBotoes = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        painelBarra = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblDoacoes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDoacoes);

        txtBusca.addActionListener(this::txtBuscaActionPerformed);
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(btnNovo)
                .addGap(42, 42, 42)
                .addComponent(btnEditar)
                .addGap(47, 47, 47)
                .addComponent(btnExcluir)
                .addGap(37, 37, 37)
                .addComponent(btnAtualizar)
                .addContainerGap(260, Short.MAX_VALUE))
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesLayout.createSequentialGroup()
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(painelBotoesLayout.createSequentialGroup()
                        .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnNovo)
                            .addComponent(btnExcluir)
                            .addComponent(btnAtualizar))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
       
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
         DoacaoCadastrar cadastrar = new DoacaoCadastrar(doacaoController, this, pessoaController);
        cadastrar.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int linha = tblDoacoes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma doação para editar.",
                    "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        Doacao doacao = doacaoController.findById(id);
        DoacaoEditar editar = new DoacaoEditar(doacaoController, this, pessoaController, doacao);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
          int linha = tblDoacoes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma doação para excluir.",
                    "Nenhuma selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Long id = (Long) tableModel.getValueAt(linha, 0);
        String doador = (String) tableModel.getValueAt(linha, 1);
        int ok = JOptionPane.showConfirmDialog(this,
                "Excluir a doação de " + doador + " (ID: " + id + ")?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                doacaoController.excluir(id);
                carregarTabela();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        carregarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
           String nome = txtBusca.getText().trim();
        if (nome.isEmpty()) {
            carregarTabela();
            return;
        }
        List<Doacao> filtradas = doacaoController.findAll().stream()
                .filter(d -> d.getDoador() != null && d.getDoador().getNome() != null
                        && d.getDoador().getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
        preencherTabela(filtradas);
    }//GEN-LAST:event_txtBuscaActionPerformed
    
    
    public static void main(String args[]) {}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tblDoacoes;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
