/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;

/**
 *
 * @author Anatoly
 */
public class СonstantTableModel extends DefaultTableModel{
    
    public СonstantTableModel(Concept concept){
        super(data, headers);
        domen = concept;
        ArrayList<Constant> constants;
        if (concept instanceof DefConcept)
            constants = ActualData.getConstantsOfDefConcept((DefConcept)concept);
        else 
            constants = ActualData.getAllConstantsInDomen(concept);
        for (Constant constant: constants){
            Object [] row = {constant.getName()};
            if (ActualData.constantIsUsedInFrame(constant) || ActualData.constantIsUsedInExtension(constant)){
                insertRow(0, row);
                firstRemovebleIndex++;
            }
            else 
                addRow(row);
        }
    }

    public СonstantTableModel() {
        super();
    }
    private int firstRemovebleIndex = 0;
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
        return row>=firstRemovebleIndex;
    }
    public void addRow(String constantName){
        Object[] row = {constantName};
        if (ActualData.constantIsUsedInFrame(ActualData.getConstantInDomenByName(constantName, domen))){
            insertRow(firstRemovebleIndex, row);
            firstRemovebleIndex++;
        }
        else 
            addRow(row);
    }
}
