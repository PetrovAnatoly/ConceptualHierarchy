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
public class AndFrame extends AbstractBinaryFrame{
    public AndFrame(String frameName, AbstractFrame first, AbstractFrame second){
        name = frameName;
        firstOperand = first;
        secondOperand = second;
    }
    
    @Override
    public boolean ISA(AbstractSimpleFrame argument) {
        return (firstOperand.ISA(argument) || secondOperand.ISA(argument));
    }
    
    @Override
    public boolean ISA(AndFrame arg){
        if (arg.firstOperand instanceof AbstractSimpleFrame){
            AbstractSimpleFrame firstArgOperand = (AbstractSimpleFrame) arg.firstOperand;
            if (arg.secondOperand instanceof AbstractSimpleFrame){
                AbstractSimpleFrame secondArgOperand = (AbstractSimpleFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof AndFrame){
                AndFrame secondArgOperand = (AndFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof OrFrame){
                OrFrame secondArgOperand = (OrFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else return false;
        }
        else if (arg.firstOperand instanceof AndFrame){
            AndFrame firstArgOperand = (AndFrame) arg.firstOperand;
            if (arg.secondOperand instanceof AbstractSimpleFrame){
                AbstractSimpleFrame secondArgOperand = (AbstractSimpleFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof AndFrame){
                AndFrame secondArgOperand = (AndFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof OrFrame){
                OrFrame secondArgOperand = (OrFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else return false;
        }
        else if (arg.firstOperand instanceof OrFrame){
            OrFrame firstArgOperand = (OrFrame) arg.firstOperand;
            if (arg.secondOperand instanceof AbstractSimpleFrame){
                AbstractSimpleFrame secondArgOperand = (AbstractSimpleFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof AndFrame){
                AndFrame secondArgOperand = (AndFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else if (arg.secondOperand instanceof OrFrame){
                OrFrame secondArgOperand = (OrFrame) arg.secondOperand;
                return (ISA(firstArgOperand) && ISA(secondArgOperand));
            }
            else return false;
        }
        return false;
    }
    
    @Override
    public boolean ISA(OrFrame arg){
        return ISA(arg.firstOperand) && ISA(arg.secondOperand);
    }

    @Override
    public String getOperation() { return "AND";}
}
