/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs.ConceptDialogs;

import GUI.Dialogs.ErrorDialog;
import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public class ConceptViewDialog extends javax.swing.JDialog {

    /**
     * Creates new form ConceptViewDialog
     * @param parent
     * @param modal
     */
    public ConceptViewDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        commentTextField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        newPropertyButton = new javax.swing.JButton();
        deletePropertyButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        propertyTable = new javax.swing.JTable();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Объявление нового концепта");

        jLabel2.setText("Имя концепта:");

        jLabel3.setText("Комментарий:");

        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titleLabel.setText("Новый концепт");

        newPropertyButton.setText("Добавить свойство");
        newPropertyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPropertyButtonActionPerformed(evt);
            }
        });

        deletePropertyButton.setText("Удалить свойство");
        deletePropertyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePropertyButtonActionPerformed(evt);
            }
        });

        propertyTable.setModel(myTableModel);
        jScrollPane1.setViewportView(propertyTable);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField)
                            .addComponent(commentTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newPropertyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletePropertyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(commentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deletePropertyButton)
                    .addComponent(newPropertyButton))
                .addGap(7, 7, 7)
                .addComponent(okButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newPropertyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPropertyButtonActionPerformed
        // TODO add your handling code here:
        addRawToPropertyTable();
    }//GEN-LAST:event_newPropertyButtonActionPerformed

    private void deletePropertyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePropertyButtonActionPerformed
        // TODO add your handling code here:
        int rowIndex = propertyTable.getSelectedRow();
        if (rowIndex > -1)
            myTableModel.removeRow(rowIndex);
    }//GEN-LAST:event_deletePropertyButtonActionPerformed

    private boolean newConceptInd=true;
    private Concept concept;
    public void setConcept(Concept conc){
        concept = conc;
        nameTextField.setText(conc.getName());
        commentTextField.setText(conc.getComment());
        for (String property: conc.getProperties()){
            Object [] raw = {property};
            myTableModel.addRow(raw);
        }
        newConceptInd = false;
        titleLabel.setText("Редактирование свойств концепта");
        setTitle("Просмотр и редактирование концепта");
    }
    private boolean conceptPropertiesIsChanged(){
        ArrayList<String> oldProp = concept.getProperties();
        ArrayList<String> newProp = new ArrayList();
        for (int i = 0; i < propertyTable.getRowCount(); i++)
            newProp.add((String) propertyTable.getValueAt(i, 0));
        return !oldProp.equals(newProp);
    }
    private boolean conceptIsRenamed(){
        return (!nameTextField.getText().equals(concept.getName()));
    }
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String conceptName = nameTextField.getText().trim();
        if (conceptName.equals("")){
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Введите имя!");
            errorD.setVisible(true);
            return;
        }
        String conceptComment = ""+commentTextField.getText().trim();
        if (!newConceptInd){
            if (!conceptPropertiesIsChanged()){
                if (conceptIsRenamed())
                    if (!ActualData.avalibleConceptName(conceptName)){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                        errorD.setVisible(true);
                        return;
                    }
                    else {
                        ActualData.renameConcept(concept.getName(), conceptName);
                        concept.setName(conceptName); 
                        concept.setComment(conceptComment);
                    }
            }
            else {
                ArrayList<String> newProp = new ArrayList();
                for (int i = 0; i < propertyTable.getRowCount(); i++){
                    String property = ((String) propertyTable.getValueAt(i, 0)).trim();
                    if (property.equals("")){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Заполните все поля в таблице!");
                        errorD.setVisible(true);
                        return; 
                    }
                    newProp.add(property);
                }
                if (conceptIsRenamed())
                    if (!ActualData.avalibleConceptName(conceptName)){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                        errorD.setVisible(true);
                        return;
                    }
                if (ActualData.conceptWithThisPropertiesIsExist(newProp)){
                    ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с такими свойствами уже есть!");
                    errorD.setVisible(true);
                    return;
                }
                else {
                    ActualData.removeConceptByName(concept.getName());
                    concept.setName(conceptName);
                    concept.setComment(conceptComment);
                    concept.setProperties(newProp);
                    ActualData.addConceptToHierarchy(concept);
                }
            }
        }
        else{
            ArrayList<String> newProp = new ArrayList();
            for (int i = 0; i < propertyTable.getRowCount(); i++){ 
                String property = ((String) propertyTable.getValueAt(i, 0)).trim();
                if (property.equals("")){
                    ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Заполните все поля в таблице!");
                    errorD.setVisible(true);
                    return;
                }
                newProp.add(property);
            }
            if (!ActualData.avalibleConceptName(conceptName)){
                ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                errorD.setVisible(true);
                return;
            }
            else if (ActualData.conceptWithThisPropertiesIsExist(newProp)){
                ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с такими свойствами уже есть!");
                errorD.setVisible(true);
                return;
            }
            else {
                concept = new Concept();
                concept.setName(conceptName);
                concept.setComment(conceptComment);
                concept.setProperties(newProp);
                ActualData.addConceptToHierarchy(concept);
            }
    }
    setVisible(false);
    dispose(); 
    }//GEN-LAST:event_okButtonActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed
    private final Object[] headers = {"Свойства концепта:"};
    private final Object[][] data = {};
    private final DefaultTableModel myTableModel = new DefaultTableModel(data,headers);
 
    private void addRawToPropertyTable(){
        Object[] raw = {""};
        myTableModel.addRow(raw);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConceptViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConceptViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConceptViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConceptViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConceptViewDialog dialog = new ConceptViewDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commentTextField;
    private javax.swing.JButton deletePropertyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newPropertyButton;
    private javax.swing.JButton okButton;
    private javax.swing.JTable propertyTable;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}