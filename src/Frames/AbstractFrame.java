/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import ModelInputLoad.CDLTag;
import Сoncepts.Concept;
import Сoncepts.Constant;

/**
 *
 * @author Anatoly
 */
public abstract class AbstractFrame{
    protected String name;
    public String getName() {return name;}
    public void setName(String str) { name = str;}
    public void rename(String arg) {name = arg;}
    public abstract boolean ISA(AbstractSimpleFrame argument);
    public boolean ISA(ScriptFrame arg) { return false;}
    public boolean ISA(AndFrame arg) { return false;}
    public boolean ISA(NotFrame arg) { 
        return false;}
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
    public boolean equals(AbstractFrame arg){
        return ISA(arg) && arg.ISA(this);
    }
    public boolean ISA(OrFrame arg){ return false;}
    public abstract boolean conceptIsUsed(Concept concept); 
    public abstract boolean constantIsUsed(Constant constant);  

    public abstract CDLTag toConDesLanTag();
}
