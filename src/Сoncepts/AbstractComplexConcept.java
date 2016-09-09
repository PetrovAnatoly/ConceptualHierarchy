/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

/**
 *
 * @author Anatoly
 */
public abstract class AbstractComplexConcept extends Concept{
    protected AbstractConcept firstOperand;
    protected AbstractConcept secondOperand;
    public AbstractComplexConcept(String n, Concept fo, Concept so){
        name = n;
        firstOperand = fo;
        secondOperand = so;
    }
    public abstract String getOperation();
    public AbstractConcept getFirstOperand() { return firstOperand;}
    public AbstractConcept getSecondOperand() { return secondOperand;}
    @Override
    public abstract boolean ISA(Concept arg);
    
}
