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
import ModelInputLoad.ConDesLanTag;
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
    public Extensional() {};
    public Extensional(AbstractSimpleFrame fr){
        frame = fr;
        predicate = fr.getPredicate();
        for (Slot slot: fr.getBody().getSlots()){
            roles.add(slot.getRole());
            domens.add(slot.getDomen());
            roleConceptAccordance.put(slot.getRole(), slot.getDomen());
        }
    }
    public Extensional(Extensional base){
        frame = base.frame;
        predicate = base.predicate;
        roles.addAll(base.roles);
        domens.addAll(base.domens);
        roleConceptAccordance = (HashMap<String, Concept>) base.roleConceptAccordance.clone();
    }
    private AbstractSimpleFrame frame;
    private String predicate; 
    private ArrayList<String> roles = new ArrayList<>();
    private ArrayList<Concept> domens = new ArrayList<>();
    private HashMap<String, Concept> roleConceptAccordance = new HashMap<>();
    private ArrayList<HashMap<String, Constant>> arguments = new ArrayList<>(); // <role:arg>
    
    public ArrayList<Concept> getDomens(){
        return domens;
    }
    public HashMap<String, Concept> getRoleConceptAccordance() { return roleConceptAccordance;}
    public void setRoleConceptAccordance(HashMap<String, Concept> rc) {roleConceptAccordance = rc;}
    public void setPredicate (String pred) { predicate = pred;}
    public void exploreRoles(Body body){
        for (Slot slot: body.getSlots()){
            if (!roleConceptAccordance.containsKey(slot.getRole())){
                roleConceptAccordance.put(slot.getRole(), slot.getDomen());
                roles.add(slot.getRole());
                if (!domens.contains(slot.getDomen()))
                    domens.add(slot.getDomen());
            }
            else if (roleConceptAccordance.get(slot.getRole()) != slot.getDomen())
                if (roleConceptAccordance.get(slot.getRole()).ISA(slot.getDomen())){
                    domens.remove(roleConceptAccordance.get(slot.getRole()));
                    domens.add(slot.getDomen());
                    roleConceptAccordance.put(slot.getRole(), slot.getDomen());
                }
        }
    }
    public void addExtension(HashMap<String, Constant> extension){
        arguments.add(extension);
        for (String role: extension.keySet())
            if (!roleConceptAccordance.containsKey(role)){
                roleConceptAccordance.put(role, extension.get(role).getDomen());
                roles.add(role);
                domens.add(extension.get(role).getDomen());
            }
    }
    public void addAll(ArrayList<HashMap<String, Constant>> ext){
        arguments.addAll(ext);
    }
    public ArrayList<HashMap<String, Constant>> getExtensions() { return arguments;}
    
    public boolean constantIsUsed(Constant constant){
        for (HashMap<String, Constant> map: arguments)
            if (map.values().contains(constant))
                return true;
        return false;
    }
    public Extensional getProjection(Body frameBody){
        Extensional rtrn = new Extensional(this);
        for (HashMap<String,Constant> constants: arguments){
            boolean rolevant = true;
            for (Slot slot: frameBody.getSlots()){
                if (!constants.containsKey(slot.getRole())){
                    rolevant = false;
                    break;
                }
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
        Extensional rtrn = new Extensional(this);
        for (HashMap<String,Constant> constants: arguments){
            boolean rolevant = true;
            if (constant != constants.get(Role))
                rolevant = false;
            if (rolevant)
                rtrn.addExtension(constants);
        }
        return rtrn;
    }
    public ArrayList<String> getRoles() { 
        return roles;
    }
    public boolean contains(HashMap<String, Constant> arg){
        for (HashMap<String, Constant> extension: arguments){
            boolean isOk = true;
            for (String role: arg.keySet()){
                if (arg.get(role) != extension.get(role)){
                    isOk = false;
                    break;
                }
            }
            if (isOk) 
                return true;
        }
        return false;
    }
    
    public HashMap<String, Constant> getExtension(HashMap<String, String> roleConstName){
        for (HashMap<String, Constant> extension: arguments){
            boolean isOk = true;
            for (String role: roleConstName.keySet()){
                if (!extension.containsKey(role))
                    break;
                if (!roleConstName.get(role).equals(extension.get(role).getName())){
                    isOk = false;
                    break;
                }
            }
            if (isOk) 
                return extension;
        }
        return null;
    }
    //вызывать только после getProjection
    public Extensional getProjection(AbstractSimpleFrame frame){
        Extensional rtrn = new Extensional(frame);
        ArrayList<Quantor> reversedQuantors = new ArrayList<>(frame.getQuantors());
        Collections.reverse(reversedQuantors);
        HashMap<Variable, String> variableRoleAccordance = frame.getVariableRoleAccordance();
        for (Quantor quantor: reversedQuantors){
            
        }
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
    
    public boolean isEmpty(){ return arguments.isEmpty();}
    
    public void clear() { arguments = new ArrayList();}
    
    //вызывать после проекции на константу
    public Extensional minus(ArrayList<Slot> slots) {
        Extensional rtrn = new Extensional(frame);
        for (HashMap<String, Constant> extension: arguments){
            for (Slot slot: slots){
                if (!slot.getArgument().isVariable() && slot.getArgument()!=extension.get(slot.getRole())){
                    rtrn.arguments.add(extension);
                    break;
                }
            }
        }
        return rtrn;
    }

    public void removeExtension(HashMap<String, Constant> extension) {
        arguments.remove(extension);
    }
    public ConDesLanTag toConDesLanTag(){
        ConDesLanTag rtrn = new ConDesLanTag("Extensional");
        for (String role: roleConceptAccordance.keySet()){
            ConDesLanTag roleConcept = new ConDesLanTag("role-concept");
            roleConcept.addSimpleProperty("role", role);
            roleConcept.addSimpleProperty("concept", roleConceptAccordance.get(role).getName());
            rtrn.addComplexTagProperty("role-concept-accordance", roleConcept);
        }
        for (HashMap<String, Constant> extension: arguments){
            ConDesLanTag extensionTag = new ConDesLanTag("Extension");
            for (String role: extension.keySet()){
                ConDesLanTag roleConstantTag = new ConDesLanTag("role-constant");
                roleConstantTag.addSimpleProperty("role", role);
                roleConstantTag.addSimpleProperty("constant", extension.get(role).getName());
                extensionTag.addComplexTagProperty("role-constants", roleConstantTag);
            }
            rtrn.addComplexTagProperty("extensions", extensionTag);
        }
        return rtrn;
    }
}
