package br.com.ifba.gestaoanimal.animal.view;

import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.enums.EspecieEnum;
import br.com.ifba.gestaoanimal.enums.PorteEnum;
import br.com.ifba.gestaoanimal.enums.SexoEnum;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import java.time.LocalDate;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import java.time.format.DateTimeParseException;

public class AnimalCadastrar extends javax.swing.JFrame {
    
    private final AnimalController animalController;
    private final AnimalListar parent;

    public AnimalCadastrar(AnimalController animalController, AnimalListar parent) {
        this.animalController = animalController;
        this.parent = parent;
        initComponents();
        configurarTela();
    }

    private void configurarTela() {
        setTitle("PatasSalvas — cadastrar animal");
        setLocationRelativeTo(null);

        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);

        // ── Preenche os ComboBoxes com os enums ──
        cmbEspecie.setModel(new DefaultComboBoxModel(EspecieEnum.values()));
        cmbSexo.setModel(new DefaultComboBoxModel(SexoEnum.values()));
        cmbPorte.setModel(new DefaultComboBoxModel(PorteEnum.values()));
        cmbStatus.setModel(new DefaultComboBoxModel(StatusAnimalEnum.values()));
        
        // ── Fontes ──
        txtNome.setFont(fonte);
        cmbEspecie.setFont(fonte);
        txtRaca.setFont(fonte);
        cmbSexo.setFont(fonte);
        spnIdade.setFont(fonte);
        cmbPorte.setFont(fonte);
        cmbStatus.setFont(fonte);
        txtDataEntrada.setFont(fonte);
        txtTemperamento.setFont(fonte);
        txtNecessidades.setFont(fonte);
        btnSalvar.setFont(fonte);
        btnCancelar.setFont(fonte);

        // ── Botão Salvar ──
        btnSalvar.setText("Salvar");
        btnSalvar.setForeground(new java.awt.Color(60, 52, 137));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));

        // ── Botão Cancelar ──
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));

        // ── Placeholder data ──
        txtDataEntrada.setToolTipText("dd/MM/yyyy");
    }

    private void salvar() {
        // ── Validação dos campos obrigatórios ──
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome é obrigatório.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbEspecie.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a espécie.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbSexo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o sexo.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbStatus.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o status.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ── Monta o objeto Animal ──
        Animal animal = new Animal();
        animal.setNome(txtNome.getText().trim());
        animal.setEspecie((EspecieEnum) cmbEspecie.getSelectedItem());
        animal.setRaca(txtRaca.getText().trim());
        animal.setSexo((SexoEnum) cmbSexo.getSelectedItem());
        animal.setIdadeEstimada((Integer) spnIdade.getValue());
        animal.setPorte((PorteEnum) cmbPorte.getSelectedItem());
        animal.setStatus((StatusAnimalEnum) cmbStatus.getSelectedItem());
        animal.setTemperamento(txtTemperamento.getText().trim());
        animal.setNecessidadesEspeciais(txtNecessidades.getText().trim());
        animal.setAtivo(true);

        // ── Data de entrada ──
        String dataStr = txtDataEntrada.getText().trim();
        if (!dataStr.isEmpty()) {
            try {
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                animal.setDataEntrada(data);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            animal.setDataEntrada(LocalDate.now());
        }

        // ── Salva e fecha ──
        animalController.save(animal);
        JOptionPane.showMessageDialog(this, "Animal cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        parent.carregarTabela();
        dispose();
    }

    private void cancelar() {
        dispose();
    }

   
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        cmbEspecie = new javax.swing.JComboBox<>();
        txtRaca = new javax.swing.JTextField();
        cmbSexo = new javax.swing.JComboBox<>();
        spnIdade = new javax.swing.JSpinner();
        cmbPorte = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        txtDataEntrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNecessidades = new javax.swing.JTextArea();
        txtTemperamento = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome *");

        jLabel2.setText("Espécie *");

        jLabel3.setText("Raça");

        jLabel4.setText("Sexo *");

        jLabel5.setText("Idade estimada (anos)");

        jLabel6.setText("Porte *");

        jLabel7.setText("Status *");

        jLabel8.setText("Data de entrada");

        jLabel9.setText("Temperamento");

        jLabel10.setText("Necessidades especiais");

        txtNecessidades.setColumns(20);
        txtNecessidades.setRows(5);
        jScrollPane1.setViewportView(txtNecessidades);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(txtRaca)
                            .addComponent(spnIdade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(232, 232, 232))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(cmbSexo, 0, 194, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addComponent(cmbPorte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(txtTemperamento, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSalvar)
                                .addGap(43, 43, 43)
                                .addComponent(btnCancelar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbStatus, 0, 164, Short.MAX_VALUE)
                                        .addGap(130, 130, 130)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(85, 85, 85))))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
         salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed
  
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbEspecie;
    private javax.swing.JComboBox<String> cmbPorte;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnIdade;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextArea txtNecessidades;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRaca;
    private javax.swing.JTextField txtTemperamento;
    // End of variables declaration//GEN-END:variables
}
