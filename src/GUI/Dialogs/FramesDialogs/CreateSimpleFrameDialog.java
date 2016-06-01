/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs.FramesDialogs;

import Frames.AbstractSimpleFrame;
import Frames.CharacteristicFrame;
import Frames.EventFrame;
import Frames.FunctionalFrame;
import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import Frames.Structure.SlotArgument;
import GUI.Dialogs.ErrorDialog;
import GUI.TableModels.FrameSlotsTableModel;
import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class CreateSimpleFrameDialog extends javax.swing.JDialog {

    /**
     * Creates new form CreateEventFrameDialog
     * @param parent
     * @param modal
     */
    public CreateSimpleFrameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }
    private String type;
    public CreateSimpleFrameDialog(java.awt.Frame parent, boolean modal, String typeOfFrame) {
        super(parent, modal);
        initComponents();
        pack();
        type = typeOfFrame;
        switch (typeOfFrame) {
            case "characteristic":
                typeLabel.setText("Characteristic frame");
                newSlotButton.setVisible(false);
                deleteSlotButton.setVisible(false);
                myTableModel = new FrameSlotsTableModel("creatingChFr");
                break;
            case "event":
                typeLabel.setText("Event frame");
                myTableModel = new FrameSlotsTableModel("creatingEvFr");
                break;
            case "function":
                typeLabel.setText("Function frame");
                myTableModel = new FrameSlotsTableModel("creatingFuncFr");
                break;
        }
        slotsTable.setModel(myTableModel);
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

        typeLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        newFrameNameTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        predicateTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        slotsTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        newSlotButton = new javax.swing.JButton();
        deleteSlotButton = new javax.swing.JButton();
        addQuantorToEndingButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        quantorsList = new javax.swing.JList(listModel);
        removeQuantorButton = new javax.swing.JButton();
        addQuantorAfterSelectedItemButton = new javax.swing.JButton();
        addQuantorBeforeSelectedItemButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        typeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        typeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeLabel.setText("Event-Frame:");
        typeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Имя нового фрейма:");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        predicateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predicateTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Предикат:");

        jLabel6.setText("Кванторы:");

        slotsTable.setModel(myTableModel);
        jScrollPane1.setViewportView(slotsTable);

        jLabel7.setText("Слоты (константы заключаются в одинарные кавычки):");

        newSlotButton.setText("Добавить слот");
        newSlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSlotButtonActionPerformed(evt);
            }
        });

        deleteSlotButton.setText("Удалить слот");
        deleteSlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSlotButtonActionPerformed(evt);
            }
        });

        addQuantorToEndingButton.setText("Добавить в конец");
        addQuantorToEndingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuantorToEndingButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(quantorsList);

        removeQuantorButton.setText("Удалить");
        removeQuantorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeQuantorButtonActionPerformed(evt);
            }
        });

        addQuantorAfterSelectedItemButton.setText("Добавить после");
        addQuantorAfterSelectedItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuantorAfterSelectedItemButtonActionPerformed(evt);
            }
        });

        addQuantorBeforeSelectedItemButton.setText("Добавить перед");
        addQuantorBeforeSelectedItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuantorBeforeSelectedItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addGap(148, 148, 148))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(predicateTextField)
                                    .addComponent(newFrameNameTextField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(addQuantorToEndingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addQuantorAfterSelectedItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(removeQuantorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(addQuantorBeforeSelectedItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newSlotButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteSlotButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(typeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(typeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(newFrameNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(predicateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addQuantorToEndingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(addQuantorAfterSelectedItemButton)))
                        .addGap(5, 5, 5)
                        .addComponent(addQuantorBeforeSelectedItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeQuantorButton)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSlotButton)
                    .addComponent(deleteSlotButton))
                .addGap(8, 8, 8)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String newFrName = newFrameNameTextField.getText();
        String newFrPredicate = predicateTextField.getText();
        if (!ActualData.avalibleFrameName(newFrName)) {
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Фрейм с таким именем уже есть");
            errorD.setVisible(true);
        }
        else if (newFrPredicate.equals("")){
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Введите предикат!");
            errorD.setVisible(true);
        }
        else if (newFrName.equals("")){
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Введите имя фрейма!");
            errorD.setVisible(true);
        }
       /* else if (!Quantor.correctQuantorString(quantorsStr)){
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Некорректная строка кванторов");
            errorD.setVisible(true);
        }*/
        else {
            ArrayList<Concept> newConcepts = new ArrayList();
            HashMap<Concept, ArrayList<Constant>> newConstants = new HashMap();
            HashMap<Concept, ArrayList<Variable>> newVariables = new HashMap();
            Body newFrBody = new Body(); 
            for (int i = 0; i < slotsTable.getRowCount(); i++){
                String role = (String) slotsTable.getValueAt(i, 0);
                String argumentStr = (String) slotsTable.getValueAt(i, 1);
                String conceptStr = (String) slotsTable.getValueAt(i, 2);
                if (role.equals("") || argumentStr.equals("") || conceptStr.equals("")){
                    String errorMassage = "Некорректный ввод слотов";
                    ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, errorMassage);
                    errorD.setVisible(true);
                    return;
                }
                Concept slotConc = null;
                SlotArgument slArg = null;
                boolean newConcIndicator = false;
                boolean newConcInThisNewFrIndicator = true;
                boolean newConstInd = true;
                boolean newVarInd = true;
                if (ActualData.avalibleConceptName(conceptStr)){
                    
                    for (Concept conc: newConcepts)
                        if (conc.getName().equals(conceptStr)) {
                            newConcInThisNewFrIndicator = false;
                            slotConc = conc;
                            break;
                        }
                    if (newConcInThisNewFrIndicator){ 
                        slotConc = new Concept(conceptStr);
                        newConcepts.add(slotConc);
                    }
                    newConcIndicator = true;
                }
                else
                    slotConc = ActualData.getConceptByName(conceptStr);
                if (argumentStr.charAt(0) == '\'' && argumentStr.charAt(argumentStr.length()-1) == '\''){
                    if (newConcIndicator){
                        if (newConstants.containsKey(slotConc)){
                            ArrayList<Constant> newConstInDomen = newConstants.get(slotConc);
                            for (Constant constant: newConstInDomen)
                                if (constant.getName().equals(argumentStr)){
                                    slArg = constant;
                                    newConstInd = false;
                                }
                            if (newConstInd) {
                                slArg = new Constant(argumentStr, slotConc);
                                newConstInDomen.add((Constant) slArg);
                            }
                        }
                        else {
                            ArrayList<Constant> newConstInDomen = new ArrayList();
                            slArg = new Constant(argumentStr, slotConc);
                            newConstInDomen.add((Constant) slArg);
                            newConstants.put(slotConc, newConstInDomen);
                        }
                    }
                    else {
                        if (!ActualData.avalibleConstantNameInDomen(argumentStr, slotConc)){
                            slArg = ActualData.getConstantInDomenByName(argumentStr, slotConc);
                        }
                        else{
                            if (newConstants.containsKey(slotConc)){
                                ArrayList<Constant> newConstInDomen = newConstants.get(slotConc);
                                for (Constant constant: newConstInDomen)
                                    if (constant.getName().equals(argumentStr)){
                                        slArg = constant;
                                        newConstInd = false;
                                    }
                                if (newConstInd) {
                                    Concept base = slotConc;
                                    while (base instanceof DefConcept)
                                        slArg = new Constant(argumentStr, slotConc);//????
                                    newConstInDomen.add((Constant) slArg);
                                }
                            }
                            else {
                                ArrayList<Constant> newConstInDomen = new ArrayList();
                                slArg = new Constant(argumentStr, slotConc);
                                newConstInDomen.add((Constant) slArg);
                                newConstants.put(slotConc, newConstInDomen);
                            }
                        }
                    }
                }
                else{
                    if (newConcIndicator){
                        if (newVariables.containsKey(slotConc)){
                            ArrayList<Variable> newVarInDomen = newVariables.get(slotConc);
                            for (Variable var: newVarInDomen)
                                if (var.getName().equals(argumentStr)){
                                    slArg = var;
                                    newVarInd = false;
                                }
                            if (newVarInd) {
                                slArg = new Variable(argumentStr, slotConc);
                                newVarInDomen.add((Variable) slArg);
                            }
                        }
                        else {
                            ArrayList<Variable> newVarInDomen = new ArrayList();
                            slArg = new Variable(argumentStr, slotConc);
                            newVarInDomen.add((Variable) slArg);
                            newVariables.put(slotConc, newVarInDomen);
                        }
                    }
                    else {
                        if (!ActualData.avalibleVariableNameInDomen(argumentStr, slotConc)){
                            slArg = ActualData.getVariableInDomenByName(argumentStr, slotConc);  
                        }
                        else{
                            if (newConstants.containsKey(slotConc)){
                                ArrayList<Variable> newVarInDomen = newVariables.get(slotConc);
                                for (Variable var: newVarInDomen)
                                    if (var.getName().equals(argumentStr)){
                                        slArg = var;
                                        newConstInd = false;
                                    }
                                if (newConstInd) {
                                    slArg = new Variable(argumentStr, slotConc);
                                    newVarInDomen.add((Variable) slArg);
                                }
                            }
                            else {
                                ArrayList<Variable> newVarInDomen = new ArrayList();
                                slArg = new Variable(argumentStr, slotConc);
                                newVarInDomen.add((Variable) slArg);
                                newVariables.put(slotConc, newVarInDomen);
                            }
                        }
                    }
                }
                Slot slot = new Slot(role, slArg, slotConc);
                newFrBody.addSlot(slot);
            }
            ArrayList<Variable> vrbls = newFrBody.getAllVariablesInBody();
            String quantorsStr = "";
            for (int i=0; i < quantorsList.getModel().getSize(); i++){
                String quantor = (String) quantorsList.getModel().getElementAt(i);
                quantor = quantor.replace(" ", "") + ".";
                quantorsStr+=quantor;
            }
            ArrayList<Quantor> qntrs = Quantor.getQuantorArray(quantorsStr, vrbls);
            AbstractSimpleFrame newFrame = null;
            switch (type) {
                case "event":
                    newFrame = new EventFrame(newFrName, newFrPredicate, qntrs, newFrBody);
                    break;
                case "characteristic":
                    newFrame = new CharacteristicFrame(newFrName, newFrPredicate, qntrs, newFrBody);
                    break;
                case "function":
                    newFrame = new FunctionalFrame(newFrName, newFrPredicate, qntrs, newFrBody);
                    break;
            }
            if (ActualData.thisFrameAlreadyExist(newFrame)){
                ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Фрейм с таким описанием уже есть!");
                errorD.setVisible(true);
            }
            else{
                ActualData.addFrameToHierarchy(newFrame);
                if (!ActualData.addingIsSucces()){
                    new ErrorDialog(null, true, "Множественное наследование фреймов запрещено!").setVisible(true);
                    return;
                }
                ActualData.addNewConcepts(newConcepts);
                ActualData.addNewConstants(newConstants);
                ActualData.addNewVariables(newVariables);
                setVisible(false);
                dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private FrameSlotsTableModel myTableModel = new FrameSlotsTableModel("creatingEvFr");
    private void addRawToSlotsTable(){
        Object[] raw = {"","", ""};
        if (type.equals("function"))
            raw[0] = "аргумент" + myTableModel.getRowCount();
        myTableModel.addRow(raw);
    }
    private void predicateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predicateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_predicateTextFieldActionPerformed

    private void newSlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSlotButtonActionPerformed
        // TODO add your handling code here:
        addRawToSlotsTable();
    }//GEN-LAST:event_newSlotButtonActionPerformed

    private void deleteSlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSlotButtonActionPerformed
        // TODO add your handling code here:
        int rowIndex = slotsTable.getSelectedRow();
        if (type.equals("function")) {
            if (rowIndex<2)
                if (rowIndex == 0 || myTableModel.getRowCount() == 2)
                    return;
            else {
                myTableModel.removeRow(rowIndex);
                if (rowIndex >= myTableModel.getRowCount())
                    return;
                for (int i = rowIndex; i < myTableModel.getRowCount(); i++)
                    myTableModel.setValueAt("аргумент" + String.valueOf(i), i, 0);
            }
        }
        else if (rowIndex > -1)
            myTableModel.removeRow(rowIndex);
    }//GEN-LAST:event_deleteSlotButtonActionPerformed

    
    private void addQuantorToEndingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuantorToEndingButtonActionPerformed
        // TODO add your handling code here:
        int index = quantorsList.getModel().getSize();
       // if (index != 0)
         //   index++;
        addQuantor(index);
    }//GEN-LAST:event_addQuantorToEndingButtonActionPerformed

    private void addQuantor(int index){
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < slotsTable.getRowCount(); i++){
            String argumentStr = (String) slotsTable.getValueAt(i, 1);
            if (argumentStr.length()>0 && argumentStr.charAt(0) != '\'')
                vars.add(argumentStr);
        }
        for (int i = 0; i < quantorsList.getModel().getSize(); i++){
            String quantor = (String) quantorsList.getModel().getElementAt(i);
            String[] qArr = quantor.split(" ");
            vars.remove(qArr[1]);
        }
        if (vars.isEmpty()){
            ErrorDialog errD = new ErrorDialog(null, true, "Нет свободных переменных!");
            errD.setTitle("Нет свободных переменных");
            errD.setVisible(true);
            return;
        }
        QuantorsDialog quanDial = new QuantorsDialog(null, true, vars);
        quanDial.setVisible(true);
        String newQuantor = quanDial.quantor;
        if (!newQuantor.equals("")){
            listModel.add(index, newQuantor);
        }
    }
    private void removeQuantorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeQuantorButtonActionPerformed
        // TODO add your handling code here:
        int index = quantorsList.getSelectedIndex();
        if (index!=-1)
            listModel.remove(index);
    }//GEN-LAST:event_removeQuantorButtonActionPerformed

    private void addQuantorAfterSelectedItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuantorAfterSelectedItemButtonActionPerformed
        // TODO add your handling code here:
        int index = quantorsList.getSelectedIndex();
        if (index!=-1)
            addQuantor(index+1);
    }//GEN-LAST:event_addQuantorAfterSelectedItemButtonActionPerformed

    private void addQuantorBeforeSelectedItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuantorBeforeSelectedItemButtonActionPerformed
        // TODO add your handling code here:
        int index = quantorsList.getSelectedIndex();
        if (index!=-1)
            addQuantor(index);
    }//GEN-LAST:event_addQuantorBeforeSelectedItemButtonActionPerformed
    DefaultListModel listModel = new DefaultListModel();
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
            java.util.logging.Logger.getLogger(CreateSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateSimpleFrameDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateSimpleFrameDialog dialog = new CreateSimpleFrameDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addQuantorAfterSelectedItemButton;
    private javax.swing.JButton addQuantorBeforeSelectedItemButton;
    private javax.swing.JButton addQuantorToEndingButton;
    private javax.swing.JButton deleteSlotButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField newFrameNameTextField;
    private javax.swing.JButton newSlotButton;
    private javax.swing.JTextField predicateTextField;
    private javax.swing.JList quantorsList;
    private javax.swing.JButton removeQuantorButton;
    private javax.swing.JTable slotsTable;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
