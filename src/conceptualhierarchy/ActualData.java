/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Frames.AbstractFrame;
import Frames.AbstractSimpleFrame;
import Frames.Structure.Body;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class ActualData {
    
    private static ArrayList<AbstractFrame> frameSet = new ArrayList();
    private static ArrayList<Concept> conceptSet = new ArrayList();
    private static ConceptNode conceptHierarchyRoot = new ConceptNode(new Concept("Concepts"));
    private static FrameNode frameHierarchyRoot = new FrameNode(
            new AbstractSimpleFrame("Frames", "", new ArrayList(), new Body(new ArrayList())));
    static HashMap<AbstractFrame, FrameNode> frameNodeAccordance = new HashMap();
    static HashMap<Concept, ConceptNode> conceptNodeAccordance = new HashMap();
    static HashMap<String, AbstractFrame> nameFrameAccordance = new HashMap();
    private static HashMap<String, Concept> nameConceptAccordance = new HashMap();
    private static ArrayList<String> conceptNameSet = new ArrayList();
    private static ArrayList<String> frameNameSet = new ArrayList();
    private static HashMap<Concept, ArrayList<String>> constantNameMap = new HashMap();
    private static HashMap<Concept, ArrayList<String>> variableNameMap = new HashMap();
    private static HashMap<Concept, ArrayList<Constant>> constantsInDomen = new HashMap();
    private static HashMap<Concept, ArrayList<Variable>> variablesInDomen = new HashMap();
    private static HashMap<String, HashMap<AbstractSimpleFrame, Extensional>> extensionals = new HashMap();
    private static HashMap<String, Extensional> predicateExtensionals = new HashMap();
    //methods
    public static FrameNode getFrameHoerarchy() {
        return frameHierarchyRoot;
    }

    public static ConceptNode getConceptHoerarchy() {
        return conceptHierarchyRoot;
    }

    public static ArrayList<String> getFrameNameSet() {
        return frameNameSet;
    }

    public static AbstractFrame getFrameByName(String frName) {
        return nameFrameAccordance.get(frName);
    }

    public static Concept getConceptByName(String concName) {
        return nameConceptAccordance.get(concName);
    }

    public static Constant getConstantInDomenByName(String constName, Concept concept) {
        Constant rtrn = null;
        ArrayList<Constant> constants = constantsInDomen.get(concept);
        for (Constant constant : constants) {
            if (constant.getName().equals(constName)) {
                rtrn = constant;
                break;
            }
        }
        if (rtrn == null){
            ConceptNode concNode = conceptNodeAccordance.get(concept);
            for (ConceptNode childNode: concNode.childNodes){
                Concept childConcept = childNode.value; 
                if (!avalibleConstantNameInDomen(constName, childConcept))
                    return getConstantInDomenByName(constName, childConcept);
            }
        }
        return rtrn;
    }
    
    public static ArrayList<Constant> getAllConstantsInDomen(Concept concept){
        ArrayList<Constant> rtrn = new ArrayList();
        if (constantsInDomen.containsKey(concept)) {
            ArrayList<Constant> constants = constantsInDomen.get(concept);
            for(Constant cnst: constants)
                if (!rtrn.contains(cnst))
                    rtrn.add(cnst);
        }
        ConceptNode concNode = conceptNodeAccordance.get(concept);
        for (ConceptNode childNode: concNode.childNodes){
            Concept childConcept = childNode.value; 
            for (Constant cnst: getAllConstantsInDomen(childConcept))
                if (!rtrn.contains(cnst))
                    rtrn.add(cnst);
        }
        return rtrn;
    }

    public static Variable getVariableInDomenByName(String varName, Concept concept) {
        Variable rtrn = null;
        ArrayList<Variable> variables = variablesInDomen.get(concept);
        for (Variable var : variables) {
            if (var.getName().equals(varName)) {
                return var;
            }
        }
        return rtrn;
    }

    public static void addNewConceptName(String arg) {
        conceptNameSet.add(arg);
    }

    public static void addNewFrameName(String arg) {
        frameNameSet.add(arg);
    }

    public static void addNewConstantInDomen(String constantName, Concept domen){
        ArrayList<Concept> ancestors = getIsaSetForConcept(domen);
        Constant constant = new Constant(constantName, domen);
        for (Concept concept: ancestors){
            if (!constantNameMap.containsKey(concept))
                continue;
            ArrayList<String> names = constantNameMap.get(concept);
            if (names.contains(constantName)){
                constant = getConstantInDomenByName(constantName, concept);
                names.remove(constantName);
                constantsInDomen.get(concept).remove(constant);
            }
        }
        constant.setDomen(domen);
        if (constantsInDomen.containsKey(domen))
            constantsInDomen.get(domen).add(constant);
        else{
            ArrayList<Constant> cnstnts = new ArrayList();
            cnstnts.add(constant);
            constantsInDomen.put(domen, cnstnts);
        }
        if (constantNameMap.containsKey(domen))
            constantNameMap.get(domen).add(constantName);
        else {
            ArrayList<String> names = new ArrayList();
            names.add(constantName);
            constantNameMap.put(domen, names);
        }
    }
    
    public static void addNewConstantNameInDomen(String arg, Concept concept) {
        if (constantNameMap.containsKey(concept)) {
            ArrayList<String> nameSetInDomen = constantNameMap.get(concept);
            nameSetInDomen.add(arg);
        } else {
            ArrayList<String> newDomenSet = new ArrayList();
            newDomenSet.add(arg);
            constantNameMap.put(concept, newDomenSet);
        }
    }

    public static void addNewVariableNameInDomen(String arg, Concept concept) {
        if (variableNameMap.containsKey(concept)) {
            ArrayList<String> nameSetInDomen = variableNameMap.get(concept);
            nameSetInDomen.add(arg);
        } else {
            ArrayList<String> newDomenSet = new ArrayList();
            newDomenSet.add(arg);
            variableNameMap.put(concept, newDomenSet);
        }
    }

    public static boolean avalibleConceptName(String arg) {
        for (String s : conceptNameSet) {
            if (s.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    public static boolean avalibleFrameName(String arg) {
        for (String s : frameNameSet) {
            if (s.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    public static boolean avalibleConstantNameInDomen(String arg, Concept concept) {
        if (!constantNameMap.containsKey(concept)) {
            return true;
        }
        ArrayList<String> constantNameSetInDomen = constantNameMap.get(concept);
        for (String s : constantNameSetInDomen) {
            if (s.equals(arg)) {
                return false;
            }
        }
        ConceptNode concNode = conceptNodeAccordance.get(concept);
        for (ConceptNode childNode: concNode.childNodes){
            Concept childConcept = childNode.value; 
            if (!avalibleConstantNameInDomen(arg, childConcept))
                return false;
        }
        return true;
    }

    public static boolean avalibleVariableNameInDomen(String arg, Concept concept) {
        if (!variableNameMap.containsKey(concept)) {
            return true;
        }
        ArrayList<String> variableNameSetInDomen = variableNameMap.get(concept);
        for (String s : variableNameSetInDomen) {
            if (s.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    public static void addFrameToHierarchy(AbstractFrame arg) {
        FrameNode newNode = new FrameNode(arg);
        ArrayList<AbstractFrame> forefatherSet = getIsaSetForFrame(arg);
        ArrayList<AbstractFrame> setOfParentFrames = getIsaSetForFrameWithoutTransitivity(forefatherSet);
        if (setOfParentFrames.isEmpty()) {
            frameHierarchyRoot.addChild(newNode);
            newNode.addParent(frameHierarchyRoot);
            frameHierarchyRoot.reformIfNeeded(newNode);
        } else {
            for (AbstractFrame i : setOfParentFrames) {
                FrameNode node = frameNodeAccordance.get(i);
                node.addChild(newNode);
                newNode.addParent(node);
                node.reformIfNeeded(newNode);
            }
        }
        frameSet.add(arg);
        frameNameSet.add(arg.getName());
        frameNodeAccordance.put(arg, newNode);
        nameFrameAccordance.put(arg.getName(), arg);
        if (arg instanceof AbstractSimpleFrame) {
            String predicate = ((AbstractSimpleFrame) arg).getPredicate();
            if (!extensionals.containsKey(predicate)){
                predicateExtensionals.put(predicate, new Extensional((AbstractSimpleFrame) arg));
                HashMap<AbstractSimpleFrame, Extensional> thisPredicateExtensionals = new HashMap();
                thisPredicateExtensionals.put((AbstractSimpleFrame) arg, new Extensional((AbstractSimpleFrame) arg));
                extensionals.put(predicate, thisPredicateExtensionals); 
            }
            else {
                extensionals.get(predicate).put((AbstractSimpleFrame) arg, new Extensional((AbstractSimpleFrame) arg));
            }
            
        }
    }

    public static void addConceptToHierarchy(Concept arg) {
        ConceptNode newNode = new ConceptNode(arg);  
        ArrayList<Concept> forefatherSet = getIsaSetForConcept(arg);
        ArrayList<Concept> setOfParent = getIsaSetForConceptWithoutTransitivity(forefatherSet);
        if (setOfParent.isEmpty()) {
            conceptHierarchyRoot.addChild(newNode);
            newNode.addParent(conceptHierarchyRoot);
            conceptHierarchyRoot.reformIfNeeded(newNode); 
        } else {
            for (Concept i : setOfParent) {
                ConceptNode node = conceptNodeAccordance.get(i);
                node.addChild(newNode);
                newNode.addParent(node);
                node.reformIfNeeded(newNode);
            }
        }
        conceptSet.add(arg);
        conceptNameSet.add(arg.getName());
        conceptNodeAccordance.put(arg, newNode);
        nameConceptAccordance.put(arg.getName(), arg);
    }

    public static void addNewConcepts(ArrayList<Concept> newConcepts) {
        for (Concept conc : newConcepts) {
            addConceptToHierarchy(conc);
        }
    }

    public static void addNewConstants(HashMap<Concept, ArrayList<Constant>> newConstants) {
        for (Concept key : newConstants.keySet()) {
            if (constantsInDomen.containsKey(key)) {
                ArrayList<Constant> constantsOfThisConcept = constantsInDomen.get(key);
                ArrayList<String> constNamesOfThisConcept = constantNameMap.get(key);
                for (Constant cnst : newConstants.get(key)) {
                    constantsOfThisConcept.add(cnst);
                    constNamesOfThisConcept.add(cnst.getName());
                }
            } else {
                ArrayList<Constant> constantsOfThisConcept = new ArrayList();
                ArrayList<String> constNamesOfThisConcept = new ArrayList();
                for (Constant cnst : newConstants.get(key)) {
                    constantsOfThisConcept.add(cnst);
                    constNamesOfThisConcept.add(cnst.getName());
                }
                constantsInDomen.put(key, constantsOfThisConcept);
                constantNameMap.put(key, constNamesOfThisConcept);
            }
        }
    }

    public static void addNewVariables(HashMap<Concept, ArrayList<Variable>> newVariables) {
        for (Concept key : newVariables.keySet()) {
            if (variablesInDomen.containsKey(key)) {
                ArrayList<Variable> variablesOfThisConcept = variablesInDomen.get(key);
                ArrayList<String> varNamesOfThisConcept = variableNameMap.get(key);
                for (Variable cnst : newVariables.get(key)) {
                    variablesOfThisConcept.add(cnst);
                    varNamesOfThisConcept.add(cnst.getName());
                }
            } else {
                ArrayList<Variable> variablesOfThisConcept = new ArrayList();
                ArrayList<String> varNamesOfThisConcept = new ArrayList();
                for (Variable var : newVariables.get(key)) {
                    variablesOfThisConcept.add(var);
                    varNamesOfThisConcept.add(var.getName());
                }
                variablesInDomen.put(key, variablesOfThisConcept);
                variableNameMap.put(key, varNamesOfThisConcept);
            }
        }
    }

    private static ArrayList<AbstractFrame> getIsaSetForFrame(AbstractFrame arg) {
        ArrayList<AbstractFrame> rtrnSet = new ArrayList();
        for (AbstractFrame i : frameSet) {
            if (arg != i && arg.ISA(i)) {
                rtrnSet.add(i);
            }
        }
        return rtrnSet;
    }
    private static ArrayList<Concept> getIsaSetForConcept(Concept arg) {
        ArrayList<Concept> rtrnSet = new ArrayList();
        for (Concept i : conceptSet) {
            if (arg!=i && arg.ISA(i)) { 
                rtrnSet.add(i);
            } 
        }
        return rtrnSet;
    }
    private static ArrayList<AbstractFrame> getIsaSetForFrameWithoutTransitivity(ArrayList<AbstractFrame> arg) {
        ArrayList<AbstractFrame> rtrnSet = new ArrayList();
        for (AbstractFrame verifiableFrame : arg) {
            boolean b = true;
            FrameNode verifiableNode = frameNodeAccordance.get(verifiableFrame);
            for (AbstractFrame k : arg) {
                FrameNode node = frameNodeAccordance.get(k);
                if (node.isDescedentOf(verifiableNode)) {
                    b = false;
                    break;
                }
            }
            if (b) {
                rtrnSet.add(verifiableFrame);
            }
        }
        return rtrnSet;
    }
    private static ArrayList<Concept> getIsaSetForConceptWithoutTransitivity(ArrayList<Concept> arg) {
        ArrayList<Concept> rtrnSet = new ArrayList();
        for (Concept verifiableConcept : arg) {
            boolean b = true;
            ConceptNode verifiableNode = conceptNodeAccordance.get(verifiableConcept);
            for (Concept k : arg) {
                ConceptNode node = conceptNodeAccordance.get(k);
                if (node.isDescedentOf(verifiableNode)) {
                    b = false;
                    break;
                }
            }
            if (b) {
                rtrnSet.add(verifiableConcept);
            }
        }
        return rtrnSet;
    }
    public static void showFrameHierarchyAsTree() {
        frameHierarchyRoot.showAsTree();
    }

    public static void removeFrameByName(String frName) {
        AbstractFrame fr = getFrameByName(frName);
        FrameNode node = frameNodeAccordance.get(fr);
        for (FrameNode prnt : node.parents) {
            prnt.childNodes.remove(node);
        }
        frameSet.remove(fr);
        frameNodeAccordance.remove(fr);
        nameFrameAccordance.remove(fr.getName());
        frameNameSet.remove(fr.getName());
        ArrayList<FrameNode> chNodes = (ArrayList<FrameNode>) node.childNodes.clone();
        node.childNodes.clear();
        for (FrameNode ch : chNodes) {
            ch.removeParent(node);
            ArrayList<AbstractFrame> isaFrames = getIsaSetForFrameWithoutTransitivity(getIsaSetForFrame(ch.value));
            if (isaFrames.isEmpty()) {
                frameHierarchyRoot.addChild(ch);
                ch.addParent(frameHierarchyRoot);
            }
            for (AbstractFrame parentFr : isaFrames) {
                FrameNode parentNode = frameNodeAccordance.get(parentFr);
                if (!parentNode.childNodes.contains(ch)) {
                    parentNode.addChild(ch);
                    ch.addParent(parentNode);
                }
            }
        }
    }
    
    public static boolean conceptWithThisPropertiesIsExist(ArrayList<String> arg){
        if (arg.size() == 0) return false;
        for (Concept conc: conceptSet){
            if (conc.getProperties().containsAll(arg) && arg.containsAll(conc.getProperties()))
                return true;
        }
        return false;
    }
    public static void removeConceptByName(String concName){
        Concept conc = getConceptByName(concName);
        ConceptNode node = conceptNodeAccordance.get(conc);
        ArrayList<Constant> constants = new ArrayList();
        boolean domesHasConstants = false;
        if (constantsInDomen.containsKey(conc) && !constantsInDomen.get(conc).isEmpty()){
            constants.addAll(constantsInDomen.get(conc));
            domesHasConstants = true;
        }
        for (ConceptNode prnt : node.parents) {
            prnt.childNodes.remove(node);
            if (domesHasConstants){
                ArrayList<Constant> constantsOfThisParent = new ArrayList();
                if (constantsInDomen.containsKey(prnt.getValue()))
                    constantsOfThisParent = constantsInDomen.get(prnt.getValue());
                else 
                    constantsInDomen.put(prnt.getValue(), constantsOfThisParent);
                for (Constant constant: constants)
                    if (!constantsOfThisParent.contains(constant))
                        constantsOfThisParent.add(constant);
            }
        }
        node.parents.clear(); // ?
        conceptSet.remove(conc);
        conceptNodeAccordance.remove(conc);
        nameConceptAccordance.remove(conc.getName());
        conceptNameSet.remove(conc.getName());
        ArrayList<ConceptNode> chNodes = (ArrayList<ConceptNode>) node.childNodes.clone();
        node.childNodes.clear();
        for (ConceptNode ch : chNodes) {
            ch.removeParent(node);
            ArrayList<Concept> isaConcepts = getIsaSetForConceptWithoutTransitivity(getIsaSetForConcept((Concept) ch.value));
            if (isaConcepts.isEmpty()) {
                conceptHierarchyRoot.addChild(ch);
                ch.addParent(conceptHierarchyRoot);
            }
            for (Concept parentConc : isaConcepts) {
                ConceptNode parentNode = conceptNodeAccordance.get(parentConc);
                if (!parentNode.childNodes.contains(ch)) {
                    parentNode.addChild(ch);
                    ch.addParent(parentNode);
                }
                
            }
        }
    }  
    public static void renameConcept(String oldName, String newName){
        conceptNameSet.remove(oldName);
        conceptNameSet.add(newName);
        Concept conc = nameConceptAccordance.remove(oldName);
        nameConceptAccordance.put(newName, conc);
    }
    
    public static HashSet<String> getNamesOfFramesThatUseConcept(Concept concept){
        HashSet<String> frameNames = new HashSet(); 
        for (AbstractFrame fr: frameSet){
            if (fr.conceptIsUsed(concept))     
                frameNames.add(fr.getName()); 
        }
        return frameNames;
    }
    public static HashSet<AbstractFrame> getFramesThatUseConcept(Concept concept){
        HashSet<AbstractFrame> frames = new HashSet();
        for (AbstractFrame fr: frameSet){
            if (fr.conceptIsUsed(concept))    
                frames.add(fr); 
        }
        return frames;
    }
    
    public static boolean constantIsUsed(Constant constant){
        for (AbstractFrame fr: frameSet){
            if (fr.constantIsUsed(constant))
                return true;
        }
        return false;
    }
    
    public static HashSet<String> getUsedConstantNamesOfThisConcept(Concept conc){
        HashSet<AbstractFrame> frames = getFramesThatUseConcept(conc);
        ArrayList<Constant> uncheckedConstantsOfThisConcept = ActualData.getAllConstantsInDomen(conc);
        HashSet<String> usedConstantNames = new HashSet();
        for (AbstractFrame fr: frames){
            for (Constant cnst: uncheckedConstantsOfThisConcept)
                if (fr.constantIsUsed(cnst)){
                    usedConstantNames.add(cnst.getName());
                    uncheckedConstantsOfThisConcept.remove(cnst);
                    break;
                }
        }
        return usedConstantNames;
    } 
    public static HashSet<Constant> getUsedConstantsOfThisConcept(Concept conc){
        HashSet<AbstractFrame> frames = getFramesThatUseConcept(conc);
        ArrayList<Constant> uncheckedConstantsOfThisConcept = ActualData.getAllConstantsInDomen(conc);
        HashSet<Constant> usedConstants = new HashSet();
        for (AbstractFrame fr: frames){
            for (Constant cnst: uncheckedConstantsOfThisConcept)
                if (fr.constantIsUsed(cnst)){
                    usedConstants.add(cnst);
                    uncheckedConstantsOfThisConcept.remove(cnst);
                    break;
                }
        }
        return usedConstants;
    } 

    public static void removeConstantInDomenByName(String constantName, Concept concept) {
        Constant constant = getConstantInDomenByName(constantName, concept);
        Concept domen = constant.getDomen();
        constantsInDomen.get(domen).remove(constant);
        constantNameMap.get(domen).remove(constant.getName());
    }
    
    public static Extensional getExtensional(AbstractSimpleFrame frame){
        if (extensionals.containsKey(frame))
            return extensionals.get(frame.getPredicate()).get(frame);
        else return new Extensional(frame); 
        
    }
    public static Extensional getExtensional(String predicate){
        if (predicateExtensionals.containsKey(predicate))
            return predicateExtensionals.get(predicate);
        else return new Extensional(new AbstractSimpleFrame()); 
    }
    public static Extensional getAllExtensionsWithThisRoleDomenSystem(){
        return null;
    }
    public static boolean frameExtensionalIsGettable(AbstractSimpleFrame frame){
        if (!frame.isClosed())
            return true;
        
        
        return false;
    }
    public static void addExtension(ArrayList<Concept> arguments){
        
    }
    public boolean frameExtensionalIsDeducible(AbstractSimpleFrame frame){
        return false;
    }
   
    //public boolean frameHas
}
