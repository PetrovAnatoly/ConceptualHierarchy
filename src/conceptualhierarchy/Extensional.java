/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Frames.AbstractSimpleFrame;
import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.Variable;

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
            roleConceptAccordance.put(slot.getRole(), slot.getDomen());
        }
    }
    private AbstractSimpleFrame frame;
    private String predicate;
    private ArrayList<String> roles = new ArrayList();
    private ArrayList<Concept> domens = new ArrayList();
    private HashMap<String, Concept> roleConceptAccordance = new HashMap();
    private ArrayList<HashMap<String, Constant>> arguments = new ArrayList(); // <role:arg>
    
    public void addExtension(HashMap<String, Constant> extension){
        arguments.add(extension);
    }
    public Extensional getProjection(Body frameBody){
        Extensional rtrn = new Extensional(frame);
        for (HashMap<String,Constant> constants: arguments){
            boolean rolevant = true;
            for (Slot slot: frameBody.getSlots()){
                if (slot.getArgument() instanceof Constant){
                    if (slot.getArgument() != constants.get(slot.getRole())){
                        rolevant = false;
                        break;
                    }
                }
                else if (slot.getDomen() != roleConceptAccordance.get(slot.getRole()))
                    if (!(slot.getDomen().ISA(roleConceptAccordance.get(slot.getRole())) ||
                            roleConceptAccordance.get(slot.getRole()).ISA(slot.getDomen()))) {
                        rolevant = false;
                        break;
                    }  
            }
            if (rolevant)
                rtrn.addExtension(constants);
        }
        return rtrn;
    }
    public Extensional getProjection(String Role, Constant constant){
        Extensional rtrn = new Extensional(frame);
        for (HashMap<String,Constant> constants: arguments){
            boolean rolevant = true;
            if (constant != constants.get(Role))
                rolevant = false;
            if (rolevant)
                rtrn.addExtension(constants);
        }
        return rtrn;
    }
    //вызывать только после getProjection
    public Extensional getProjection(AbstractSimpleFrame frame){
        Extensional rtrn = new Extensional(frame);
        ArrayList<Quantor> reversedQuantor = new ArrayList<>(frame.getQuantors());
        Collections.reverse(reversedQuantor);
        HashMap<Variable, String> variableRoleAccordance = frame.getVariableRoleAccordance();
        for (Quantor quantor: frame.getQuantors()){
            Concept concept = frame.getBody().getConceptByVariable(quantor.getVariable());
            ArrayList<Constant> constantsOfThisConcept = ActualData.getAllConstantsInDomen(concept);
            String role = variableRoleAccordance.get(quantor.getVariable());
            for (Constant constant: constantsOfThisConcept){
                Extensional thisConstExt = this.getProjection(role, constant);
            }
            for (HashMap<String, Constant> constants: arguments){
                Extensional thisConstExt = this.getProjection(role, null);
            }
        }
        return rtrn;
    }
    
}
