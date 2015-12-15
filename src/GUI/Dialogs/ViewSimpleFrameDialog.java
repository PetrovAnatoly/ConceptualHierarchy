/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs;

import Frames.AbstractSimpleFrame;
import Frames.CharacteristicFrame;
import Frames.EventFrame;
import Frames.Structure.Slot;
import GUI.TableModels.FrameSlotsTableModel;
import conceptualhierarchy.ActualData;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anatoly
 */
public class ViewSimpleFrameDialog extends javax.swing.JDialog {

    /**
     * Creates new form viewSimpleFrame
     */
    public ViewSimpleFrameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameTypeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        frameNameField = new javax.swing.JTextField();
        quantorsField = new javax.swing.JTextField();
        predicateField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        slotsTable = new javax.swing.JTable();
        viewExtensionalButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        frameTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        frameTypeLabel.setText("jLabel1");

        jLabel2.setText("Имя:");

        jLabel3.setText("Кванторы:");

        jLabel4.setText("Предикат:");

        frameNameField.setEditable(false);
        frameNameField.setText("jTextField1");

        quantorsField.setEditable(false);
        quantorsField.setText("jTextField2");

        predicateField.setEditable(false);
        predicateField.setText("jTextField3");

        jLabel5.setText("Слоты (константы заключены в кавычки):");

        slotsTable.setModel(myTableModel);
        slotsTable.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(slotsTable);

        viewExtensionalButton.setText("Означивания");
        viewExtensionalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewExtensionalButtonActionPerformed(evt);
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
                        .addGap(168, 168, 168)
                        .addComponent(frameTypeLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(0, 95, Short.MAX_VALUE))
                            .addComponent(predicateField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(frameNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quantorsField, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(viewExtensionalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(frameTypeLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(frameNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantorsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(predicateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewExtensionalButton)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewExtensionalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewExtensionalButtonActionPerformed
        // TODO add your handling code here:
        if (frame.isClosed()){
            if (ActualData.frameExtensionalIsGettable(frame)){
                
            }
            else {
                
            }
        }
    }//GEN-LAST:event_viewExtensionalButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ViewSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewSimpleFrameDialog dialog = new ViewSimpleFrameDialog(new javax.swing.JFrame(), true);
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
    private AbstractSimpleFrame frame;
    private DefaultTableModel myTableModel = new DefaultTableModel();
    public void setFrame(AbstractSimpleFrame fr){
        if (fr instanceof EventFrame){
            frameTypeLabel.setText("Event-Frame");
        }
        else if (fr instanceof CharacteristicFrame){
            frameTypeLabel.setText("Characteristic-Frame");
        }
        frameNameField.setText(fr.getName());
        predicateField.setText(fr.getPredicate());  
        quantorsField.setText(fr.getQuantorsLine()); 
        myTableModel = new FrameSlotsTableModel(fr);
        slotsTable.setModel(myTableModel);
        if (fr.isClosed())
            viewExtensionalButton.setText("Вывести означивания");
        else
            viewExtensionalButton.setText("Посмотреть означивания");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField frameNameField;
    private javax.swing.JLabel frameTypeLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField predicateField;
    private javax.swing.JTextField quantorsField;
    private javax.swing.JTable slotsTable;
    private javax.swing.JButton viewExtensionalButton;
    // End of variables declaration//GEN-END:variables
}