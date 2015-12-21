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
public abstract class AbstractComplexFrame extends AbstractFrame{
    protected AbstractFrame firstOperand;
    @Override
    public boolean conceptIsUsed(Concept concept){
        return firstOperand.conceptIsUsed(concept); 
    }
    @Override
    public boolean constantIsUsed(Constant constant){
        return firstOperand.constantIsUsed(constant);
    }
}
