/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Frames.AbstractBinaryFrame;
import Frames.AbstractFrame;
import Frames.AbstractSimpleFrame;
import Frames.AndFrame;
import Frames.OrFrame;
import GUI.Dialogs.BinaryFrameViewDialog;
import GUI.Dialogs.ChoiceTypeOfNewFrameDialog;
import GUI.Dialogs.ConceptViewDialog;
import GUI.Dialogs.ConstantViewDialog;
import GUI.Dialogs.ViewSimpleFrameDialog;
import GUI.Dialogs.errorDialog;
import conceptualhierarchy.ActualData;
import conceptualhierarchy.ConceptNode;
import conceptualhierarchy.FrameNode;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        frameTree = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        conceptTree = new javax.swing.JTree();
        jLabel2 = new javax.swing.JLabel();
        addFrameButton = new javax.swing.JButton();
        deleteFrameButton = new javax.swing.JButton();
        frameViewButton = new javax.swing.JButton();
        conceptViewButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        removeConceptButton = new javax.swing.JButton();
        constantViewButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        updateFrameIsaTree();
        frameTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameTreeMouseClicked(evt);
            }
        });
        frameTree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                frameTreeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(frameTree);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Фреймы:");

        updateConceptIsaTree();
        jScrollPane2.setViewportView(conceptTree);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Концепты:");

        addFrameButton.setText("Добавить фрейм");
        addFrameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFrameButtonActionPerformed(evt);
            }
        });

        deleteFrameButton.setText("Удалить фрейм");
        deleteFrameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFrameButtonActionPerformed(evt);
            }
        });

        frameViewButton.setText("Просмотреть выбранный фрейм");
        frameViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frameViewButtonActionPerformed(evt);
            }
        });

        conceptViewButton.setText("Свойства концепта");
        conceptViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conceptViewButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Добавить концепт");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        removeConceptButton.setText("Удалить концепт");
        removeConceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeConceptButtonActionPerformed(evt);
            }
        });

        constantViewButton.setText("Константы");
        constantViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constantViewButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu3.setText("jMenu3");
        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(frameViewButton))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addFrameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteFrameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(constantViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conceptViewButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeConceptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(frameViewButton)
                            .addComponent(conceptViewButton)
                            .addComponent(constantViewButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteFrameButton)
                    .addComponent(addFrameButton)
                    .addComponent(jButton1)
                    .addComponent(removeConceptButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addFrameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFrameButtonActionPerformed
        ChoiceTypeOfNewFrameDialog chTOfNewWframe = new ChoiceTypeOfNewFrameDialog(this, true);
        chTOfNewWframe.setVisible(true);
        updateFrameIsaTree();
        updateConceptIsaTree();
    }//GEN-LAST:event_addFrameButtonActionPerformed

    private void deleteFrameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFrameButtonActionPerformed
        // TODO add your handling code here:
        TreeSelectionModel TSM = frameTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null) 
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        
        String s = (String) TC.getUserObject();
        if ((!s.equals("Frames")) && (!(s == null))) {
            ActualData.removeFrameByName(s);
        }
        updateFrameIsaTree();
    }//GEN-LAST:event_deleteFrameButtonActionPerformed

    private void frameTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameTreeMouseClicked
        // TODO add your handling code here:
          
    }//GEN-LAST:event_frameTreeMouseClicked

    private void frameTreeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_frameTreeKeyPressed
        // TODO add your handling code here:
        int extendedKeyCode = evt.getExtendedKeyCode();
        TreeSelectionModel TSM = frameTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null) 
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        String s = (String) TC.getUserObject();
        if ((!s.equals("Frames")) && (!(s == null))) {
            AbstractFrame fr = ActualData.getFrameByName(s);
            viewFrame(fr);
        }
    }//GEN-LAST:event_frameTreeKeyPressed
 
    private void frameViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frameViewButtonActionPerformed
        // TODO add your handling code here:
        TreeSelectionModel TSM = frameTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null)  
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        String s = (String) TC.getUserObject();
        if ((!s.equals("Frames")) && (!(s == null))) {
            AbstractFrame fr = ActualData.getFrameByName(s);
            viewFrame(fr);
        }
    }//GEN-LAST:event_frameViewButtonActionPerformed

    private void conceptViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conceptViewButtonActionPerformed
        // TODO add your handling code here:
        
        TreeSelectionModel TSM = conceptTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null)  
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        String s = (String) TC.getUserObject();
        if (!s.equals("Concepts")) {
            Concept conc = ActualData.getConceptByName(s);
            ConceptViewDialog cwd = new ConceptViewDialog(this, true);
            cwd.setConcept(conc);
            cwd.setVisible(true);
        }
        updateFrameIsaTree();
        updateConceptIsaTree();
    }//GEN-LAST:event_conceptViewButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ConceptViewDialog cwd = new ConceptViewDialog(this, true);
        cwd.setVisible(true);
        updateFrameIsaTree();
        updateConceptIsaTree();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void constantViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constantViewButtonActionPerformed
        // TODO add your handling code here:
        TreeSelectionModel TSM = conceptTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null) 
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        String s = (String) TC.getUserObject();
        if (!s.equals("Concepts")) {
            Concept conc = ActualData.getConceptByName(s);
            ConstantViewDialog cwd = new ConstantViewDialog(this, true);
            cwd.setConcept(conc);
            cwd.setVisible(true);
        }
        
    }//GEN-LAST:event_constantViewButtonActionPerformed

    private void removeConceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeConceptButtonActionPerformed
        // TODO add your handling code here:
        TreeSelectionModel TSM = conceptTree.getSelectionModel();
        TreePath TP = TSM.getSelectionPath();
        if (TP == null)  
            return;
        DefaultMutableTreeNode TC = (DefaultMutableTreeNode)TP.getLastPathComponent();
        String s = (String) TC.getUserObject();
        if (!s.equals("Concepts")) {
            HashSet<String> framesThatUseConcept = ActualData.getNamesOfFramesThatUseConcept(ActualData.getConceptByName(s));
            if (!framesThatUseConcept.isEmpty()){
                String errMess ="Концепт задействован в фреймах "+framesThatUseConcept.toString()+", сначала удалите их!";
                errorDialog errorD = new errorDialog(new javax.swing.JFrame(), true, errMess);
                errorD.setVisible(true);
                return;
            }
            ActualData.removeConceptByName(s);
            updateFrameIsaTree();
            updateConceptIsaTree();
        }
    }//GEN-LAST:event_removeConceptButtonActionPerformed
    public static void viewFrame(AbstractFrame fr){
        if (fr instanceof AndFrame){
            BinaryFrameViewDialog frViewDialog = new BinaryFrameViewDialog(new javax.swing.JFrame(), true);
            frViewDialog.setFrame((AbstractBinaryFrame) fr); 
            frViewDialog.setVisible(true);
        }
        else if (fr instanceof OrFrame){
            BinaryFrameViewDialog frViewDialog = new BinaryFrameViewDialog(new javax.swing.JFrame(), true);
            frViewDialog.setFrame((AbstractBinaryFrame) fr);
            frViewDialog.setVisible(true);
        }
        else if (fr instanceof AbstractSimpleFrame) {
            ViewSimpleFrameDialog frViewDialog = new ViewSimpleFrameDialog(new javax.swing.JFrame(), true);
            frViewDialog.setFrame((AbstractSimpleFrame) fr);
            frViewDialog.setVisible(true);
        }
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //AbstractSimpleFrame.main(args);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFrameButton;
    private javax.swing.JTree conceptTree;
    private javax.swing.JButton conceptViewButton;
    private javax.swing.JButton constantViewButton;
    private javax.swing.JButton deleteFrameButton;
    private javax.swing.JTree frameTree;
    private javax.swing.JButton frameViewButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeConceptButton;
    // End of variables declaration//GEN-END:variables

    private DefaultMutableTreeNode getFrameHierarchyTree(FrameNode node){
        ArrayList<FrameNode> childNodes = node.getChildNodes();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(node.getValue().getName());  
        for (FrameNode child: childNodes){
            DefaultMutableTreeNode childNode = getFrameHierarchyTree(child);  
            root.add(childNode);
        }
        return root;
    }
    private DefaultMutableTreeNode getConceptHierarchyTree(ConceptNode node){
        ArrayList<ConceptNode> childNodes = node.getChildNodes();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(node.getValue().getName());   
        for (ConceptNode child: childNodes){
            DefaultMutableTreeNode childNode = getConceptHierarchyTree(child);  
            root.add(childNode);
        }
        return root;
    }
    private void updateFrameIsaTree(){
        frameTree = new JTree(getFrameHierarchyTree(ActualData.getFrameHoerarchy()));
        jScrollPane1.setViewportView(frameTree);
    }
    private void updateConceptIsaTree(){
        conceptTree = new JTree(getConceptHierarchyTree(ActualData.getConceptHoerarchy()));
        jScrollPane2.setViewportView(conceptTree);
    }
}