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
public class ComplementConcept extends AbstractComplexConcept{
    public ComplementConcept(String n, Concept fo, Concept so){ super(n, fo, so);}
    private final static String operation = "COMPL";
    @Override
    public String getOperation() { return operation;}
    @Override
    public boolean ISA(AbstractConcept arg) {
        if (arg instanceof DefConcept)
            return ISA((DefConcept) arg);
        else if (arg instanceof Concept)
            return ISA((Concept) arg);
        else if (arg instanceof AndConcept)
            return ISA((AndConcept)arg);
        else if (arg instanceof OrConcept)
            return ISA((OrConcept)arg);
        else if (arg instanceof ComplementConcept)
            return ISA((ComplementConcept)arg);
        return false;
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
    public boolean ISA(ComplementConcept arg){
        return firstOperand.ISA(arg) && secondOperand.ISA(arg);
    }
}