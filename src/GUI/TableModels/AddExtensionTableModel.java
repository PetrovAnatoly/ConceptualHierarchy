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
public class AddExtensionTableModel extends DefaultTableModel{
    public AddExtensionTableModel(AbstractSimpleFrame arg){
        super();
        Object[] headers = {"роль", "константа", "домен"};
        setColumnIdentifiers(headers);
        for (Slot slot: arg.getBody().getSlots()){
            Object[] row = {slot.getRole(),slot.getArgument().isVariable()?"":slot.getArgument().getName(),slot.getDomen().getName()};
            addRow(row);
        }
        frame = arg;
    }
    private final AbstractSimpleFrame frame;
    @Override
    public boolean isCellEditable(int i, int k){ 
        if (k==0)
            return false;
        if (k==2)
            return true;
        if (k==1)
            return frame.getBody().getSlots().get(i).getArgument().isVariable();
        return false;
    }
}
