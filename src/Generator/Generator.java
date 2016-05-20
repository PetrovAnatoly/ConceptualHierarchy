/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

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
        String predicate = "predicate0";
        ArrayList<EventFrame> generatedFrames = new ArrayList<>();
        ArrayList<EventFrame> generatedFramesToUseAsBase = new ArrayList<>();
        while (!ActualData.avalibleFrameName("frame" + String.valueOf(frameId)))
            frameId++;
        while (ActualData.getAllExtensionals().containsKey(predicate))
            predicate = "predicate" + String.valueOf(++predicateId);
        while (generatedFramestCount < count){
            EventFrame newFrame;
            if (getRandInt(0, 100) <= nestingProbability && !generatedFramesToUseAsBase.isEmpty()){
                EventFrame base = generatedFramesToUseAsBase.get(getRandInt(getRandInt(0, generatedFramesToUseAsBase.size()-1), generatedFramesToUseAsBase.size()-1));
                ArrayList<Quantor> quantors = base.cloneQuantors();
                Body body = base.getBody();
                String basePredicate = base.getPredicate();
                newFrame = new EventFrame("frame" + String.valueOf(frameId), basePredicate, quantors, body);
                if (quantors.isEmpty()){
                    int k = getRandInt(0, body.getSlots().size()-1);
                    quantors.add(new Quantor("[1}", (Variable) body.getSlots().get(k).getArgument()));
                }
                else {
                    if (getRandInt(0, 100) <= 100/slotCount || quantors.size() == body.getSlots().size()){
                        int k = getRandInt(0, quantors.size()-1);
                        quantors.get(k).setValue(quantors.get(k).getValue() + 1);
                    }
                    else {
                        ArrayList<Variable> notQuantifVars = newFrame.getNotQuantifiedVariables();
                        quantors.add(new Quantor("[1}", (Variable) notQuantifVars.get(0)));
                    }
                }
                boolean frameIsAlreadyAdded = false;
                for (EventFrame fr: generatedFrames)
                    if (fr.ISA(newFrame) && newFrame.ISA(fr)){
                        frameIsAlreadyAdded = true;
                        break;
                    }
                while (frameIsAlreadyAdded){
                    if (getRandInt(0, 100) <= 100/slotCount || quantors.size() == body.getSlots().size()){
                        int k = getRandInt(0, quantors.size()-1);
                        quantors.get(k).setValue(quantors.get(k).getValue() + 1);
                    }
                    else {
                        ArrayList<Variable> notQuantifVars = newFrame.getNotQuantifiedVariables();
                        quantors.add(new Quantor("[1}", (Variable) notQuantifVars.get(0)));
                    }
                    for (EventFrame fr: generatedFrames)
                        if (fr.ISA(newFrame) && newFrame.ISA(fr))
                            break;
                    frameIsAlreadyAdded = false;
                    if (getRandInt(1,100)<=75)
                        generatedFramesToUseAsBase.remove(base);
                }
                if (!frameIsAlreadyAdded){
                    generatedFrames.add(newFrame);
                    generatedFramesToUseAsBase.add(newFrame);
                    ActualData.addFrameToHierarchy(newFrame);
                    if (getRandInt(1,5)<=2)
                        generatedFramesToUseAsBase.remove(base);
                }
                else 
                    continue;
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
                generatedFramesToUseAsBase.add(newFrame);
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
            if (getRandInt(0, 100) <= nestingProbability){
                ArrayList<Concept> actualConcepts = ActualData.getConcepts();
                if (!actualConcepts.isEmpty())
                    properties.addAll(actualConcepts.get(getRandInt(0, actualConcepts.size()-1)).getProperties());
            }
            newConcept.setProperties(properties);
            ActualData.addConceptToHierarchy(newConcept);
            generatedConceptCount++;
        }
    }
    public static void generateConstants(String location, int constantCountMin, int constantCountMax){
        boolean forAll = false;
        if (location.equals("All concepts"))
            forAll = true;
        ArrayList<Concept> concepts = new ArrayList<>();
        if (forAll)
            concepts = ActualData.getConcepts();
        else 
            concepts.add(ActualData.getConceptByName(location));
        for (Concept concept: concepts){
            int count = getRandInt(constantCountMin, constantCountMax);
            int counter = 0;
            int id = 0;
            while (counter<count){
                String constantName = "\'constant_of_" + concept.getName() + "_" + String.valueOf(id) + "\'";
                if (ActualData.avalibleConstantNameInDomen(constantName, concept)){
                    ActualData.addNewConstantInDomen(constantName, concept);
                    counter++;
                }
                id++;
            }
        }
    }
    private static int getRandInt(int from, int to){
        return from + (new Random()).nextInt(to-from+1);
    }
}
