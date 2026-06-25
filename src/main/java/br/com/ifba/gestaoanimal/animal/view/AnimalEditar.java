
package br.com.ifba.gestaoanimal.animal.view;
import br.com.ifba.gestaoanimal.animal.controller.AnimalController;
import br.com.ifba.gestaoanimal.animal.entity.Animal;
import br.com.ifba.gestaoanimal.enums.EspecieEnum;
import br.com.ifba.gestaoanimal.enums.PorteEnum;
import br.com.ifba.gestaoanimal.enums.SexoEnum;
import br.com.ifba.gestaoanimal.enums.StatusAnimalEnum;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class AnimalEditar extends javax.swing.JFrame {
    
 
    private final AnimalController animalController;
    private final AnimalListar parent;
    private final Animal animal;
 
    public AnimalEditar(AnimalController animalController, AnimalListar parent, Animal animal) {
        this.animalController = animalController;
        this.parent = parent;
        this.animal = animal;
        initComponents();
        configurarTela();
        preencherCampos();
    }
 
    private void configurarTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PatasSalvas — editar animal");
        setLocationRelativeTo(null);
 
        java.awt.Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
 
        cmbEspecie.setModel(new DefaultComboBoxModel(EspecieEnum.values()));
        cmbSexo.setModel(new DefaultComboBoxModel(SexoEnum.values()));
        cmbPorte.setModel(new DefaultComboBoxModel(PorteEnum.values()));
        cmbStatus.setModel(new DefaultComboBoxModel(StatusAnimalEnum.values()));
 
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
 
        btnSalvar.setText("✔ Salvar alterações");
        btnSalvar.setForeground(new java.awt.Color(60, 52, 137));
        btnSalvar.setBackground(java.awt.Color.WHITE);
        btnSalvar.setOpaque(true);
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 52, 137), 1, true));
 
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(80, 80, 80));
        btnCancelar.setBackground(java.awt.Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 1, true));
 
        txtDataEntrada.setToolTipText("dd/MM/yyyy");
    }
 
    private void preencherCampos() {
        txtNome.setText(animal.getNome());
        cmbEspecie.setSelectedItem(animal.getEspecie());
        txtRaca.setText(animal.getRaca() != null ? animal.getRaca() : "");
        cmbSexo.setSelectedItem(animal.getSexo());
        spnIdade.setValue(animal.getIdadeEstimada() != null ? animal.getIdadeEstimada() : 0);
        cmbPorte.setSelectedItem(animal.getPorte());
        cmbStatus.setSelectedItem(animal.getStatus());
        txtTemperamento.setText(animal.getTemperamento() != null ? animal.getTemperamento() : "");
        txtNecessidades.setText(animal.getNecessidadesEspeciais() != null ? animal.getNecessidadesEspeciais() : "");
        if (animal.getDataEntrada() != null) {
            txtDataEntrada.setText(
                animal.getDataEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }
 
    private void salvar() {
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
    animal.setNome(txtNome.getText().trim());
        animal.setEspecie((EspecieEnum) cmbEspecie.getSelectedItem());
        animal.setRaca(txtRaca.getText().trim());
        animal.setSexo((SexoEnum) cmbSexo.getSelectedItem());
        animal.setIdadeEstimada((Integer) spnIdade.getValue());
        animal.setPorte((PorteEnum) cmbPorte.getSelectedItem());
        animal.setStatus((StatusAnimalEnum) cmbStatus.getSelectedItem());
        animal.setTemperamento(txtTemperamento.getText().trim());
        animal.setNecessidadesEspeciais(txtNecessidades.getText().trim());
 
        String dataStr = txtDataEntrada.getText().trim();
        if (!dataStr.isEmpty()) {
            try {
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                animal.setDataEntrada(data);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
 
        animalController.update(animal);
        JOptionPane.showMessageDialog(this, "Animal atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtRaca = new javax.swing.JTextField();
        txtTemperamento = new javax.swing.JTextField();
        txtDataEntrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNecessidades = new javax.swing.JTextArea();
        spnIdade = new javax.swing.JSpinner();
        cmbStatus = new javax.swing.JComboBox<>();
        cmbSexo = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        cmbEspecie = new javax.swing.JComboBox<>();
        cmbPorte = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel1.setText("Nome*");

        jLabel2.setText("Raça");

        jLabel3.setText("Idade estimada (anos)");

        jLabel4.setText("Status*");

        jLabel5.setText("Temperamento");

        jLabel6.setText("Necessidades especiais");

        jLabel7.setText("Espécie*");

        jLabel8.setText("Sexo*");

        jLabel9.setText("Porte*");

        jLabel10.setText("Data de entrada");

        txtNome.addActionListener(this::txtNomeActionPerformed);

        txtNecessidades.setColumns(20);
        txtNecessidades.setRows(5);
        jScrollPane1.setViewportView(txtNecessidades);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSalvar.setText("Salvar Alterações");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        cmbEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbPorte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spnIdade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtTemperamento, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRaca, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(btnCancelar)
                                .addGap(57, 57, 57)
                                .addComponent(btnSalvar)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(97, 97, 97)))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7)
                                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(txtDataEntrada)
                                    .addComponent(cmbPorte, 0, 235, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)))
                        .addGap(77, 77, 77))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

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
