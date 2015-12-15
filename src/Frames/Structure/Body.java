/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Structure;

import java.util.ArrayList;
import java.util.HashMap;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class Body {
    //constructors
    public Body(){
        slots = new ArrayList();
    }
    public Body(ArrayList<Slot> arg){
        slots = arg;
    }
    //fields
    private ArrayList<Slot> slots = new ArrayList();
    public ArrayList<Variable> getAllVariablesInBody(){
        ArrayList<Variable> rtrn = new ArrayList();
        for (Slot slot: slots){
            if (slot.argument instanceof Variable){
                if (!rtrn.contains(slot.argument))
                    rtrn.add((Variable) slot.argument); //проверить!!
            }
        }
        return rtrn;
    }
    //methods
    public ArrayList<Slot> getSlots(){ return slots;}
    public void addSlot(Slot slot) { slots.add(slot);}
    public boolean coveredAndSimilar(Body arg){
        varAccording = new HashMap();
        if (slots.size()!=arg.slots.size()) 
            return false;
        for (Slot slot: slots)
            for (Slot argSlot: arg.slots){
                if (slot.role.equals(argSlot.role) 
                        && (slot.domen == argSlot.domen || slot.domen.ISA(argSlot.domen))) {
                    if (slot.argument.isVariable() && !argSlot.argument.isVariable())
                        return false;
                    else {
                        if (slot.argument.isVariable() && argSlot.argument.isVariable()){
                            varAccording.put(slot.argument, argSlot.argument);
                            break;
                        }
                        else if ((!slot.argument.isVariable()) && argSlot.argument.isVariable())
                            break;
                        else if ((!slot.argument.isVariable()) && !argSlot.argument.isVariable())
                            if (slot.argument.getName().equals(argSlot.argument.getName()))
                                break;
                            else 
                                return false;
                    }
                }
                if (arg.slots.indexOf(argSlot) == arg.slots.size()-1) 
                    return false;  
            }
        return true;
    }
    
    public static HashMap<SlotArgument, SlotArgument> varAccording = new HashMap();
    
    /*public boolean equals(Body arg){
        if (slots)
    }*/
}
