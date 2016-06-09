/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import conceptualhierarchy.ActualData;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import Сoncepts.Concept;
import Сoncepts.DefConcept;

/**
 *
 * @author Anatoly
 */
public class ConceptNodeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) value;
        String conceptName = (String) treenode.getUserObject();
        if (conceptName.equals("Concepts"))
            setIcon(new ImageIcon("icon/hierarchyRoot.png"));
        else {
            Concept concept = ActualData.getConceptByName(conceptName);
            if (concept instanceof DefConcept)
                setIcon(new ImageIcon("icon/defConcept.png"));
            else 
                setIcon(new ImageIcon("icon/simpleConcept.png"));
        }
        return this;
    }
}
