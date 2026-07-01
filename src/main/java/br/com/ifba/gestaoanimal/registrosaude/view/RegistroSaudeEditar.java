package br.com.ifba.gestaoanimal.registrosaude.view;

import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.pessoa.controller.PessoaController;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.registrosaude.controller.RegistroSaudeController;
import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RegistroSaudeEditar extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroSaudeEditar.class.getName());
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final RegistroSaudeController registroSaudeController;
    private final AnimalController animalController;
    private final PessoaController pessoaController;
    private final RegistroSaudeListar parent;
    private final RegistroSaude registro;

    public RegistroSaudeEditar(RegistroSaudeController registroSaudeController,
            AnimalController animalController,
            PessoaController pessoaController,
            RegistroSaudeListar parent,
            RegistroSaude registro) {
        this.registroSaudeController = registroSaudeController;
        this.animalController = animalController;
        this.pessoaController = pessoaController;
        this.parent = parent;
        this.registro = registro;
        initComponents();
        configurarTela();
        preencherCampos();
    }

    private void configurarTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SOSPatas — Editar Registro de Saúde");
        setLocationRelativeTo(null);

        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);

        painelBarra.setBackground(new java.awt.Color(60, 52, 137));
        painelBarra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));
        JLabel lblTitulo = new JLabel("SOSPatas - Editar Registro de Saúde");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Palatino Linotype", java.awt.Font.BOLD, 20));
        painelBarra.add(lblTitulo);

        cmbAnimal.setModel(new DefaultComboBoxModel<>(animalController.findAll().toArray(Animal[]::new)));
        cmbAnimal.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Animal animal) {
                    setText(animal.getNome());
                }
                return this;
            }
        });

        cmbProcedimento.setModel(new DefaultComboBoxModel<>(TipoProcedimentoEnum.values()));

        cmbResponsavel.setModel(new DefaultComboBoxModel<>(pessoaController.findAll().toArray(Pessoa[]::new)));
        cmbResponsavel.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pessoa pessoa) {
                    setText(pessoa.getNome());
                }
                return this;
            }
        });

        cmbAnimal.setFont(fonte);
        cmbProcedimento.setFont(fonte);
        cmbResponsavel.setFont(fonte);
        txtDataRealizacao.setFont(fonte);
        txtProximaDose.setFont(fonte);
        txtaDescricao.setFont(fonte);
        txtaObservacoes.setFont(fonte);

        btnCadastrar.setText(" Salvar ");
        btnCadastrar.setForeground(new java.awt.Color(59, 109, 17));
        btnCadastrar.setBackground(java.awt.Color.WHITE);
        btnCadastrar.setFont(fonte);
        btnCadastrar.setOpaque(true);
        btnCadastrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 109, 17), 1, true));
        btnCadastrar.setPreferredSize(new java.awt.Dimension(110, 32));

        btnCancelar.setText(" Cancelar ");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setFont(fonte);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 32));

        txtDataRealizacao.setToolTipText("dd/MM/yyyy");
        txtProximaDose.setToolTipText("dd/MM/yyyy (opcional)");

        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
    }

    private void preencherCampos() {
        cmbAnimal.setSelectedItem(registro.getAnimal());
        cmbProcedimento.setSelectedItem(registro.getTipo());
        cmbResponsavel.setSelectedItem(registro.getResponsavel());

        if (registro.getDataRealizacao() != null) {
            txtDataRealizacao.setText(registro.getDataRealizacao().format(FMT));
        }
        if (registro.getDataProximaDose() != null) {
            txtProximaDose.setText(registro.getDataProximaDose().format(FMT));
        }
        txtaDescricao.setText(registro.getDescricao() != null ? registro.getDescricao() : "");
        txtaObservacoes.setText(registro.getObservacoes() != null ? registro.getObservacoes() : "");
    }

    private void salvar() {
        if (cmbAnimal.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o animal.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbProcedimento.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o tipo de procedimento.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtDataRealizacao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data de realização.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate dataRealizacao;
        try {
            dataRealizacao = LocalDate.parse(txtDataRealizacao.getText().trim(), FMT);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data de realização inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        registro.setAnimal((Animal) cmbAnimal.getSelectedItem());
        registro.setTipo((TipoProcedimentoEnum) cmbProcedimento.getSelectedItem());
        registro.setDataRealizacao(dataRealizacao);
        registro.setResponsavel((Pessoa) cmbResponsavel.getSelectedItem());
        registro.setDescricao(txtaDescricao.getText().trim().isEmpty() ? null : txtaDescricao.getText().trim());
        registro.setObservacoes(txtaObservacoes.getText().trim().isEmpty() ? null : txtaObservacoes.getText().trim());

        String proximaDoseStr = txtProximaDose.getText().trim();
        if (!proximaDoseStr.isEmpty()) {
            try {
                LocalDate proximaDose = LocalDate.parse(proximaDoseStr, FMT);
                if (proximaDose.isBefore(dataRealizacao)) {
                    JOptionPane.showMessageDialog(this, "Data da próxima dose não pode ser anterior à data de realização.", "Data inválida", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                registro.setDataProximaDose(proximaDose);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data da próxima dose inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            registro.setDataProximaDose(null);
        }

        registroSaudeController.update(registro);
        JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        parent.carregarTabela();
        dispose();
    }

    private void cancelar() {
        dispose();
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        salvar();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        cancelar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBarra = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbAnimal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbProcedimento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtDataRealizacao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProximaDose = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbResponsavel = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescricao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaObservacoes = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        jLabel1.setText("Animal*");

        jLabel2.setText("Tipo de procedimento*");

        jLabel3.setText("Data de realização*");

        jLabel4.setText("Data da próxima dose");

        jLabel5.setText("Responsável");

        jLabel6.setText("Descrição");

        txtaDescricao.setColumns(20);
        txtaDescricao.setRows(5);
        jScrollPane1.setViewportView(txtaDescricao);

        jLabel7.setText("Observações");

        txtaObservacoes.setColumns(20);
        txtaObservacoes.setRows(5);
        jScrollPane2.setViewportView(txtaObservacoes);

        btnCancelar.setText("Cancelar");

        btnCadastrar.setText("Cadastrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(58, 58, 58)
                        .addComponent(btnCadastrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(cmbAnimal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDataRealizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(cmbProcedimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtProximaDose, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(cmbResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProcedimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataRealizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProximaDose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCadastrar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<Animal> cmbAnimal;
    private javax.swing.JComboBox<TipoProcedimentoEnum> cmbProcedimento;
    private javax.swing.JComboBox<Pessoa> cmbResponsavel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JTextField txtDataRealizacao;
    private javax.swing.JTextField txtProximaDose;
    private javax.swing.JTextArea txtaDescricao;
    private javax.swing.JTextArea txtaObservacoes;
    // End of variables declaration//GEN-END:variables
}
