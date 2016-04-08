/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs;

import Frames.AbstractSimpleFrame;
import GUI.TableModels.AddExtensionTableModel;
import conceptualhierarchy.ActualData;
import java.util.HashMap;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;

/**
 *
 * @author Anatoly
 */
public final class AddExtensionDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddExtensionDialog
     * @param parent
     * @param modal
     */
    public AddExtensionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }
    public AddExtensionDialog(AbstractSimpleFrame arg){
        super(new java.awt.Frame(), true);
        setFrame(arg);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        predicateTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        extensionTable = new javax.swing.JTable();
        okButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Предикат:");

        predicateTextField.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Добавление означивания");

        extensionTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(extensionTable);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Заполните таблицу (константы записывайте без кавычек):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(predicateTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(predicateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean extIsAdded = false;
    private HashMap<String, Constant> addedExtension;
    public boolean extensionIsAdded() { return extIsAdded;}
    public HashMap<String, Constant> getAddedExtension() { return addedExtension;}
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        HashMap<String, Concept> roleConc = frame.getBody().getRoleConceptAccordance();
        for (int i = 0; i < extensionTable.getRowCount();i++){
            String constName = ((String) extensionTable.getValueAt(i, 1)).trim();
            String domenName = ((String) extensionTable.getValueAt(i, 2)).trim();
            String role = (String) extensionTable.getValueAt(i, 0);
            if (constName.isEmpty()){
                String errorMessage = "Вы не ввели имя константы в строке " + String.valueOf(i);
                errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
                errorD.setVisible(true);
                return;
            }
            if (ActualData.avalibleConceptName(domenName)){
                String errorMessage = "Вы ввели несуществующий концепт в строке " + String.valueOf(i);
                errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
                errorD.setVisible(true);
                return;
            }
            Concept concept = ActualData.getConceptByName(domenName);
            if (concept != roleConc.get(role) && concept.ISA(roleConc.get(role))){
                String errorMessage = "Вы ввели некорректный концепт в строке " + String.valueOf(i);
                errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
                errorD.setVisible(true);
                return;
            }
        }
        HashMap<String, Constant> extension = new HashMap();
        boolean newConstIndicator = false;
        for (int i = 0; i < extensionTable.getRowCount();i++){
            String role = (String) extensionTable.getValueAt(i, 0);
            String constName =((String) extensionTable.getValueAt(i, 1)).trim();
            if (constName.charAt(0)!='\'')
                constName ="\'" +constName+"\'";
            String domenName = ((String) extensionTable.getValueAt(i, 2)).trim();
            Concept domen = ActualData.getConceptByName(domenName);
            while (domen instanceof DefConcept)
                domen = ((DefConcept)domen).getBaseConcept();
            if (ActualData.avalibleConstantNameInDomen(constName, domen)){
                newConstIndicator = true;
                ActualData.addNewConstantInDomen(constName, domen);
            }
            Constant constant = ActualData.getConstantInDomenByName(constName, domen);
            extension.put(role, constant);
        }
        if (!newConstIndicator && ActualData.getExtensional(frame.getPredicate()).contains(extension)){
            String errorMessage = "Такое означивание уже есть!";
            errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errorMessage);
            errorD.setVisible(true);
        }
        else {
            ActualData.addExtension(frame.getPredicate(), extension); 
            extIsAdded = true;
            addedExtension = extension;
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddExtensionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddExtensionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddExtensionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddExtensionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddExtensionDialog dialog = new AddExtensionDialog(new javax.swing.JFrame(), true);
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
    private AbstractSimpleFrame frame = null;
    void setFrame(AbstractSimpleFrame arg){
        predicateTextField.setText(arg.getPredicate());
        extensionTable.setModel(new AddExtensionTableModel(arg));
        frame = arg;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable extensionTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField predicateTextField;
    // End of variables declaration//GEN-END:variables
}
