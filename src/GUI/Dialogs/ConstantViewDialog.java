/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs;

import GUI.TableModels.constantTableModel;
import conceptualhierarchy.ActualData;
import Сoncepts.Concept;
import Сoncepts.DefConcept;

/**
 *
 * @author Anatoly
 */
public class ConstantViewDialog extends javax.swing.JDialog {

    /**
     * Creates new form ConstantViewDialog
     * @param parent
     * @param modal
     */
    public ConstantViewDialog(java.awt.Frame parent, boolean modal) {
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

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        conceptNameTextField = new javax.swing.JTextField();
        conceptCommentTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        constantTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        renameButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setText("Имя концепта:");

        jLabel4.setText("Комментарий:");

        conceptNameTextField.setEditable(false);

        conceptCommentTextField.setEditable(false);

        constantTable.setModel(myTableModel);
        constantTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                constantTableMouseClicked(evt);
            }
        });
        constantTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                constantTableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(constantTable);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Константы выбранного концепта");

        addButton.setText("Добавить");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        renameButton.setText("Переименовать");
        renameButton.setEnabled(false);
        renameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameButtonActionPerformed(evt);
            }
        });

        okButton.setText("ОК");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Удалить");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(renameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conceptCommentTextField)
                            .addComponent(conceptNameTextField))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(conceptNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(conceptCommentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(renameButton)
                    .addComponent(removeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void renameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameButtonActionPerformed
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex < -1)
            return;
        String oldName = (String) constantTable.getValueAt(rowIndex, 0);
        AddConstantDialog jDialog = new AddConstantDialog(new java.awt.Frame(), true);
        jDialog.titleLabel.setText("Переименование константы");
        jDialog.setVisible(true);
        String newName = AddConstantDialog.constantName;
        if (newName == null)
            return;
        if (newName.isEmpty()){
            String errorMessage = "Вы не ввели новое имя константы!";
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
            errorD.setVisible(true);
            return;
        }
        if (newName.equals(oldName)){
            String errorMessage = "Новое и старое имя совпадают";
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
            errorD.setVisible(true);
            return;
        }
        if (!ActualData.avalibleConstantNameInDomen(newName, concept))
        {
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, "Rонстанта c этим именем уже есть!");
            errorD.setVisible(true);
        }
        else{
            ActualData.getConstantInDomenByName(oldName, concept).setName(newName);
            constantTable.setValueAt(newName, rowIndex, 0);
        }
        
    }//GEN-LAST:event_renameButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        jDialog1 = new AddConstantDialog(new java.awt.Frame(), true);
        jDialog1.setVisible(true);
        String constName = "\'" + AddConstantDialog.constantName + "\'";
        if (constName == null)
            return;
        if (constName.isEmpty()){
            String errorMessage = "Вы не ввели имя константы!";
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
            errorD.setVisible(true);
            return;
        }
        if (!ActualData.avalibleConstantNameInDomen(constName, concept))
        {
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, "Такая константа уже есть!");
            errorD.setVisible(true);
        }
        else{
            ActualData.addNewConstantInDomen(constName, concept);
            myTableModel.addRow(constName);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void constantTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_constantTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex > -1){
            if (rowIndex >= myTableModel.getFirstRemovebleRowIndex())
                removeButton.setEnabled(true);
            else 
                removeButton.setEnabled(false);
            renameButton.setEnabled(true);
        }
    }//GEN-LAST:event_constantTableMouseClicked

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex < -1)
            return;
        String constantName = (String) constantTable.getValueAt(rowIndex, 0);
        ActualData.removeConstantInDomenByName(constantName, concept);
        myTableModel.removeRow(rowIndex);
    }//GEN-LAST:event_removeButtonActionPerformed

    private void constantTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_constantTableKeyPressed
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex > -1){
            if (rowIndex >= myTableModel.getFirstRemovebleRowIndex())
                removeButton.setEnabled(true);
            else 
                removeButton.setEnabled(false);
            renameButton.setEnabled(true);
        }
    }//GEN-LAST:event_constantTableKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex > -1){
            if (rowIndex >= myTableModel.getFirstRemovebleRowIndex())
                removeButton.setEnabled(true);
            else 
                removeButton.setEnabled(false);
            renameButton.setEnabled(true);
        }
        else{
            removeButton.setEnabled(false);
            renameButton.setEnabled(false);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        int rowIndex = constantTable.getSelectedRow();
        if (rowIndex > -1){
            if (rowIndex >= myTableModel.getFirstRemovebleRowIndex())
                removeButton.setEnabled(true);
            else 
                removeButton.setEnabled(false);
            renameButton.setEnabled(true);
        }
    }//GEN-LAST:event_formMouseClicked

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
            java.util.logging.Logger.getLogger(ConstantViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConstantViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConstantViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConstantViewDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConstantViewDialog dialog = new ConstantViewDialog(new javax.swing.JFrame(), true);
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
    
    private constantTableModel myTableModel = new constantTableModel();
    private Concept concept;
    public void setConcept(Concept conc){
        concept = conc;
        myTableModel = new constantTableModel(conc);
        constantTable.setModel(myTableModel);
        constantTable.setVisible(true);
        conceptCommentTextField.setText(concept.getComment());
        conceptNameTextField.setText(concept.getName());
        if (conc instanceof DefConcept){
            removeButton.setVisible(false);
            addButton.setVisible(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField conceptCommentTextField;
    private javax.swing.JTextField conceptNameTextField;
    private javax.swing.JTable constantTable;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton renameButton;
    // End of variables declaration//GEN-END:variables
}
