/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import conceptualhierarchy.ActualData;
import javax.swing.table.DefaultTableModel;
import Сoncepts.Concept;
import Сoncepts.Constant;

/**
 *
 * @author Anatoly
 */
public class constantTableModel extends DefaultTableModel{
    
    public constantTableModel(Concept concept){
        super(data, headers);
        domen = concept;
        for (Constant constant: ActualData.getAllConstantsInDomen(concept)){
            Object [] row = {constant.getName()};
            if (ActualData.constantIsUsed(constant))
                insertRow(0, row);
            else 
                addRow(row);
        }
    }

    public constantTableModel() {
        super();
    }
    public int getFirstRemovebleRowIndex(){
        for (int i = 0; i < getRowCount(); i++){
            if (isCellRemoveble(i, 0))
                return i;
        }
        return Integer.MAX_VALUE;
    }
    private Concept domen;
    private static final Object[] headers = {"Константы:"};
    private static final Object[][] data = {};
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    public boolean isCellRemoveble(int row, int column){
        String constantName = ((String) getValueAt(row, 0)).trim();
        return !ActualData.constantIsUsed(ActualData.getConstantInDomenByName(constantName, domen));
    }
    public void addRow(String constantName){
        Object[] row = {constantName};
        if (ActualData.constantIsUsed(ActualData.getConstantInDomenByName(constantName, domen)))
            insertRow(0, row);
        else 
            addRow(row);
    }
}
