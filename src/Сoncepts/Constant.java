/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

import Frames.Structure.SlotArgument;

/**
 *  
 * @author Anatoly
 */
public class Constant extends AbstractConcept implements SlotArgument{
    //constructors
    public Constant(String argName, Concept argDomen){
        name = argName;
        domen = argDomen;
    }
    
    //fields
    private Concept domen;

    @Override
    public boolean isVariable() {
        return false;}
    public Concept getDomen() { return domen;}
    public void setDomen(Concept concept) { domen = concept;}
}
