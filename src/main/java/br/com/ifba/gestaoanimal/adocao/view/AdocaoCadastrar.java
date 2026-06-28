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

public class AdocaoCadastrar extends javax.swing.JFrame {

    private final AdocaoController adocaoController;
    private final AnimalController animalController;
    private final PessoaController pessoaController;
    private final AdocaoListar parent;

    public AdocaoCadastrar(AdocaoController adocaoController, AnimalController animalController,
            PessoaController pessoaController, AdocaoListar parent) {
        this.adocaoController = adocaoController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.parent = parent;
        initComponents();
        configurarTela();
    }

    private void configurarTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SOSPatas — nova adoção");
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

        btnSalvar.setText("Cadastrar");
        btnSalvar.setForeground(new java.awt.Color(59, 109, 17));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));

        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));

        txtDataConclusao.setToolTipText("dd/MM/yyyy (deixe em branco se ainda não concluída)");
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

        Adocao adocao = new Adocao();
        adocao.setAnimal((Animal) cmbAnimal.getSelectedItem());
        adocao.setAdotante((Pessoa) cmbAdotante.getSelectedItem());
        adocao.setResponsavel((Pessoa) cmbResponsavel.getSelectedItem());
        adocao.setStatus((StatusAdocaoEnum) cmbStatus.getSelectedItem());
        adocao.setDataAbertura(LocalDate.now());
        adocao.setObservacoesEntrevista(txtObs.getText().trim());
        adocao.setMotivoRecusa(txtMotivoRecusa.getText().trim());

        String dataConclusaoStr = txtDataConclusao.getText().trim();
        if (!dataConclusaoStr.isEmpty()) {
            try {
                LocalDate data = LocalDate.parse(dataConclusaoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                adocao.setDataConclusao(data);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de conclusão inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        adocaoController.save(adocao);
        JOptionPane.showMessageDialog(this, "Adoção cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        cmbAnimal = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        cmbAdotante = new javax.swing.JComboBox<>();
        cmbResponsavel = new javax.swing.JComboBox<>();
        txtDataConclusao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivoRecusa = new javax.swing.JTextArea();

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

        jLabel7.setText("Motivo da recusa");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSalvar.setText("Cadastrar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        txtMotivoRecusa.setColumns(20);
        txtMotivoRecusa.setRows(5);
        jScrollPane2.setViewportView(txtMotivoRecusa);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    
    public static void main(String args[]) {
      
    }

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
