/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

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
    public boolean ISA(AbstractSimpleFrame argument) {
        return (firstOperand.ISA(argument) && secondOperand.ISA(argument));
    }
    
    @Override
    public boolean ISA(AndFrame argument){
        return (firstOperand.ISA(argument) && secondOperand.ISA(argument));
    }
    
    @Override
    public boolean ISA(OrFrame argument){
        return (firstOperand.ISA(argument) && secondOperand.ISA(argument));
    }
    
}
