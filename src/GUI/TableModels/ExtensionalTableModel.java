/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import Frames.AbstractSimpleFrame;
import Frames.Structure.Slot;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anatoly
 */
public class ExtensionalTableModel extends DefaultTableModel{
    public ExtensionalTableModel(AbstractSimpleFrame frame){
        ArrayList<String> roles = new ArrayList();
        for (Slot slot: frame.getBody().getSlots())
            roles.add(slot.getRole());
        Object[] headers = roles.toArray();
        setColumnIdentifiers(headers);
    }
}
