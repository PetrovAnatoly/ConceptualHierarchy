/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import ModelInputLoad.CDLTag;

/**
 *
 * @author Anatoly
 */
public class OrFrame extends AbstractBinaryFrame{
    public OrFrame(String frameName, AbstractFrame first, AbstractFrame second){
        name = frameName;
        firstOperand = first;
        secondOperand = second;
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
        return (firstOperand.ISA(argument) && secondOperand.ISA(argument));
    }
    
    @Override
    public boolean ISA(AndFrame arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
    
    @Override
    public boolean ISA(OrFrame arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
    
    @Override
    public String getOperation() { return "OR";}
    @Override
    public boolean ISA(NotFrame arg){
        //AbstractFrame notArg = arg.firstOperand;
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
    @Override
    public CDLTag toConDesLanTag() {
        CDLTag rtrn = new CDLTag("фрейм");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("тип", "OR");
        rtrn.addSimpleProperty("аргумент1", this.firstOperand.getName());
        rtrn.addSimpleProperty("аргумент2", this.secondOperand.getName());
        return rtrn;
    }
}
