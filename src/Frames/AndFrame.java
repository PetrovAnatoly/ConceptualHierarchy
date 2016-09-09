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
public class AndFrame extends AbstractBinaryFrame{
    public AndFrame(String frameName, AbstractFrame first, AbstractFrame second){
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
    public boolean ISA(AbstractSimpleFrame argument) {
        /*NotFrame notArg = new NotFrame("", argument);
        OrFrame orNotOperands = new OrFrame("", new NotFrame("", firstOperand), new NotFrame("", secondOperand));
        return notArg.ISA(orNotOperands);*/
        return firstOperand.ISA(argument) && secondOperand.ISA(argument);
    }
    
    @Override
    public boolean ISA(AndFrame arg){
        return ISA(arg.firstOperand) && ISA(arg.secondOperand);
    }
    @Override
    public boolean ISA(OrFrame arg){
        if (ISA(arg.firstOperand))
            return true;
        if (ISA(arg.secondOperand))
            return true;
        NotFrame notThis = new NotFrame("", this);
        AndFrame notArg = new AndFrame("", new NotFrame("", arg.firstOperand), new NotFrame("", arg.secondOperand));
        return notArg.ISA(notThis);
    }
    @Override
    public boolean ISA(NotFrame arg){
        return firstOperand.ISA(arg) || secondOperand.ISA(arg);
        //return ISA(arg.getEquivalent());
    }
    @Override
    public String getOperation() { return "AND";}

    @Override
    public CDLTag toConDesLanTag() {
        CDLTag rtrn = new CDLTag("фрейм");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("тип", "AND");
        rtrn.addSimpleProperty("аргумент1", this.firstOperand.getName());
        rtrn.addSimpleProperty("аргумент2", this.secondOperand.getName());
        return rtrn;
    }
}
