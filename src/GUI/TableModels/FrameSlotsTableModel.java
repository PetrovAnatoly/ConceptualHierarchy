/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import Frames.AbstractSimpleFrame;
import Frames.Structure.Slot;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anatoly
 */
public class FrameSlotsTableModel extends DefaultTableModel {
    public FrameSlotsTableModel(AbstractSimpleFrame frame){ 
        Object[] headers = { "Роль", "Аргумент", "Концепт" };
        setColumnIdentifiers(headers);
        for (Slot slot: frame.getBody().getSlots()){
            if (slot.getRole() == null)
                System.out.println("role is null!");
            if (slot.getArgument() == null)
                System.out.println(slot.getRole() + " arg is null!");
            if (slot.getDomen() == null)
                System.out.println("domen is null!");
            Object[] row = {slot.getRole(), slot.getArgument().getName(), slot.getDomen().getName()};
            addRow(row);
        }
        mode = "view";
        
    }
    public FrameSlotsTableModel(String s){
        mode = s;
        Object[] headers = { "Роль", "Аргумент", "Концепт" };
        setColumnIdentifiers(headers);
        if (s.equals("creatingChFr")){
            Object[] row1 = {"характеристика", "", ""};
            addRow(row1);
            Object[] row2 = {"значение", "", ""};
            addRow(row2);
        }
    }
    private final String mode; // "view", "creatingEvFr", "creatingChFr"
    @Override
    public boolean isCellEditable(int i, int k){
        switch (mode) {
            case "view":
                return false;
            case "creatingEvFr":
                return true;
            case "creatingChFr":
                return (k!=0);
        }
        return false;
    }
}
