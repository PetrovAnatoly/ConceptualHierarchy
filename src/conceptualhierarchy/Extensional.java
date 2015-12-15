/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Frames.AbstractSimpleFrame;
import Frames.Structure.Slot;
import java.util.ArrayList;
import Сoncepts.Concept;
import Сoncepts.Constant;

/**
 *
 * @author Anatoly
 */
public class Extensional {
    public Extensional(AbstractSimpleFrame fr){
        frame = fr;
        predicate = fr.getPredicate();
        for (Slot slot: fr.getBody().getSlots()){
            roles.add(slot.getRole());
            domens.add(slot.getDomen());
        }
    }
    private AbstractSimpleFrame frame;
    private String predicate;
    private ArrayList<String> roles = new ArrayList();
    private ArrayList<Concept> domens = new ArrayList();
    
    private ArrayList<ArrayList<Constant>> arguments = new ArrayList();
    
    public void addExtension(ArrayList<Constant> extension){
        arguments.add(extension);
    }
}
