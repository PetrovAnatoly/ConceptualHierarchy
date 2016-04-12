/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import Frames.AbstractFrame;
import Frames.AbstractSimpleFrame;
import Frames.EventFrame;
import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Сoncepts.Concept;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class Generator {
    public static void generateSimpleFrames(int count, int nestingProbability, int slotCount){
        int frameId = 0;
        int generatedFramestCount = 0;
        int predicateId = 0;
        int slotId = 0;
        String predicate = "predicate0";
        ArrayList<EventFrame> generatedFrames = new ArrayList<>(); 
        while (!ActualData.avalibleFrameName("frame" + String.valueOf(frameId)))
            frameId++;
        while (ActualData.getAllExtensionals().containsKey(predicate))
            predicate = "predicate" + String.valueOf(++predicateId);
        while (generatedFramestCount < count){
            EventFrame newFrame;
            if (getRandInt(0, 100) < nestingProbability && !generatedFrames.isEmpty()){
                EventFrame base = generatedFrames.get(getRandInt(0, generatedFrames.size()-1));
                ArrayList<Quantor> quantors = (ArrayList<Quantor>) base.getQuantors().clone();
                Body body = base.getBody();
                String basePredicate = base.getPredicate();
                if (quantors.isEmpty()){
                    quantors.add(new Quantor("[1}", (Variable) body.getSlots().get(0).getArgument()));
                }
                else 
                    quantors.get(0).setValue(quantors.get(0).getValue() + 1);
                generatedFrames.remove(base);
                newFrame = new EventFrame("frame" + String.valueOf(frameId), basePredicate, quantors, body);
                generatedFrames.add(newFrame);
                ActualData.addFrameToHierarchy(newFrame);
            }
            else {
                ArrayList<Slot> slots = new ArrayList<>();
                HashMap<Concept, ArrayList<Variable>> newVars = new HashMap<>();
                for (int i = 0; i < slotCount; i++){
                    Concept domen = ActualData.getConcepts().get(getRandInt(0, ActualData.getConcepts().size()-1));
                    Variable var = new Variable("var_of_" + domen.getName(), domen);
                    if (newVars.containsKey(domen))
                        newVars.get(domen).add(var);
                    else {
                        ArrayList<Variable> varsOfConcept = new ArrayList<>();
                        varsOfConcept.add(var);
                        newVars.put(domen, varsOfConcept);
                    }
                    Slot slot = new Slot("role"+String.valueOf(i), var, domen);
                    slots.add(slot);
                }
                Body body = new Body(slots);
                ArrayList<Quantor> quantors = new ArrayList<>();
                newFrame = new EventFrame("frame" + String.valueOf(frameId), "predicate" + String.valueOf(predicateId), quantors, body);
                ActualData.addNewVariables(newVars);
                ActualData.addFrameToHierarchy(newFrame);
                generatedFrames.add(newFrame);
                predicateId++;
            }
            frameId++;
            generatedFramestCount++;
        }
    }
    public static void generateConcepts(int count, int nestingProbability){
        int conceptId = 0;
        int propertyId = 0;
        for (Concept concept: ActualData.getConcepts()){
            while (concept.getProperties().contains("property" + String.valueOf(propertyId)))
                propertyId++;
            while (!ActualData.avalibleConceptName("concept" + String.valueOf(conceptId)))
                conceptId++;
        }
        int generatedConceptCount = 0;
        while (generatedConceptCount < count){
            Concept newConcept = new Concept("concept" + String.valueOf(conceptId));
            conceptId++;
            ArrayList<String> properties = new ArrayList<>();
            properties.add("property" + String.valueOf(propertyId));
            propertyId++;
            if (getRandInt(0, 100) < nestingProbability){
                ArrayList<Concept> actualConcepts = ActualData.getConcepts();
                if (!actualConcepts.isEmpty())
                    properties.addAll(actualConcepts.get(getRandInt(0, actualConcepts.size()-1)).getProperties());
            }
            newConcept.setProperties(properties);
            ActualData.addConceptToHierarchy(newConcept);
            generatedConceptCount++;
        }
    }
    public static void generateConstants(String location, int min, int max){
        
    }
    private static int getRandInt(int from, int to){
        return from + (new Random()).nextInt(to-from+1);
    }
    private boolean frameIsUsable(AbstractSimpleFrame frame){
        boolean rtrn = false;
        for (Quantor quantor: frame.getQuantors()){
            if (!quantor.getType().equals("[]"))
                return true;
        }
        return rtrn;
    }
    
}
