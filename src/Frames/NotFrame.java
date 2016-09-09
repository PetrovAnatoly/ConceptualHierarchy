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
    public boolean ISA(AbstractFrame arg) { 
        if (arg instanceof AbstractSimpleFrame)
            return ISA((AbstractSimpleFrame) arg);
        else if (arg instanceof AndFrame)
            return ISA((AndFrame) arg);
        else if (arg instanceof OrFrame)
            return ISA((OrFrame) arg);
        else if (arg instanceof NotFrame)   
            return ISA((NotFrame) arg);  
        else return false;
    }
    @Override
    public boolean ISA(AbstractSimpleFrame argument) {
        return false;
        //return getEquivalent().ISA(argument);
    }
    @Override
    public boolean ISA(NotFrame argument){
        return !argument.firstOperand.ISA(firstOperand);
    }
    public boolean ISA(AndFrame arg){
        if (firstOperand.ISA(arg.firstOperand) && firstOperand.ISA(arg.secondOperand))
            return false;
        return !ISA(arg.firstOperand) || !ISA(arg.secondOperand);
    }
    public boolean ISA(OrFrame arg){
        if (firstOperand.ISA(arg.firstOperand) || firstOperand.ISA(arg.secondOperand))
            return false;
        return !ISA(arg.firstOperand) && !ISA(arg.secondOperand);
    }
    AbstractFrame getEquivalent(){
        AbstractFrame fr = null;
        if (firstOperand instanceof AbstractSimpleFrame){
            AbstractSimpleFrame fo = (AbstractSimpleFrame)firstOperand;
            if (!fo.getQuantors().isEmpty()){
                ArrayList<Quantor> foQntrs = fo.cloneQuantors();
                int originalFrstQuantorValue = foQntrs.get(0).getValue();
                AbstractSimpleFrame notFrameSimple;
                if (foQntrs.get(0).getType().equals("[]")){
                    foQntrs.get(0).setType("{]");
                    foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                    notFrameSimple = new AbstractSimpleFrame(fo.getName(), fo.getPredicate() , foQntrs, fo.getBody());
                    ArrayList<Quantor> rtrnScndOperandQntrs = fo.cloneQuantors();
                    rtrnScndOperandQntrs.get(0).setType("[}");
                    rtrnScndOperandQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                    AbstractSimpleFrame notFrameSimpleSecondPart = new AbstractSimpleFrame(fo.getName(), fo.getPredicate() , rtrnScndOperandQntrs, fo.getBody());
                    return new OrFrame("", notFrameSimple, notFrameSimpleSecondPart);
                }
                else if (foQntrs.get(0).getType().equals("[}")){
                    foQntrs.get(0).setType("{]");
                    foQntrs.get(0).setValue(originalFrstQuantorValue - 1);
                    return new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                }
                else if (foQntrs.get(0).getType().equals("{]")){
                    foQntrs.get(0).setType("[}");
                    foQntrs.get(0).setValue(originalFrstQuantorValue + 1);
                    return new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                }
                else if (foQntrs.get(0).getType().equals("A")){
                    foQntrs.get(0).setType("[]");
                    foQntrs.get(0).setValue(0);
                    return new AbstractSimpleFrame(fo.getName(), fo.getPredicate(), foQntrs, fo.getBody());
                }
            }
            else if (fo.getVariableCount() == 0){
                //дописать!
                return new AbstractSimpleFrame("none", "none", new ArrayList(), fo.getBody());
            }
            return new AbstractSimpleFrame("none", "none", new ArrayList(), fo.getBody());
        }
        else if (firstOperand instanceof NotFrame)
            fr = ((NotFrame)firstOperand).firstOperand;
        else if (firstOperand instanceof AndFrame){
            fr = new OrFrame("", new NotFrame("",((AndFrame)firstOperand).firstOperand), 
                    new NotFrame("",((AndFrame)firstOperand).secondOperand));
        }
        else if (firstOperand instanceof OrFrame){
            fr = new AndFrame("", new NotFrame("",((OrFrame)firstOperand).firstOperand), 
                    new NotFrame("",((OrFrame)firstOperand).secondOperand));
        }
        return fr;
        
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
