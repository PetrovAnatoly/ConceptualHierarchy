/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import conceptualhierarchy.ActualData;
import java.util.ArrayList;
import java.util.Random;
import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public class Generator {
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
    private static Concept getRandomConceptFromActualData(){
        Concept rtrn = null;
        
        return rtrn;
    }
    public static void generateConstants(String location, int min, int max){
        
    }
    private static int getRandInt(int from, int to){
        return from + (new Random()).nextInt(to-from+1);
    }
}
