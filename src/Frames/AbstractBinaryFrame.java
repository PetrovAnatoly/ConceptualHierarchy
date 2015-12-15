/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Сoncepts.Concept;
import Сoncepts.Constant;

/**
 *
 * @author Anatoly
 */
public abstract class AbstractBinaryFrame extends AbstractComplexFrame{
    protected AbstractFrame secondOperand; 
    public AbstractFrame getFirstOperand() { return firstOperand;}
    public AbstractFrame getSecondOperand() { return secondOperand;}
    @Override 
    public boolean conceptIsUsed(Concept concept){
        return firstOperand.conceptIsUsed(concept) || secondOperand.conceptIsUsed(concept);
    }
    
    @Override
    public boolean constantIsUsed(Constant constant){ 
        return firstOperand.constantIsUsed(constant) || secondOperand.constantIsUsed(constant);
    }
}
