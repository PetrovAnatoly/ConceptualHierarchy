/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import ModelInputLoad.InputOutputXML;
import Frames.AbstractFrame;
import Frames.AbstractSimpleFrame;
import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class ActualData {
    private static ActualConfiguration configuration = new ActualConfiguration();
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
    static HashMap<String, Extensional> predicateExtensionals = new HashMap();
    private static HashMap<AbstractSimpleFrame, ArrayList<DefConcept>> defFrameConcept = new HashMap<>();
    //methods
    public static FrameNode getFrameHoerarchy() {
        return frameHierarchyRoot;
    }
    public static boolean framesMultipleInhIsExists(){
        for (FrameNode frNode: frameNodeAccordance.values())
            if (frNode.getParents().size()>1)
                return true;
        return false;
    }
    public static boolean conceptsMultipleInhIsExists(){
        for (ConceptNode concNode: conceptNodeAccordance.values())
            if (concNode.parents.size()>1)
                return true;
        return false;
    }
    public static ArrayList<DefConcept> getDefConcepts(Concept base){
        ArrayList<DefConcept> rtrn = new ArrayList<>();
        for (ArrayList<DefConcept> dCs: defFrameConcept.values())
            for (DefConcept dC: dCs)
                if (dC.getBaseConcept() == base)
                    rtrn.add(dC);
        return rtrn;
    }
    public static ActualConfiguration getConfiguration() { return configuration;}
    public static ConceptNode getConceptHoerarchy() {
        return conceptHierarchyRoot;
    }
    public static ArrayList<AbstractFrame> getFrames() { return frameSet;}
    public static HashMap<String, Extensional> getAllExtensionals() { return predicateExtensionals;}
    public static ArrayList<Concept> getConcepts() { return conceptSet;}
    public static HashMap<Concept, ArrayList<Constant>> getConstants() { return constantsInDomen;}
    public static HashMap<Concept, ArrayList<Variable>> getVariables() { return variablesInDomen;}
    public static ArrayList<String> getFrameNameSet() {
        return frameNameSet;
    }
    public static FrameNode getFrameNode(AbstractFrame arg) { return frameNodeAccordance.get(arg);}
    public static AbstractFrame getFrameByName(String frName) {
        return nameFrameAccordance.get(frName);
    }
    public static ArrayList<DefConcept> getDefConcepts(AbstractSimpleFrame frame){
        ArrayList<DefConcept> rtrn = new ArrayList<>();
        if (defFrameConcept.containsKey(frame))
            return defFrameConcept.get(frame);
        else return rtrn;
    }
    public static Concept getConceptByName(String concName) {
        return nameConceptAccordance.get(concName);
    }

    public static Constant getConstantInDomenByName(String constName, Concept concept) {
        Constant rtrn = null;
        if (!constantsInDomen.containsKey(concept))
            return rtrn;
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
    
    
    public static ArrayList<Constant> getConstantsOfDefConcept(DefConcept concept){
        ArrayList<Constant> rtrn = new ArrayList();
        Extensional defFrameExt = getExtensional((concept.getDefFrame()));
        for (HashMap<String, Constant> consts: defFrameExt.getExtensions())
            if (consts.containsKey(concept.getRole()))
                if (!rtrn.contains(consts.get(concept.getRole())))
                    rtrn.add(consts.get(concept.getRole()));
        return rtrn;
    }
    public static ArrayList<Constant> getAllConstantsInDomen(Concept concept){
        ArrayList<Constant> rtrn = new ArrayList();
        //if (concept instanceof DefConcept)
          //  return rtrn;
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
    
    public static int getConstantInDomenCount(Concept concept){
        if (concept instanceof DefConcept) 
            return ActualData.getConstantsOfDefConcept((DefConcept) concept).size();
        else
            return getAllConstantsInDomen(concept).size();
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

    public static Extensional getExtensionalOfPredicate(String predicate){
        return predicateExtensionals.get(predicate);
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
    
    private static boolean succesAdding;
    public static boolean addingIsSucces() { return succesAdding;}
    public static ArrayList<AbstractFrame> getAllDirectChilds(AbstractFrame frame){
        ArrayList<AbstractFrame> rtrn = new ArrayList<>();
        for (AbstractFrame fr: frameSet)
            if (fr.ISA(frame))
                rtrn.add(fr);
        int i = 0;
        while (i < rtrn.size()-1){
            FrameNode frstNode = ActualData.getFrameNode(rtrn.get(i));
            for (int k = 0; k < rtrn.size(); k++){
                FrameNode scndNode = ActualData.getFrameNode(rtrn.get(k));
                if (scndNode.isDescedentOf(frstNode)){
                    rtrn.remove(i);
                    break;
                }
            }
            i++;
        }
        return rtrn;
    }
    public static void reformFrameTree(){
        frameHierarchyRoot = new FrameNode(new AbstractSimpleFrame("Frames", "", new ArrayList(), new Body(new ArrayList())));
        frameNodeAccordance.clear();
        //HashMap<AbstractFrame, FrameNode> newFrNodeAcc = new HashMap<>();
        //frameNodeAccordance = newFrNodeAcc;
        for (AbstractFrame arg: frameSet){
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
            frameNodeAccordance.put(arg, newNode);
        }
    }
    public static void addFrameToHierarchy(AbstractFrame arg) {
        FrameNode newNode = new FrameNode(arg);
        ArrayList<AbstractFrame> forefatherSet = getIsaSetForFrame(arg);
        ArrayList<AbstractFrame> setOfParentFrames = getIsaSetForFrameWithoutTransitivity(forefatherSet);
        if (!configuration.getBoolSettings().get("framesMultipleInheritance")){
            if (setOfParentFrames.size()>1){
                succesAdding = false;
                return;
            }
        }
        if (setOfParentFrames.isEmpty()) {
            frameHierarchyRoot.addChild(newNode);
            newNode.addParent(frameHierarchyRoot);
            frameHierarchyRoot.reformIfNeeded(newNode);
        } else {
            for (AbstractFrame i: setOfParentFrames) {
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
        if (!configuration.getBoolSettings().get("framesMultipleInheritance")){
            for (FrameNode child: newNode.childNodes){
                if (child.parents.size()>1){
                    ActualData.removeFrameByName(arg.getName());
                    succesAdding = false;
                    return;
                }
            }
        }
        if (arg instanceof AbstractSimpleFrame) {
            String predicate = ((AbstractSimpleFrame) arg).getPredicate();
            if (!predicateExtensionals.containsKey(predicate))
                predicateExtensionals.put(predicate, new Extensional((AbstractSimpleFrame) arg));
            else 
                predicateExtensionals.get(predicate).exploreRoles(((AbstractSimpleFrame)arg).getBody());
        }
        succesAdding = true;
    }
    public static void addConceptToHierarchy(Concept arg) {
        ConceptNode newNode = new ConceptNode(arg);  
        ArrayList<Concept> forefatherSet = getIsaSetForConcept(arg);
        ArrayList<Concept> setOfParent = getIsaSetForConceptWithoutTransitivity(forefatherSet);
        if (!configuration.getBoolSettings().get("conceptsMultipleInheritance")){
            if (setOfParent.size()>1){
                succesAdding = false;
                return;
            }
        }
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
        if (!configuration.getBoolSettings().get("conceptsMultipleInheritance")){
            for (ConceptNode child: newNode.childNodes){
                if (child.parents.size()>1){
                    ActualData.removeConceptByName(arg.getName());
                    succesAdding = false;
                    return;
                }
            }
        }
        if (!constantNameMap.containsKey(arg))
            constantNameMap.put(arg, new ArrayList());
        if (!constantsInDomen.containsKey(arg) && !(arg instanceof DefConcept))
            constantsInDomen.put(arg, new ArrayList());
        if (!variableNameMap.containsKey(arg))
            variableNameMap.put(arg, new ArrayList());
        if (!variablesInDomen.containsKey(arg))
            variablesInDomen.put(arg, new ArrayList());
        if (arg instanceof DefConcept){
            AbstractSimpleFrame defFrame = ((DefConcept) arg).getDefFrame();
            if (defFrameConcept.containsKey(defFrame))
                defFrameConcept.get(defFrame).add((DefConcept) arg);
            else {
                ArrayList<DefConcept> defConcepts = new ArrayList<>();
                defConcepts.add((DefConcept) arg);
                defFrameConcept.put(((DefConcept) arg).getDefFrame(), defConcepts);
            }
        }
        succesAdding = true;
    }

    public static void addNewConcepts(ArrayList<Concept> newConcepts) {
        for (Concept conc : newConcepts) {
            addConceptToHierarchy(conc);
        }
    }

    public static void addNewConstants(HashMap<Concept, ArrayList<Constant>> newConstants) {
        for (Concept key : newConstants.keySet()) {
            Concept realKey = key;
            while (key instanceof DefConcept)
                key = ((DefConcept)key).getBaseConcept();
            if (constantsInDomen.containsKey(key)) {
                ArrayList<Constant> constantsOfThisConcept = constantsInDomen.get(key);
                ArrayList<String> constNamesOfThisConcept = constantNameMap.get(key);
                for (Constant cnst : newConstants.get(realKey)) {
                    constantsOfThisConcept.add(cnst);
                    constNamesOfThisConcept.add(cnst.getName());
                }
            } else {
                ArrayList<Constant> constantsOfThisConcept = new ArrayList();
                ArrayList<String> constNamesOfThisConcept = new ArrayList();
                for (Constant cnst : newConstants.get(realKey)) {
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
        ArrayList<AbstractFrame> rtrnSet = new ArrayList<>();
        for (AbstractFrame i : frameNodeAccordance.keySet()) {
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
            HashSet<AbstractFrame> isaFrames = frameHierarchyRoot.getMinimisedIsaSetForFrame(fr);//getIsaSetForFrameWithoutTransitivity(getIsaSetForFrame(ch.value));
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
        if (fr instanceof AbstractSimpleFrame){
            defFrameConcept.remove(fr);
            String predicate = ((AbstractSimpleFrame) fr).getPredicate();
            if (getFrameWithThisPredicate(predicate) == null)
                predicateExtensionals.remove(predicate);
        }
    }
    
    public static boolean conceptWithThisPropertiesIsExist(ArrayList<String> arg){
        if (arg.isEmpty()) return false;
        for (Concept conc: conceptSet){
            if (conc.getCharacteristics().containsAll(arg) && arg.containsAll(conc.getCharacteristics()))
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
            if (prnt.value.getName().equals(concName))
                break;
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
        constantsInDomen.remove(conc);
        variablesInDomen.remove(conc);
        conceptNodeAccordance.remove(conc);
        nameConceptAccordance.remove(conc.getName());
        conceptNameSet.remove(conc.getName());
        Set<AbstractSimpleFrame> defFrames = defFrameConcept.keySet();
        if (conc instanceof DefConcept){
            for (AbstractSimpleFrame defFrame: defFrames){
                ArrayList<DefConcept> defConcepts = defFrameConcept.get(defFrame);
                defConcepts.remove((DefConcept) conc);
            }
        }
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
    public static boolean conceptIsUsed(Concept concept){
        if (!getNamesOfFramesThatUseConcept(concept).isEmpty())
            return true;
        for (Concept cncpt: getConcepts())
            if (cncpt instanceof DefConcept)
                if (((DefConcept) cncpt).getBaseConcept() == concept)
                    return true;
        return false;
    }
    public static HashSet<AbstractFrame> getFramesThatUseConcept(Concept concept){
        HashSet<AbstractFrame> frames = new HashSet();
        for (AbstractFrame fr: frameSet){
            if (fr.conceptIsUsed(concept))    
                frames.add(fr); 
        }
        return frames;
    }
    
    public static boolean constantIsUsedInFrame(Constant constant){
        for (AbstractFrame fr: frameSet){
            if (fr.constantIsUsed(constant))
                return true;
        }
        return false;
    }
    public static boolean constantIsUsedInExtension(Constant constant){
        for (Extensional ext: predicateExtensionals.values())
            if (ext.constantIsUsed(constant))
                return true;
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
    
    // для открытых фреймов
    private static Extensional getExtensionalOfFrameWithDefConcepts(AbstractSimpleFrame frame){
        AbstractSimpleFrame frameWithoutDefConcepts = frame.getBaseFrameWithoutDefConcepts();
        Extensional extOfBase = getExtensional(frameWithoutDefConcepts);
        Extensional rtrn = new Extensional(frame);
        HashMap<String, ArrayList<Constant>> constantsOfFrameDefConcepts = new HashMap<>();
        for (Slot slot: frame.getBody().getSlots())
            if (slot.getDomen() instanceof DefConcept)
                constantsOfFrameDefConcepts.put(slot.getRole(), ActualData.getConstantsOfDefConcept((DefConcept)slot.getDomen()));
        for (HashMap<String, Constant> extension: extOfBase.getExtensions()){
            boolean isOk = true;
            for (String role: extension.keySet()){
                if (constantsOfFrameDefConcepts.containsKey(role) && !constantsOfFrameDefConcepts.get(role).contains(extension.get(role))){
                    isOk = false;
                    break;
                }
            }
            if (isOk && !rtrn.contains(extension))
                rtrn.addExtension(extension);
        }
        return rtrn;
    }
    public static Extensional getExtensional(AbstractSimpleFrame frame){
        for (Slot slot: frame.getBody().getSlots()){
            if (slot.getDomen() instanceof DefConcept){
                return getExtensionalOfFrameWithDefConcepts(frame);
            }
        }
        gettableCheckResult = predicateExtensionals.get(frame.getPredicate()).getProjection(frame);
        ArrayList<Variable> quantifiedVariables = frame.getQuantifiedVariables();
        ArrayList<Quantor> newQuantors = (ArrayList<Quantor>) frame.getQuantors().clone();
        for (Slot slot: frame.getBody().getSlots())
            if (slot.getArgument() instanceof Variable)
                if (!quantifiedVariables.contains((Variable) slot.getArgument())){
                    Quantor newQuantor = new Quantor("[}",1,(Variable) slot.getArgument());
                    newQuantors.add(newQuantor);
                }
        AbstractSimpleFrame closedFrame = new AbstractSimpleFrame(frame.getName(),frame.getPredicate(), newQuantors, frame.getBody());
        boolean b = frameExtensionalIsGettable(closedFrame);
        if (b)
            return gettableCheckResult;
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
    private static Extensional gettableCheckResult;
    public static boolean frameExtensionalIsGettable(AbstractSimpleFrame frame){
        gettableCheckResult = new Extensional(frame);
        if (!frame.isClosed())
            return true;
        for (Quantor quantor: frame.getQuantors()){
            Variable var = quantor.getVariable();
            Concept domen = var.getDomen();
            int domenConstantsCount = getConstantInDomenCount(domen);
            switch (quantor.getType()){
                case "A":
                    break;
                case "[}":
                case "[]":
                    if (domenConstantsCount < quantor.getValue())
                        return false;
                    break;
                case "{]":
                    break;
            }
        }
        Extensional predicateExtensional = predicateExtensionals.get(frame.getPredicate());
        Extensional bodyProjectedExt = predicateExtensional.getProjection(frame.getBody());//
        if (frame.getQuantors().isEmpty()) {
            gettableCheckResult = bodyProjectedExt;
            return !bodyProjectedExt.isEmpty();
        }
        else 
            return extIsOk(bodyProjectedExt, frame.getQuantors(), frame.getBody().getSlots());
    }
    public static Extensional getLastExtensionalCheckResult() { 
        Extensional rtrn = gettableCheckResult;
        gettableCheckResult = new Extensional();
        return rtrn;
    }
    //вызывать после проекции
    private static boolean extIsOk(Extensional ext, ArrayList<Quantor> quantors, ArrayList<Slot> slots){
        if (quantors.isEmpty()){
            HashMap<String, Constant> roleConstant= new HashMap();
            for (Slot slot: slots)
                roleConstant.put(slot.getRole(), (Constant) slot.getArgument());
            if (ext.contains(roleConstant)){
                gettableCheckResult.addExtension(roleConstant);
                return true;
            }
            else 
                return false;
        }
        else {
            Quantor qntr = quantors.get(0);
            Concept conc = null;
            String role = null;
            for (Slot slot: slots)
                if (slot.getArgument() == qntr.getVariable()){
                    conc = slot.getDomen();
                    role = slot.getRole();
                    break;
                }
            if (conc==null)
                return false;
            ArrayList<Constant> allConstantsInDomen = (conc instanceof DefConcept) ?
                    ActualData.getConstantsOfDefConcept((DefConcept)conc):
                    getAllConstantsInDomen(conc);
            int count = 0;
            for (Constant constant: allConstantsInDomen){
                Extensional projectedExt = ext.getProjection(role, constant);
                ArrayList<Quantor> projectedQuantors = (ArrayList<Quantor>) quantors.clone();
                projectedQuantors.remove(0);
                ArrayList<Slot> projectedSlots = (ArrayList<Slot>) slots.clone();
                for (Slot slot: projectedSlots)
                    if (slot.getRole().equals(role)){
                        projectedSlots.remove(slot);
                        projectedSlots.add(new Slot(role, constant, conc));
                        break;
                    }
                if (extIsOk(projectedExt, projectedQuantors, projectedSlots))
                    count++;
                else {
                    gettableCheckResult = gettableCheckResult.minus(projectedSlots); 
                }
            }
            boolean rtrn = false;
            switch (qntr.getType()) {
                case "A":
                    rtrn = count == allConstantsInDomen.size();
                    break;
                case "[]":
                    rtrn = count == qntr.getValue();
                    break;
                case "[}":
                    rtrn = count >= qntr.getValue();
                    break;
                case "{]":
                    rtrn = count <= qntr.getValue();
                    break;
            }
            return rtrn;
        }
    }
    
    public static void addExtension(String predicate, HashMap<String, Constant> extension){
        predicateExtensionals.get(predicate).addExtension(extension); 
    }
    public static boolean frameExtensionalIsDeducible(AbstractSimpleFrame frame){
        for (Quantor quantor: frame.getQuantors()){
            Variable var = quantor.getVariable();
            Concept domen = var.getDomen();
            int domenConstantsCount = getConstantInDomenCount(domen);
            switch (quantor.getType()){
                case "A":
                    break;
                case "[}":
                case "[]":
                    if (domenConstantsCount != quantor.getValue())
                        return false;
                    break;
                case "{]":
                    if (domenConstantsCount!=0)
                        return false;
            }
        }
        return true;
    }
    public static Extensional deduceExtensional(AbstractSimpleFrame frame){
        Extensional ext = new Extensional(frame);
        ArrayList<Quantor> qntrs = frame.getQuantors();
        HashMap<String, Concept> roleConc = frame.getBody().getRoleConceptAccordance();
        HashMap<String, Constant> roleConst = new HashMap();
        for (Slot slot: frame.getBody().getSlots())
            if (!slot.getArgument().isVariable()){
                roleConc.remove(slot.getRole());
                roleConst.put(slot.getRole(), (Constant) slot.getArgument());
            }
        if (roleConc.isEmpty()){
            ext.addExtension(roleConst);
            return ext;
        }
        ArrayList<HashMap<String, Constant>> extensions = getCartesianProduct(roleConc);
        extensions = getCartesianProduct(roleConst, extensions);
        ext.addAll(extensions);
        return ext;
    }
    private static ArrayList<HashMap<String, Constant>> getCartesianProduct(HashMap<String, Concept> roleConc){
        ArrayList<HashMap<String, Constant>> rtrn = new ArrayList();
        Concept concept = null;
        String role = null;
        for (String rol: roleConc.keySet()){
            concept = roleConc.get(rol);
            role = rol;
            roleConc.remove(rol);
            break;
        }
        if (roleConc.isEmpty())
            for (Constant cnst: (concept instanceof DefConcept) ?
                    ActualData.getConstantsOfDefConcept((DefConcept) concept):
                    ActualData.getAllConstantsInDomen(concept)){
                HashMap<String, Constant> newExt = new HashMap();
                newExt.put(role, cnst);
                rtrn.add(newExt);
            }
        else {
            HashMap<String, Concept> newRoleConc = (HashMap<String, Concept>) roleConc.clone();
            newRoleConc.remove(role);
            ArrayList<HashMap<String, Constant>> nextProd = getCartesianProduct(newRoleConc);
            for (Constant cnst: ActualData.getAllConstantsInDomen(concept)){
                for (HashMap<String, Constant> ext: nextProd){
                    HashMap<String, Constant> newExt = (HashMap<String, Constant>) ext.clone();
                    newExt.put(role, cnst);
                    rtrn.add(newExt);
                }
            }
        }
        return rtrn;
    }
    private static ArrayList<HashMap<String, Constant>> getCartesianProduct(HashMap<String, Constant> roleConst,ArrayList<HashMap<String, Constant>> ext){
        for (String role: roleConst.keySet()){
            Constant constant = roleConst.get(role);
            for (HashMap<String, Constant> extension: ext)
                extension.put(role, constant);
        }
        return ext;
    }
    public static void removeExstension(String predicate, HashMap<String, Constant> extension){
        Extensional predExt = predicateExtensionals.get(predicate);
        predExt.removeExtension(extension);
    }
    
    public static boolean thisFrameAlreadyExist(AbstractFrame argFrame){
        for (AbstractFrame frame: frameSet){
            if (frame.equals(argFrame))
                return true;
        }
        return false;
    }
    //public boolean frameHas
    public static DefConcept getDefConcept(AbstractSimpleFrame frame, String role){ return new DefConcept(frame, role);}
    
    public static ArrayList<String> getNotUsedRoles(AbstractSimpleFrame frame){
        ArrayList<String> rtrn = frame.getRoles();
        ArrayList<DefConcept> defConc = getDefConcepts(frame);
        for (DefConcept dc: defConc)
            rtrn.remove(dc.getRole());
        for (Slot slot: frame.getBody().getSlots())
            if (slot.getArgument() instanceof Constant)
                rtrn.remove(slot.getRole());
        return rtrn;
    }

    public static void save(String absolutePath) throws ParserConfigurationException, SAXException, IOException {
        InputOutputXML.save(absolutePath); 
    }
    public static AbstractSimpleFrame getFrameWithThisPredicate(String predicate) {
        AbstractSimpleFrame rtrn = null;
        for (AbstractFrame fr: frameSet){
            if (fr instanceof AbstractSimpleFrame){
                if (((AbstractSimpleFrame) fr).getPredicate().equals(predicate))
                    return (AbstractSimpleFrame) fr;
            }
        }
        return rtrn;
    }
    public static void clear(){
        frameSet = new ArrayList();
        conceptSet = new ArrayList();
        conceptHierarchyRoot = new ConceptNode(new Concept("Concepts"));
        frameHierarchyRoot = new FrameNode(
            new AbstractSimpleFrame("Frames", "", new ArrayList(), new Body(new ArrayList())));
        frameNodeAccordance = new HashMap();
        conceptNodeAccordance = new HashMap();
        nameFrameAccordance = new HashMap();
        nameConceptAccordance = new HashMap();
        conceptNameSet = new ArrayList();
        frameNameSet = new ArrayList();
        constantNameMap = new HashMap();
        variableNameMap = new HashMap();
        constantsInDomen = new HashMap();
        variablesInDomen = new HashMap();
        predicateExtensionals = new HashMap();
        defFrameConcept = new HashMap<>();
    }

    public static void setPredicateExtensionals(HashMap<String, Extensional> arg) {
        predicateExtensionals = arg;
    }

    public static void clearExtensionals() {
        for (String predicate: predicateExtensionals.keySet()){
            predicateExtensionals.get(predicate).clear();
        }
    }
    public static void removeAllNotDefFrames(){
        ArrayList<AbstractFrame> framesToRemove = new ArrayList<>();
        for (AbstractFrame frame: frameSet)
            if (frame instanceof AbstractSimpleFrame)
                if (!defFrameConcept.containsKey(frame))
                    framesToRemove.add(frame);
        for (AbstractFrame frame: framesToRemove)
            removeFrameByName(frame.getName());
    }
    public static void removeAllNotUsedConcepts(){
        
    }
    public static boolean frameIsUsedInDefDemension(AbstractFrame frame){
        if(frame instanceof AbstractSimpleFrame)
            return defFrameConcept.containsKey(frame) && !defFrameConcept.get(frame).isEmpty();
        else return false;
    }
    public boolean conceptWithThisPropertiesCreateMultipleInheritance(){
        return false;
    }
}
