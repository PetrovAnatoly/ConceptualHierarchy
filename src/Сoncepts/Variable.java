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
public class Variable implements SlotArgument{
    //constructors
    public Variable(String argName){ name = argName;}
    public Variable(String argName, Concept concept){ 
        name = argName;
        domen = concept;
    } 
    //fields
    private String name;
    private Concept domen;
    
    //methods
    @Override
    public String getName() { return name;}
    @Override
    public boolean isVariable() { return true;}
    public Concept getDomen() { return domen;}
}
