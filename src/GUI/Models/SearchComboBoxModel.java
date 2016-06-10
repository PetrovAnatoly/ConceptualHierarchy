/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Anatoly
 */
public class SearchComboBoxModel extends DefaultComboBoxModel{
    ArrayList<String> allItems = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    String selection;
    JComboBox comboBox;
    ComboBoxEditor cbe;
    int currPos = 0;
    public SearchComboBoxModel(JComboBox cmbBx, Object items[]){
        super(items);
        comboBox = cmbBx;
        for (Object item: items)
            allItems.add((String) item);
        cbe = cmbBx.getEditor();
        cbe.getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {}
            public void keyReleased(java.awt.event.KeyEvent e) {
            String str = cbe.getItem().toString();
                JTextField jtf = (JTextField)cbe.getEditorComponent();
                currPos = jtf.getCaretPosition();
                if(e.getKeyChar() == KeyEvent.CHAR_UNDEFINED){
                    if(e.getKeyCode() != KeyEvent.VK_ENTER ){
                        cbe.setItem(str);
                        jtf.setCaretPosition(currPos);
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    comboBox.setSelectedIndex(comboBox.getSelectedIndex());
                else{
                    updateModel(comboBox.getEditor().getItem().toString());
                    cbe.setItem(str);
                    jtf.setCaretPosition(currPos);
                }
            }
            public void keyTyped(KeyEvent e){}
        });
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e){
                cbe.setItem(e.getItem().toString());
                comboBox.setSelectedItem(e.getItem());
            }
        });
    }
    public void updateModel(String in){
        data.clear();
        for(String s: allItems)
            if (s.toLowerCase().startsWith(in.toLowerCase()))
                data.add(s);
        super.fireContentsChanged(this, 0, data.size());
        comboBox.hidePopup();
        comboBox.showPopup();
        if(!data.isEmpty())
            comboBox.setSelectedIndex(0);
    }

    @Override
    public int getSize(){return data == null?0:data.size();}
    @Override
    public String getElementAt(int index){return data.get(index);}
    @Override
    public void setSelectedItem(Object anItem) {selection = (String) anItem;}
    @Override
    public Object getSelectedItem(){return selection;}
}

