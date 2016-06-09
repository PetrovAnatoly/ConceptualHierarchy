/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import Frames.AbstractFrame;
import Frames.AndFrame;
import Frames.CharacteristicFrame;
import Frames.EventFrame;
import Frames.FunctionalFrame;
import Frames.NotFrame;
import Frames.OrFrame;
import conceptualhierarchy.ActualData;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Anatoly
 */
public class FrameNodeCellRenderer extends DefaultTreeCellRenderer{
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
        //setForeground(getTextSelectionColor());
        DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) value;
        String frameName = (String) treenode.getUserObject();
        if (frameName.equals("Frames"))
            setIcon(new ImageIcon("icon/hierarchyRoot.png"));
        else {
            AbstractFrame frame = ActualData.getFrameByName(frameName);
            if (frame instanceof EventFrame)
                setIcon(new ImageIcon("icon/eventFrame.png"));
            else if (frame instanceof CharacteristicFrame)
                setIcon(new ImageIcon("icon/characteristicFrame.png"));
            else if (frame instanceof FunctionalFrame)
                setIcon(new ImageIcon("icon/functionalFrame.png"));
            else if (frame instanceof AndFrame)
                setIcon(new ImageIcon("icon/andFrame.png"));
            else if (frame instanceof OrFrame)
                setIcon(new ImageIcon("icon/orFrame.png"));
            else if (frame instanceof NotFrame)
                setIcon(new ImageIcon("icon/notFrame.png"));
        }
        return this;
    }
}
