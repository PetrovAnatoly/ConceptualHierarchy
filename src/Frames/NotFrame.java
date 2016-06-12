/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Frames.Structure.Quantor;
import ModelInputLoad.CDLTag;
import java.util.ArrayList;

/**
 *
 * @author Anatoly
 */
public class NotFrame extends AbstractComplexFrame{
    public NotFrame(String frName, AbstractFrame operand){
        name = frName;
        firstOperand = operand;
    }
    public AbstractFrame getOperand() { return firstOperand;}
    
    public AbstractFrame transformWithDeMorganLaws(){
        AbstractFrame fr = null;
        
        if (firstOperand instanceof NotFrame)
            fr = ((NotFrame)firstOperand).firstOperand;
        else if (firstOperand instanceof AndFrame){
            fr = new OrFrame("", new NotFrame("",((AndFrame)firstOperand).firstOperand), 
                    new NotFrame("",((AndFrame)firstOperand).firstOperand));
        }
        else if (firstOperand instanceof OrFrame){
            fr = new AndFrame("", new NotFrame("",((OrFrame)firstOperand).firstOperand), 
                    new NotFrame("",((OrFrame)firstOperand).firstOperand));
        }
        return fr;
    }
    @Override
    public boolean ISA(AbstractSimpleFrame argument) {
        if(firstOperand instanceof AbstractSimpleFrame){
            AbstractSimpleFrame fo = (AbstractSimpleFrame)firstOperand;
            if(argument.getPredicate().equals(fo.getPredicate())){
                if (!fo.getQuantors().isEmpty()){
                    ArrayList<Quantor> foQntrs = fo.cloneQuantors();
                    int originalFrstQuantorValue = foQntrs.get(0).getValue();
                    AbstractSimpleFrame notFrameSimple;
                    if (foQntrs.get(0).getType().equals("[]")){
                        foQntrs.get(0).setType("{]");
                        foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                        notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate() , foQntrs, fo.getBody());
                        if (!notFrameSimple.ISA(argument))
                            return false;
                        foQntrs.get(0).setType("[}");
                        foQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                        return notFrameSimple.ISA(argument);
                    }
                    else if (foQntrs.get(0).getType().equals("[}")){
                        foQntrs.get(0).setType("{]");
                        foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                        notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                        return notFrameSimple.ISA(argument);
                    }
                    else if (foQntrs.get(0).getType().equals("{]")){
                        foQntrs.get(0).setType("[}");
                        foQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                        notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                        return notFrameSimple.ISA(argument);
                    }
                    else if (foQntrs.get(0).getType().equals("A")){
                        foQntrs.get(0).setType("[]");
                        foQntrs.get(0).setValue(0);
                        notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                        return notFrameSimple.ISA(argument);
                    }
                }
                else if (fo.getVariableCount() == 0)
                    return !fo.ISA(argument);
            }
            else 
                return false;
        }
        return false;
    }
    @Override
    public boolean ISA(NotFrame argument){
        if(firstOperand instanceof AbstractSimpleFrame){
            AbstractSimpleFrame fo = (AbstractSimpleFrame)firstOperand;
            if (!fo.getQuantors().isEmpty()){
                ArrayList<Quantor> foQntrs = fo.cloneQuantors();
                int originalFrstQuantorValue = foQntrs.get(0).getValue();
                AbstractSimpleFrame notFrameSimple;
                if (foQntrs.get(0).getType().equals("[]")){
                    foQntrs.get(0).setType("{]");
                    foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                    notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate() , foQntrs, fo.getBody());
                    if (!notFrameSimple.ISA(argument))
                        return false;
                    foQntrs.get(0).setType("[}");
                    foQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                    return notFrameSimple.ISA(argument);
                }
                else if (foQntrs.get(0).getType().equals("[}")){
                    foQntrs.get(0).setType("{]");
                    foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                    notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                    return notFrameSimple.ISA(argument);
                }
                else if (foQntrs.get(0).getType().equals("{]")){
                    foQntrs.get(0).setType("[}");
                    foQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                    notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                    return notFrameSimple.ISA(argument);
                }
                else if (foQntrs.get(0).getType().equals("A")){
                    foQntrs.get(0).setType("[]");
                    foQntrs.get(0).setValue(0);
                    notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                    return notFrameSimple.ISA(argument);
                }
            }
            else if (fo.getVariableCount() == 0)
                return !fo.ISA(argument);
        }
        else 
            return transformWithDeMorganLaws().ISA(argument);
        return false;
    }
    @Override
    public CDLTag toConDesLanTag() {
        CDLTag rtrn = new CDLTag("фрейм");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("тип", "NOT");
        rtrn.addSimpleProperty("аргумент", this.firstOperand.getName());
        return rtrn;
    }
}
