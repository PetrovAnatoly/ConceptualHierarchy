/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

import Frames.AbstractSimpleFrame;
import ModelInputLoad.ConDesLanTag;
import conceptualhierarchy.ActualData;
import conceptualhierarchy.FrameNode;
import java.util.ArrayList;

/**
 *
 * @author Anatoly
 */
public class DefConcept extends Concept{
    public DefConcept(AbstractSimpleFrame defFrArg, String argRole){
        defFrame = defFrArg;
        role = argRole;
        baseConcept = defFrArg.getBody().getRoleConceptAccordance().get(role); //потом поправить
        ArrayList<FrameNode> parentsOfDefFrame = ActualData.getFrameNode(defFrame).getParents();
        properties = (ArrayList<String>) baseConcept.getProperties().clone();
        if (!parentsOfDefFrame.contains(ActualData.getFrameHoerarchy()))
            for (FrameNode parentNode: parentsOfDefFrame)
                if (parentNode.getValue() instanceof AbstractSimpleFrame){
                    AbstractSimpleFrame absSmplFr = (AbstractSimpleFrame) parentNode.getValue();
                    ArrayList<DefConcept> defConc = ActualData.getDefConcepts(absSmplFr);
                    if (!defConc.isEmpty()){
                        for (DefConcept dc: defConc)
                            if (dc.role.equals(role))
                                this.addAllProperties(dc.properties);
                    }
                }
    }
    protected AbstractSimpleFrame defFrame;
    protected String role;
    protected Concept baseConcept;
    public String getRole() { return role;}
    @Override
    public boolean ISA(Concept arg){
        if (arg instanceof DefConcept){
            if (!role.equals(((DefConcept) arg).role))
                return false;
            if (baseConcept == arg || baseConcept.ISA(arg))
                return true;
            if ((baseConcept == ((DefConcept) arg).baseConcept || baseConcept.ISA(((DefConcept) arg).baseConcept)) 
                    && defFrame.ISA(((DefConcept) arg).getDefFrame()))
                return true;
        }
        if (baseConcept == arg || baseConcept.ISA(arg))
            return true;
        else return super.ISA(arg);
    }
    public Concept getBaseConcept(){
        return baseConcept;
    }
    public AbstractSimpleFrame getDefFrame() { return defFrame;}
    @Override
    public ConDesLanTag toConDesLanTag(){
        ConDesLanTag rtrn = new ConDesLanTag("def-концепт");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("комментарий", comment);
        rtrn.addSimpleProperty("def-фрейм", defFrame.getName());
        rtrn.addSimpleProperty("роль", role);
        rtrn.addSimpleProperty("базовыйКонцепт", baseConcept.getName());
        rtrn.addSimpleProperty("роль", role);
        for (String prop: properties)
            rtrn.addComplexStringProperty("свойства", prop);
        return rtrn;
    }
}
