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
public class AndConcept extends AbstractComplexConcept{
    private final static String operation = "AND";
    @Override
    public String getOperation() { return operation;}

    @Override
    public boolean ISA(AbstractConcept arg) {
        if (arg instanceof Concept)
            return (firstOperand.ISA(arg) || secondOperand.ISA(arg));
        else if (arg instanceof AndConcept)
            return ISA((AndConcept)arg);
            
        return false;
    }
    public boolean ISA(AndConcept arg){
        return (ISA(arg.firstOperand) && ISA(arg.secondOperand));
    }
}
