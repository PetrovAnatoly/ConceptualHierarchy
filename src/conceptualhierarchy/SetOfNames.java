/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import Сoncepts.Concept;
import Сoncepts.Variable;

/**  
 *
 * @author Anatoly
 */
public  class SetOfNames {
    //fields
    private static ArrayList<String> conceptNameSet;
    private static ArrayList<String> frameNameSet;
    private static HashMap<Concept, ArrayList<String>> constantNameMap;
    private static HashMap<Concept, ArrayList<String>> variableNameMap;
    //methods
    public static void addNewConceptName(String arg) {conceptNameSet.add(arg);}
    public static void addNewFrameName(String arg) {frameNameSet.add(arg);}
    public static void addNewConstantNameInDomen(String arg, Concept concept) {
        if (constantNameMap.containsKey(concept)){
            ArrayList<String> nameSetInDomen = constantNameMap.get(concept);
            nameSetInDomen.add(arg);}
        else{
            ArrayList<String> newDomenSet = new ArrayList();
            newDomenSet.add(arg);
            constantNameMap.put(concept, newDomenSet);
        }
    }
    public static void addNewVariableNameInDomen(String arg, Concept concept) {
        if (variableNameMap.containsKey(concept)){
            ArrayList<String> nameSetInDomen = variableNameMap.get(concept);
            nameSetInDomen.add(arg);}
        else{
            ArrayList<String> newDomenSet = new ArrayList();
            newDomenSet.add(arg);
            variableNameMap.put(concept, newDomenSet);
        }
    }
    
    public static boolean avalibleConceptName(String arg){
        for (String s: conceptNameSet)
            if (s.equals(arg)) return false;
        return true;
    }
    public static boolean avalibleFrameName(String arg){
        for (String s: frameNameSet)
            if (s.equals(arg)) return false;
        return true;
    }
    public static boolean avalibleConstantNameInDomen(String arg, Concept concept){
        ArrayList<String> constantNameSetInDomen = constantNameMap.get(concept);
        for (String s: constantNameSetInDomen)
            if (s.equals(arg)) return false;
        return true;
    }
    public static boolean avalibleVariableNameInDomen(String arg, Concept concept){
        ArrayList<String> variableNameSetInDomen = variableNameMap.get(concept);
        for (String s: variableNameSetInDomen)
            if (s.equals(arg)) return false;
        return true;
    } 
}
