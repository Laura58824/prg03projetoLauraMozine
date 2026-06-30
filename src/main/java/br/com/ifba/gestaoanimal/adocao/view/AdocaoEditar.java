package br.com.ifba.gestaoanimal.adocao.view;

import br.com.ifba.gestaoanimal.adocao.controller.AdocaoController;
import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class AdocaoEditar extends javax.swing.JFrame {

    private final AdocaoController adocaoController;
    private final AnimalController animalController;
    private final PessoaController pessoaController;
    private final AdocaoListar parent;
    private final Adocao adocao;

    public AdocaoEditar(AdocaoController adocaoController, AnimalController animalController,
            PessoaController pessoaController, AdocaoListar parent, Adocao adocao) {
        this.adocaoController = adocaoController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.parent = parent;
        this.adocao = adocao;
        initComponents();
        configurarTela();
        preencherCampos();
    }

    private void configurarTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SOSPatas — editar adoção");
        setLocationRelativeTo(null);

        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);

        
         cmbAnimal.setModel(new DefaultComboBoxModel<>(animalController.findAll().toArray(Animal[]::new)));

        cmbAdotante.setModel(new DefaultComboBoxModel<>(pessoaController.findAll().toArray(Pessoa[]::new)));

        cmbResponsavel.setModel(new DefaultComboBoxModel<>(pessoaController.findAll().toArray(Pessoa[]::new)));
        cmbStatus.setModel(new DefaultComboBoxModel<>(StatusAdocaoEnum.values()));

        cmbAnimal.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Animal a) {
                    setText(a.getNome());
                }
                return this;
            }
        });

        javax.swing.DefaultListCellRenderer rendererPessoa = new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pessoa p) {
                    setText(p.getNome());
                }
                return this;
            }
        };
        cmbAdotante.setRenderer(rendererPessoa);
        cmbResponsavel.setRenderer(rendererPessoa);

        cmbAnimal.setFont(fonte);
        cmbAdotante.setFont(fonte);
        cmbResponsavel.setFont(fonte);
        cmbStatus.setFont(fonte);
        txtDataConclusao.setFont(fonte);
        txtObs.setFont(fonte);
        txtMotivoRecusa.setFont(fonte);
        btnSalvar.setFont(fonte);
        btnCancelar.setFont(fonte);

        btnSalvar.setText("Salvar");
        btnSalvar.setForeground(new java.awt.Color(60, 52, 137));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));

        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));

        txtDataConclusao.setToolTipText("dd/MM/yyyy");
    }

    private void preencherCampos() {
        cmbAnimal.setSelectedItem(adocao.getAnimal());
        cmbAdotante.setSelectedItem(adocao.getAdotante());
        cmbResponsavel.setSelectedItem(adocao.getResponsavel());
        cmbStatus.setSelectedItem(adocao.getStatus());
        txtObs.setText(adocao.getObservacoesEntrevista() != null ? adocao.getObservacoesEntrevista() : "");
        txtMotivoRecusa.setText(adocao.getMotivoRecusa() != null ? adocao.getMotivoRecusa() : "");
        if (adocao.getDataConclusao() != null) {
            txtDataConclusao.setText(adocao.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    private void salvar() {
        
        if (cmbAnimal.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o animal.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbAdotante.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o adotante.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbResponsavel.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o responsável.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbStatus.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o status.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        Pessoa adotante = (Pessoa) cmbAdotante.getSelectedItem();
        Pessoa responsavel = (Pessoa) cmbResponsavel.getSelectedItem();
        StatusAdocaoEnum status = (StatusAdocaoEnum) cmbStatus.getSelectedItem();
 
        
        if (status == StatusAdocaoEnum.RECUSADA && txtMotivoRecusa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o motivo da recusa.",
                    "Campo obrigatorio", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        
        if ((status == StatusAdocaoEnum.CONCLUIDA|| status == StatusAdocaoEnum.RECUSADA)
                && txtDataConclusao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data de conclusao para este status.",
                    "Campo obrigatorio", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        adocao.setAdotante(adotante);
        adocao.setResponsavel(responsavel);
        adocao.setStatus(status);
        adocao.setObservacoesEntrevista(txtObs.getText().trim());
        adocao.setMotivoRecusa(txtMotivoRecusa.getText().trim());
 
        String dataConclusaoStr = txtDataConclusao.getText().trim();
        if (!dataConclusaoStr.isEmpty()) {
            try {
                LocalDate data = LocalDate.parse(dataConclusaoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
               
                if (adocao.getDataAbertura() != null && data.isBefore(adocao.getDataAbertura())) {
                    JOptionPane.showMessageDialog(this,
                            "Data de conclusao nao pode ser anterior a data de abertura ("
                            + adocao.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ").",
                            "Data invalida", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                adocao.setDataConclusao(data);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de conclusão inválida. Use o formato dd/MM/yyyy.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            adocao.setDataConclusao(null);
        }
 
        adocaoController.update(adocao);
        JOptionPane.showMessageDialog(this, "Adoção atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        parent.carregarTabela();
        dispose();
    }

    private void cancelar() {
        dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbAnimal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbAdotante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbResponsavel = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDataConclusao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivoRecusa = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jLabel1.setText("Animal*");

        jLabel2.setText("Status*");

        jLabel3.setText("Adotante*");

        jLabel4.setText("Responsavel*");

        jLabel5.setText("Data de conclusão");

        jLabel6.setText("Observações da entrevista");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        jLabel7.setText("Motivo da recusa");

        txtMotivoRecusa.setColumns(20);
        txtMotivoRecusa.setRows(5);
        jScrollPane2.setViewportView(txtMotivoRecusa);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbAdotante, 0, 255, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(cmbAnimal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDataConclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(cmbStatus, 0, 255, Short.MAX_VALUE)
                            .addComponent(cmbResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(32, 32, 32)
                        .addComponent(btnSalvar)
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAdotante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDataConclusao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public static void main(String args[]) { }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoa> cmbAdotante;
    private javax.swing.JComboBox<Animal> cmbAnimal;
    private javax.swing.JComboBox<Pessoa> cmbResponsavel;
    private javax.swing.JComboBox<StatusAdocaoEnum> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtDataConclusao;
    private javax.swing.JTextArea txtMotivoRecusa;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables
}
