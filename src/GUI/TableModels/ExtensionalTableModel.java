/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import Frames.AbstractSimpleFrame;
import Frames.Structure.Slot;
import conceptualhierarchy.Extensional;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import Сoncepts.Constant;

/**
 *
 * @author Anatoly
 */
public class ExtensionalTableModel extends DefaultTableModel{
    public ExtensionalTableModel(AbstractSimpleFrame frame, Extensional extensions){
        ArrayList<String> roles = new ArrayList();
        for (Slot slot: frame.getBody().getSlots())
            roles.add(slot.getRole());
        Object[] headers = roles.toArray();
        setColumnIdentifiers(headers);
        for (HashMap<String, Constant> extension: extensions.getExtensions()){
            addRow(extension);
        }
    }  
    public ExtensionalTableModel(Extensional extensions){
        ArrayList<String> roles = extensions.getRoles();
        Object[] headers = roles.toArray();
        setColumnIdentifiers(headers);
        ArrayList<String> constantNames = new ArrayList();
        String columnName = new String();
        for (HashMap<String, Constant> extension: extensions.getExtensions()){
            addRow(extension);
        }
    }
    @Override
    public boolean isCellEditable(int i, int k) { return false;}
    public final void addRow(HashMap<String, Constant> extension){
        ArrayList<String> newRow = new ArrayList();
        for (int i=0;i<getColumnCount(); i++){
            String constName;
            if (!extension.containsKey(getColumnName(i)))
                constName = "";
            else
                constName = extension.get(getColumnName(i)).getName();
            newRow.add(constName);
        }
        this.addRow(newRow.toArray());
    }
}
