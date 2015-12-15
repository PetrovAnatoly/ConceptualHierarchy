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
public abstract class AbstractFrame{
    //fields
    protected String name;
    
    //methods
    public String getName() {return name;}
    public void rename(String arg) {name = arg;}
    
    public abstract boolean ISA(AbstractSimpleFrame argument);
    public boolean ISA(ScriptFrame arg) { return false;}
    public boolean ISA(AndFrame arg) { return false;}
    public boolean ISA(NotFrame arg) { return false;}
    public boolean ISA(AbstractFrame arg) { 
        if (arg instanceof AbstractSimpleFrame)
            return ISA((AbstractSimpleFrame) arg);
        else if (arg instanceof AndFrame)
            return ISA((AndFrame) arg);
        else if (arg instanceof OrFrame)
            return ISA((OrFrame) arg);
        else if (arg instanceof NotFrame)   
            return ISA((NotFrame) arg);  
        else return false;
        }
    public boolean ISA(OrFrame arg){ return false;}

    public abstract boolean conceptIsUsed(Concept concept); 
    public abstract boolean constantIsUsed(Constant constant);  
}
