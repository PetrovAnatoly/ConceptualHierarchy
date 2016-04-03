/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import Frames.Structure.SlotArgument;
import ModelInputLoad.ConDesLanTag;
import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import java.util.HashMap;  
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class AbstractSimpleFrame extends AbstractFrame {
    //constr
    public AbstractSimpleFrame(String frameName, 
            String framePredicate,ArrayList<Quantor> frameQuantors,Body frameBody){
        name = frameName;
        predicate = framePredicate;
        quantors = frameQuantors;
        body = frameBody;
    }
    
    
    private String predicate=new String();
    private ArrayList<Quantor> quantors = new ArrayList(); 
    private Body body=new Body();

    public AbstractSimpleFrame() {
        
    }
    
    private Quantor getQuantorByVariable(SlotArgument var)
    {
        for (Quantor q: quantors)
            if (q.getVariable() == var) 
                return q; 
        return new Quantor();
    }
        
    public String getPredicate() { return predicate;}
    public void setPredicate(String str) { predicate = str;}
    public Body getBody(){ return body;}  
    public ArrayList<Quantor> getQuantors() { return quantors;}
    public String getQuantorsLine() {
        String rtrn = "";
        for (Quantor qntr: quantors){
            rtrn += qntr.toString();
        }
        return rtrn;
    }
    @Override
    public boolean ISA(AbstractSimpleFrame arg)
    {
        if (arg == this)
            return true;
        if (!predicate.equals(arg.predicate))
            return false;
        if (!body.coveredAndSimilar(arg.body))
            return false;
        if (arg.quantors.isEmpty())
            return true;
        ArrayList<Variable> thisQuantifiedVariables = getQuantifiedVariables();
        for (Variable var: arg.getQuantifiedVariables()){
            if (this.getVarByRole(arg.getRoleByVariable(var)).getName().equals(""))
                return false;
        }
        if (quantorsDifferentOrdered(arg))
            return false;
        HashMap<SlotArgument, SlotArgument> varAccording = Body.varAccording;
        for (SlotArgument var: varAccording.keySet()){
            Quantor thisQuantor = getQuantorByVariable(var);
            Quantor argQuantor = arg.getQuantorByVariable(varAccording.get(var));
            if (!thisQuantor.covered(argQuantor))
                return false;
        }
        return true;
    } 
      
    @Override
    public boolean ISA(OrFrame arg){
        return ISA(arg.firstOperand) || ISA(arg.secondOperand);
    }
    //!!!
    public AbstractSimpleFrame getMergeFrame(AbstractSimpleFrame arg){
        AbstractSimpleFrame rtrn;
        
        
        return this;
    }
    //!!!
    public boolean isContrary(AbstractSimpleFrame arg){
        boolean rtrn = false;
        if (!this.predicate.equals(arg.predicate))
            return false;
        ArrayList<Variable> thisVariables = body.getAllVariablesInBody();
        ArrayList<Variable> argVariables = arg.body.getAllVariablesInBody();
        if (thisVariables.size() != quantors.size())
            return false;
        if (argVariables.size() != arg.quantors.size())
            return false;
        
        if (ISA(arg) || arg.ISA(this))
            return false;
        if (quantorsDifferentOrdered(arg))
        /*    if (((allQuantorsIsExistType() || allQuantorsIsOnenessType()) && 
                    (arg.allQuantorsIsExistType() || arg.allQuantorsIsOnenessType()))
                    ||
                    (this.allQuantorsIsUniversalityType() && arg.allQuantorsIsUniversalityType()))*/
            return false;
        HashMap<Variable, String> thisVrblRoleAccord = getVariableRoleAccordance();
        HashMap<Variable, String> argVrblRoleAccord = arg.getVariableRoleAccordance();
        if (thisVariables.size() == argVariables.size()){
            
        }
        return rtrn;
    }
    //!!
    public HashMap<Variable, String> getVariableRoleAccordance(){
        HashMap<Variable, String> rtrn = new HashMap();
        for (Slot slot: body.getSlots())
            if (slot.getArgument() instanceof Variable)
                rtrn.put((Variable)slot.getArgument(), slot.getRole());
        return rtrn;
    }
    private String getRoleByVariable(Variable var){
        String rtrn = "";
        for (Slot slot: body.getSlots())
            if (slot.getArgument() == var)
                return slot.getRole();
        return rtrn;
    }
    private Variable getVarByRole(String role){
        Variable rtrn = new Variable("");
        for (Slot slot: body.getSlots())
            if (slot.getRole().equals(role)){
                if (slot.getArgument() instanceof Variable)
                    return (Variable) slot.getArgument();
                else return rtrn;
            }
        return rtrn;
    }
    private int getVariableCount(){
        int i = 0;
        for (Slot slot: body.getSlots())
            if (slot.getArgument().isVariable())
                i++;
        return i;
    }
    public ArrayList<String> getRoles() { 
        ArrayList<String> rtrn = new ArrayList<>();
        for (Slot slot: body.getSlots())
            rtrn.add(slot.getRole());
        return rtrn;
    }
    public ArrayList<Variable> getQuantifiedVariables(){
        ArrayList<Variable> rtrn = new ArrayList();
        for (Quantor quantor: quantors)
            rtrn.add(quantor.getVariable());
        return rtrn;
    }
    private boolean allQuantorsIsExistType(){
        for (Quantor qntr: quantors)
            if (!qntr.toString().contains("[1}"))
                return false;
        return true;
    }
    private boolean allQuantorsIsUniversalityType(){
        for (Quantor qntr: quantors)
            if (!qntr.toString().contains("A"))
                return false;
        return true;
    }
    private boolean allQuantorsIsOnenessType(){
        for (Quantor qntr: quantors)
            if (!qntr.toString().contains("[1]"))
                return false;
        return true;
    }
    
    private boolean quantorsDifferentOrdered(AbstractSimpleFrame arg){
        if (quantors.size()<2 || arg.quantors.size()<2)
            return false;
        ArrayList<Variable> thisQuantifiedVariables = getQuantifiedVariables();
        ArrayList<Variable> argQuantifiedVariables = arg.getQuantifiedVariables();
        int index = 0;
        for (Quantor qntr: quantors){
            String role = getRoleByVariable(qntr.getVariable());
            Variable argVar = arg.getVarByRole(role);
            if (!argQuantifiedVariables.contains(argVar))
                continue;
            else{
                while (arg.quantors.get(index).getVariable()!=argVar){
                    index++;
                    if (index>arg.quantors.size()-1)
                        return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean conceptIsUsed(Concept concept) {
        for (Slot slot: body.getSlots())
            if (slot.getDomen() == concept)
                return true;
        return false;
    }
    @Override
    public boolean constantIsUsed(Constant constant){
        for (Slot slot: body.getSlots())
            if (slot.getArgument() == constant)
                return true;
        return false;
    }
    public boolean extensionIsCorrect(ArrayList<String> constants){
        ArrayList<Slot> slots = body.getSlots();
        if (constants.size()!= slots.size())
            return false;
        for (int i = 0; i<slots.size(); i++)
            if (!slots.get(i).getArgument().isVariable()){
                if (!slots.get(i).getArgument().getName().equals(constants.get(i)))
                    return false;
            }
        return true;
    }
    public boolean isClosed(){
        return getVariableCount() == quantors.size();
    }

    public ConDesLanTag toConDesLanTag() {
        ConDesLanTag rtrn = new ConDesLanTag("фрейм");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("тип", this instanceof EventFrame ? "event":"characteristic");
        rtrn.addSimpleProperty("предикат", predicate);
        rtrn.addSimpleProperty("кванторы", getQuantorsLine());
        for (ConDesLanTag slotTag : body.toConDesLanTags())
            rtrn.addComplexTagProperty("слоты", slotTag);
        return rtrn; 
    }
    
}
   