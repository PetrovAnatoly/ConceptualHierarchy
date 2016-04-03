/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import ModelInputLoad.ConDesLanTag;

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

    @Override
    public ConDesLanTag toConDesLanTag() {
        ConDesLanTag rtrn = new ConDesLanTag("фрейм");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("тип", "NOT");
        rtrn.addSimpleProperty("аргумент", this.firstOperand.getName());
        return rtrn;
    }
}
