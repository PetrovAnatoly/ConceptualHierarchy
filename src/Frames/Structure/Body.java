/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Structure;

import ModelInputLoad.CDLTag;
import java.util.ArrayList;
import java.util.HashMap;
import Сoncepts.Concept;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class Body {
    public Body(){ slots = new ArrayList();}
    public Body(ArrayList<Slot> arg){ slots = arg;}
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
    
    public ArrayList<Slot> getSlots(){ return slots;}
    public void addSlot(Slot slot) { slots.add(slot);}
    
    public boolean covered(Body arg){
        varAccording = new HashMap();
        if (slots.size()<arg.slots.size()) 
            return false;
        for (Slot argSlot: arg.slots)
            for (Slot slot: slots)
            {
                if (slot.role.equals(argSlot.role) 
                        && (slot.domen == argSlot.domen || slot.domen.ISA(argSlot.domen))) 
                {
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
                if (slots.indexOf(slot) == slots.size()-1) 
                    return false;  
            }
        System.out.println("------------------------covered-----");
        System.out.println("this:\n");
        for (CDLTag tag: toConDesLanTags())
            System.out.println(tag.getConDesLanStructure());
        System.out.println("arg:\n");
        for (CDLTag tag: arg.toConDesLanTags())
            System.out.println(tag.getConDesLanStructure());
        
        return true;
    }
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
    public Concept getConceptByVariable(Variable var){
        for (Slot slot: slots)
            if (slot.getArgument() == var)
                return slot.getDomen();
        return null;
    }
    public HashMap<String, Concept> getRoleConceptAccordance(){
        HashMap<String, Concept> rtrn = new HashMap();
        for (Slot slot: slots){
            rtrn.put(slot.role, slot.domen);
        }
        return rtrn;
    }
    public static HashMap<SlotArgument, SlotArgument> varAccording = new HashMap();
    public ArrayList<CDLTag> toConDesLanTags(){
        ArrayList<CDLTag> rtrn = new ArrayList<>();
        for (Slot slot: slots){
            CDLTag slotTag = new CDLTag("слот");
            slotTag.addSimpleProperty("роль", slot.role);
            slotTag.addSimpleProperty("аргумент", slot.argument.getName());
            slotTag.addSimpleProperty("домен", slot.domen.getName());
            rtrn.add(slotTag);
        }
        return rtrn;
    }
    /*public boolean equals(Body arg){
        if (slots)
    }*/
}
