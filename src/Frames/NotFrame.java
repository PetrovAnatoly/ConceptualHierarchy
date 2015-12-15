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
public class NotFrame extends AbstractComplexFrame{
    public NotFrame(String frName, AbstractFrame operand){
        name = frName;
        firstOperand = operand;
    }
    public AbstractFrame getOperand() { return firstOperand;}
    @Override
    public boolean ISA(AbstractSimpleFrame argument) {
        return false;
    }
}
