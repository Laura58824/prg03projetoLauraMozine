package br.com.ifba.gestaoanimal.solicitacaovoluntario.view;

import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.controller.SolicitacaoVoluntarioIController;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import java.awt.Color;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SolicitacaoVoluntarioAnalisar extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SolicitacaoVoluntarioAnalisar.class.getName());
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final SolicitacaoVoluntarioIController solicitacaoVoluntarioController;
    private final SolicitacaoVoluntario solicitacao;
    private final SolicitacaoVoluntarioListar parent;

    public SolicitacaoVoluntarioAnalisar(SolicitacaoVoluntarioIController solicitacaoVoluntarioController,
            SolicitacaoVoluntario solicitacao, SolicitacaoVoluntarioListar parent) {
        this.solicitacaoVoluntarioController = solicitacaoVoluntarioController;
        this.solicitacao = solicitacao;
        this.parent = parent;
        initComponents();
        configurarTela();
        carregarDados();
    }

    private void configurarTela() {
        setTitle("SOSPatas — analisar solicitação de voluntariado");
        setLocationRelativeTo(null);

        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);

        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 14));
        JLabel lblTitulo = new JLabel(" SOSPatas - Analisar Solicitação");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 18));
        painelBarra.add(lblTitulo);

        for (JComponent campo : new JComponent[]{lblSolicitante, lblMotivacao, jLabel1,
                txtSolicitante, txtMotivacao, txtObservacoes, btnAprovar, btnRecusar}) {
            campo.setFont(fonte);
        }

        txtSolicitante.setEditable(false);
        txtSolicitante.setLineWrap(true);
        txtSolicitante.setWrapStyleWord(true);
        txtSolicitante.setBackground(new java.awt.Color(245, 245, 245));

        txtMotivacao.setEditable(false);
        txtMotivacao.setLineWrap(true);
        txtMotivacao.setWrapStyleWord(true);
        txtMotivacao.setBackground(new java.awt.Color(245, 245, 245));

        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);

        btnAprovar.setText("Aprovar");
        btnAprovar.setForeground(new java.awt.Color(59, 109, 17));
        btnAprovar.setBackground(java.awt.Color.WHITE);
        btnAprovar.setOpaque(true);
        btnAprovar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));
        btnAprovar.addActionListener(this::btnAprovarActionPerformed);

        btnRecusar.setText("Recusar");
        btnRecusar.setForeground(new java.awt.Color(163, 45, 45));
        btnRecusar.setBackground(java.awt.Color.WHITE);
        btnRecusar.setOpaque(true);
        btnRecusar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 45, 45), 1, true));
        btnRecusar.addActionListener(this::btnRecusarActionPerformed);
    }

    private void carregarDados() {
        String nomeSolicitante = solicitacao.getSolicitante() != null ? solicitacao.getSolicitante().getNome() : "—";
        String dataSolicitacao = solicitacao.getDataSolicitacao() != null ? solicitacao.getDataSolicitacao().format(FMT) : "—";

        txtSolicitante.setText(
                "Solicitante: " + nomeSolicitante
                + "\nData da solicitação: " + dataSolicitacao
                + "\nStatus atual: " + solicitacao.getStatus()
        );

        txtMotivacao.setText(solicitacao.getMotivacao() != null ? solicitacao.getMotivacao() : "—");

        if (solicitacao.getObservacaoAdmin() != null && !solicitacao.getObservacaoAdmin().isBlank()) {
            txtObservacoes.setText(solicitacao.getObservacaoAdmin());
        }

        boolean jaAnalisada = solicitacao.getStatus() != StatusSolicitacaoEnum.PENDENTE;
        boolean podeAnalisar = SessaoUsuario.temPermissao("ANALISAR_SOLICITACAO_VOLUNTARIO");

        if (jaAnalisada || !podeAnalisar) {
            txtObservacoes.setEditable(false);
            btnAprovar.setVisible(false);
            btnRecusar.setVisible(false);
        }
    }

    private String obterObservacao() {
        String texto = txtObservacoes.getText().trim();
        return texto.isEmpty() ? null : texto;
    }

    private void aprovar() {
        int ok = JOptionPane.showConfirmDialog(this,
                "Aprovar a solicitação de " + solicitacao.getSolicitante().getNome() + "?",
                "Confirmar aprovação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ok != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            var analisadoPor = SessaoUsuario.getUsuarioLogado() != null ? SessaoUsuario.getUsuarioLogado().getPessoa() : null;
            solicitacaoVoluntarioController.aprovar(solicitacao.getId(), analisadoPor, obterObservacao());
            JOptionPane.showMessageDialog(this, "Solicitação aprovada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            parent.carregarTabela();
            dispose();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao aprovar solicitação: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recusar() {
        int ok = JOptionPane.showConfirmDialog(this,
                "Recusar a solicitação de " + solicitacao.getSolicitante().getNome() + "?",
                "Confirmar recusa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            var analisadoPor = SessaoUsuario.getUsuarioLogado() != null ? SessaoUsuario.getUsuarioLogado().getPessoa() : null;
            solicitacaoVoluntarioController.recusar(solicitacao.getId(), analisadoPor, obterObservacao());
            JOptionPane.showMessageDialog(this, "Solicitação recusada.", "Concluído", JOptionPane.INFORMATION_MESSAGE);
            parent.carregarTabela();
            dispose();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao recusar solicitação: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAprovarActionPerformed(java.awt.event.ActionEvent evt) {
        aprovar();
    }

    private void btnRecusarActionPerformed(java.awt.event.ActionEvent evt) {
        recusar();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBarra = new javax.swing.JPanel();
        lblSolicitante = new javax.swing.JLabel();
        lblMotivacao = new javax.swing.JLabel();
        btnRecusar = new javax.swing.JButton();
        btnAprovar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivacao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSolicitante = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        lblSolicitante.setText("Solicitante*");

        lblMotivacao.setText("Motivação*");

        btnRecusar.setText("Recusar");

        btnAprovar.setText("Aprovar");

        txtMotivacao.setColumns(20);
        txtMotivacao.setRows(5);
        jScrollPane1.setViewportView(txtMotivacao);

        txtSolicitante.setColumns(20);
        txtSolicitante.setRows(5);
        jScrollPane2.setViewportView(txtSolicitante);

        jLabel1.setText("Observações");

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane3.setViewportView(txtObservacoes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(lblMotivacao)
                            .addComponent(lblSolicitante)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRecusar)
                        .addGap(27, 27, 27)
                        .addComponent(btnAprovar)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSolicitante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(lblMotivacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRecusar)
                    .addComponent(btnAprovar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprovar;
    private javax.swing.JButton btnRecusar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMotivacao;
    private javax.swing.JLabel lblSolicitante;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JTextArea txtMotivacao;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextArea txtSolicitante;
    // End of variables declaration//GEN-END:variables
}
