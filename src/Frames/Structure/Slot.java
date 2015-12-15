/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Structure;

import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public class Slot {
    public Slot(){}
    public Slot(String roleArg, SlotArgument slotArg, Concept domenArg)
    {
        role = roleArg;
        argument = slotArg;
        domen = domenArg;
    }
    String role;
    SlotArgument argument;
    Concept domen; 
    
    public void setRole(String arg) { role = arg;}
    public void setArgument(SlotArgument arg) { argument = arg;}
    public void setDomen(Concept arg) { domen = arg;}
    public String getRole() { return role;}
    public SlotArgument getArgument() { return argument;}
    public Concept getDomen() { return domen;}
}
