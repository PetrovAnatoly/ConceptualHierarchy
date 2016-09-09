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
public class OrConcept extends AbstractComplexConcept{
    private final static String operation = "OR";
    public OrConcept(String n, Concept fo, Concept so) {super(n, fo, so);}
    @Override
    public String getOperation() { return operation;}
    @Override
    public boolean ISA(AbstractConcept arg) {
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
        /*if (arg instanceof DefConcept)
            return ISA((DefConcept) arg);
        else if (arg instanceof Concept)
            return ISA((Concept) arg);
        else if (arg instanceof AndConcept)
            return ISA((AndConcept)arg);
        else if (arg instanceof OrConcept)
            return ISA((OrConcept)arg);
        return false;*/
    }
    public boolean ISA(Concept arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
    public boolean ISA(AndConcept arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
    public boolean ISA(OrConcept arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
}
