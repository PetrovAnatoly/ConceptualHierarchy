/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelInputLoad;

import Frames.AbstractFrame;
import Frames.AbstractSimpleFrame;
import Frames.AndFrame;
import Frames.CharacteristicFrame;
import Frames.EventFrame;
import Frames.NotFrame;
import Frames.OrFrame;
import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import conceptualhierarchy.ActualData;
import conceptualhierarchy.Extensional;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;
import Сoncepts.Variable;
import java.util.Date;
/**
 *
 * @author Anatoly
 */
public class InputOutputConDesLan {
    public static void save(String absolutePath) throws IOException{
        FileWriter fw = new FileWriter(absolutePath, false);
        ConDesLanTag root = new ConDesLanTag("data");
        String username = System.getProperty("user.name");
        root.addSimpleProperty("Autor", username);
        Date date = new Date(System.currentTimeMillis());
        date = new Date(date.getTime());
        //сделать нормальную дату!!! (в нулевой или текущей временной зоне)
        root.addSimpleProperty("Creation_time", date.toString());
        ConDesLanTag framesTag = getFramesTag(ActualData.getFrames());
        root.addComplexTagProperty("содержимое", framesTag);
        ConDesLanTag conceptsTag = getConceptsTag(ActualData.getConcepts());
        root.addComplexTagProperty("содержимое", conceptsTag);
        ConDesLanTag constantsTag = getConstantsTag(ActualData.getConstants());
        root.addComplexTagProperty("содержимое", constantsTag);
        ConDesLanTag variablesTag = getVariablesTag(ActualData.getVariables());
        root.addComplexTagProperty("содержимое", variablesTag);
        ConDesLanTag extensionalsTag = getExtensionalsTag(ActualData.getAllExtensionals());
        root.addComplexTagProperty("содержимое", extensionalsTag);
        fw.write(root.getConDesLanStructure());
        fw.flush();
    }
    public static void load(String absolutePath) throws IOException{
        FileReader fr = new FileReader(absolutePath);
        File file = new File(absolutePath);
        char[] buffer = new char[(int)file.length()];
        fr.read(buffer);
        String fileContent = new String(buffer);
        ConDesLanTag cdl = ConDesLanTag.parseString(fileContent);
        System.out.println("file is parsed, parse result:\n");
        System.out.println(cdl.getConDesLanStructure());
        ArrayList<ConDesLanTag> content = cdl.getComplexTagPropertyValue("содержимое");
        if (content == null)
            throw new IOException();
        ConDesLanTag conceptsTag = null, constantsTag = null, variablesTag = null, framesTag = null, extensionalsTag = null;
        ArrayList<ConDesLanTag> defConceptsTag = new ArrayList<>();
        for (ConDesLanTag tag: content){
            switch (tag.getName()) {
                case "concepts":
                    conceptsTag = tag;
                    defConceptsTag = conceptsTag.getComplexTagPropertyValue("defConcepts");
                    break;
                case "constants":
                    constantsTag = tag;
                    break;
                case "variables":
                    variablesTag = tag;
                    break;
                case "frames":
                    framesTag = tag;
                    break;
                case "extensionals":
                    extensionalsTag = tag;
                    break;
            }
        }
        if (conceptsTag == null || constantsTag == null || variablesTag == null || framesTag == null || extensionalsTag == null)
            throw new IOException();
        ActualData.clear();
        loadBasicConcepts(conceptsTag);
        System.out.println("basic concepts is loaded");
        loadConstants(constantsTag);
        System.out.println("constants is loaded");
        loadVariables(variablesTag);
        System.out.println("variables is loaded");
        loadFrames(framesTag, conceptsTag, constantsTag, variablesTag);
        System.out.println("all frames is loaded");
        //loadDefConcepts(conceptsTag);
        System.out.println("def-concepts is loaded");
        loadExtensionals(extensionalsTag);
        System.out.println("extensionals is loaded");
    }
    private static void loadBasicConcepts(ConDesLanTag conceptsTag){
        ArrayList<ConDesLanTag> basicConceptTags = conceptsTag.getComplexTagPropertyValue("basicConcepts");
        if (basicConceptTags == null)
            return;
        String name, comment;
        ArrayList<String> properties;
        for (ConDesLanTag conceptTag: basicConceptTags){
            if (!conceptTag.getName().equals("концепт"))
                continue;
            name = conceptTag.getSimplePropertyValue("имя");
            comment = conceptTag.getSimplePropertyValue("комментарий");
            if (conceptTag.getComplexStringProperties().containsKey("свойства"))
                properties = conceptTag.getComplexStringPropertyValue("свойства");
            else 
                properties = new ArrayList<>();
            Concept conc = new Concept(name, comment, properties);
            ActualData.addConceptToHierarchy(conc);
        }
    }
    private static void loadConstants(ConDesLanTag constantsTag){
        HashMap<Concept, ArrayList<Constant>> newConstants = new HashMap<>();
        for (String conceptName: constantsTag.getComplexStringProperties().keySet()){
            Concept domen = ActualData.getConceptByName(conceptName);
            if (domen == null)
                continue;
            ArrayList<Constant> constants = new ArrayList<>();
            newConstants.put(domen, constants);
            for (String constantName: constantsTag.getComplexStringPropertyValue(conceptName)){
                constants.add(new Constant(constantName, domen));
            }
        }
        ActualData.addNewConstants(newConstants);
    }
    private static void loadVariables(ConDesLanTag variablesTag){
        HashMap<Concept, ArrayList<Variable>> newVariables = new HashMap<>();
        for (String conceptName: variablesTag.getComplexStringProperties().keySet()){
            Concept domen = ActualData.getConceptByName(conceptName);
            if (domen == null)
                continue;
            ArrayList<Variable> variables = new ArrayList<>();
            newVariables.put(domen, variables);
            for (String constantName: variablesTag.getComplexStringPropertyValue(conceptName)){
                variables.add(new Variable(constantName, domen));
            }
        }
        ActualData.addNewVariables(newVariables);
    }
    private static void loadFrames(ConDesLanTag framesTag, ConDesLanTag conceptsTag, ConDesLanTag constantsTag, ConDesLanTag variablesTag){
        ArrayList<ConDesLanTag> notAddedFrames = new ArrayList<>();
        ArrayList<ConDesLanTag> defConceptTags = conceptsTag.getComplexTagPropertyValue("defConcepts");
        ArrayList<ConDesLanTag> simpleFrameTags = new ArrayList<>();
        if (framesTag.getComplexTagProperties().containsKey("eventFrames"))
            simpleFrameTags.addAll(framesTag.getComplexTagPropertyValue("eventFrames"));
        if (framesTag.getComplexTagProperties().containsKey("characteristicFrames"))
            simpleFrameTags.addAll(framesTag.getComplexTagPropertyValue("characteristicFrames"));
        if (framesTag.getComplexTagProperties().containsKey("notFrames"))
            notAddedFrames.addAll(framesTag.getComplexTagPropertyValue("notFrames"));
        if (framesTag.getComplexTagProperties().containsKey("orFrames"))
            notAddedFrames.addAll(framesTag.getComplexTagPropertyValue("orFrames"));
        if (framesTag.getComplexTagProperties().containsKey("andFrames"))
            notAddedFrames.addAll(framesTag.getComplexTagPropertyValue("andFrames"));
        while (!simpleFrameTags.isEmpty()){
            for (ConDesLanTag simpleFrameTag: simpleFrameTags){
                AbstractSimpleFrame fr;
                boolean frameIsAdded = true;
                String name = simpleFrameTag.getSimplePropertyValue("имя");
                String predicate = simpleFrameTag.getSimplePropertyValue("предикат");
                String quantors = simpleFrameTag.getSimplePropertyValue("кванторы");
                Body frameBody = new Body();
                for (ConDesLanTag slotTag: simpleFrameTag.getComplexTagPropertyValue("слоты")){
                    Slot slot = new Slot();
                    boolean argIsVar = false;
                    String argName = slotTag.getSimplePropertyValue("аргумент");
                    if (!((argName.charAt(0) == '\'') && (argName.charAt(argName.length()-1) == '\'')))
                        argIsVar = true;
                    String domenName = slotTag.getSimplePropertyValue("домен");
                    Concept domen = ActualData.getConceptByName(domenName);
                    if (domen != null)
                        slot.setDomen(domen);
                    else {
                        frameIsAdded = false;
                        break;
                    }
                    String role = slotTag.getSimplePropertyValue("роль");
                    slot.setRole(role);
                    if (argIsVar){
                        if (ActualData.getVariableInDomenByName(argName, domen) != null)
                            slot.setArgument(ActualData.getVariableInDomenByName(argName, domen));
                        else{
                            ActualData.addNewVariableNameInDomen(argName, domen);
                            slot.setArgument(ActualData.getVariableInDomenByName(argName, domen));
                        }
                    }
                    else {
                        if (ActualData.getConstantInDomenByName(argName, domen) != null)
                            slot.setArgument(ActualData.getConstantInDomenByName(argName, domen));
                        else {
                            ActualData.addNewConstantInDomen(argName, domen);
                            slot.setArgument(ActualData.getConstantInDomenByName(argName, domen));
                        }
                    }
                    frameBody.addSlot(slot);
                }
                if (!frameIsAdded)
                    continue;
                ArrayList<Quantor> qntrs = Quantor.getQuantorArray(quantors, frameBody.getAllVariablesInBody());
                if (simpleFrameTag.getSimplePropertyValue("тип").equals("характеристика"))
                    fr = new CharacteristicFrame(name, predicate, qntrs, frameBody);
                else 
                    fr = new EventFrame(name, predicate, qntrs, frameBody);
                ActualData.addFrameToHierarchy(fr);
                System.out.println("frame + " + fr.getName() + " is added...");
                simpleFrameTags.remove(simpleFrameTag);
                if (defConceptTags == null)
                    break;
                for (ConDesLanTag defConceptTag: defConceptTags){
                    if (defConceptTag.getSimplePropertyValue("def-фрейм").equals(fr.getName())){
                        String defConceptName = defConceptTag.getSimplePropertyValue("имя");
                        String comment = defConceptTag.getSimplePropertyValue("комментарий");
                        String role = defConceptTag.getSimplePropertyValue("роль");
                        String defFrName = defConceptTag.getSimplePropertyValue("def-фрейм");
                        AbstractSimpleFrame defFrame = (AbstractSimpleFrame) ActualData.getFrameByName(defFrName);
                        ArrayList<String> properties = new ArrayList<>();
                        if (defConceptTag.getComplexStringProperties().containsKey("свойства"))
                            properties.addAll(defConceptTag.getComplexStringPropertyValue("свойства"));
                        DefConcept conc = new DefConcept(defFrame, role);
                        conc.setName(defConceptName);
                        conc.setComment(comment);
                        conc.setProperties(properties);
                        ActualData.addConceptToHierarchy(conc);
                        System.out.println("\tdefConcept" + defConceptName + " is added");
                        HashMap<Concept,ArrayList<Constant>> newConstants = new HashMap<>();
                        newConstants.put(conc, new ArrayList<Constant>());
                        HashMap<Concept,ArrayList<Variable>> newVariables = new HashMap<>();
                        newVariables.put(conc, new ArrayList<Variable>());
                        if (constantsTag.getComplexStringPropertyValue(defConceptName) != null)
                            for (String defConceptConstantName: constantsTag.getComplexStringPropertyValue(defConceptName))
                                newConstants.get(conc).add(new Constant(defConceptConstantName, conc));
                        ActualData.addNewConstants(newConstants);
                        System.out.println("\t\t"+ newConstants.get(conc) + "added");
                        if (variablesTag.getComplexStringPropertyValue(defConceptName) != null)
                            for (String defConceptVariableName: variablesTag.getComplexStringPropertyValue(defConceptName))
                                newVariables.get(conc).add(new Variable(defConceptVariableName, conc));
                        ActualData.addNewVariables(newVariables);
                        System.out.println("\t\t"+ newVariables.get(conc) + "added");
                    }
                }
                break;
            }
        }
        System.out.println("simple frames is loaded");
        while (!notAddedFrames.isEmpty()){
            for (ConDesLanTag frameTag: notAddedFrames){
                if (frameTag.getSimplePropertyValue("тип").equals("NOT")){
                    String operandName = frameTag.getSimplePropertyValue("аргумент");
                    if (!ActualData.avalibleFrameName(operandName)){
                        String notFrameName = frameTag.getSimplePropertyValue("имя");
                        NotFrame newFrame = new NotFrame(notFrameName, ActualData.getFrameByName(operandName));
                        ActualData.addFrameToHierarchy(newFrame);
                        notAddedFrames.remove(frameTag);
                        break;
                    }
                }
                else if (frameTag.getSimplePropertyValue("тип").equals("AND")){
                    System.out.println("AND frame checking");
                    String firstOperandName = frameTag.getSimplePropertyValue("аргумент1");
                    String secondOperandName = frameTag.getSimplePropertyValue("аргумент2");
                    if (!ActualData.avalibleFrameName(firstOperandName) && !ActualData.avalibleFrameName(secondOperandName)){
                        String andFrameName = frameTag.getSimplePropertyValue("имя");
                        AbstractFrame frst = ActualData.getFrameByName(firstOperandName);
                        AbstractFrame scnd = ActualData.getFrameByName(secondOperandName);
                        AndFrame newFrame = new AndFrame(andFrameName, frst, scnd);
                        ActualData.addFrameToHierarchy(newFrame);
                        notAddedFrames.remove(frameTag);
                        break;
                    }
                }
                else if (frameTag.getSimplePropertyValue("тип").equals("OR")){
                    String firstOperandName = frameTag.getSimplePropertyValue("аргумент1");
                    String secondOperandName = frameTag.getSimplePropertyValue("аргумент2");
                    if (!ActualData.avalibleFrameName(firstOperandName) && !ActualData.avalibleFrameName(secondOperandName)){
                        String andFrameName = frameTag.getSimplePropertyValue("имя");
                        AbstractFrame frst = ActualData.getFrameByName(firstOperandName);
                        AbstractFrame scnd = ActualData.getFrameByName(secondOperandName);
                        OrFrame newFrame = new OrFrame(andFrameName, frst, scnd);
                        ActualData.addFrameToHierarchy(newFrame);
                        notAddedFrames.remove(frameTag);
                        break;
                    }
                }
            }
        }
    }
    private static void loadDefConcepts(ConDesLanTag conceptsTag){
        ArrayList<ConDesLanTag> defConceptTags = conceptsTag.getComplexTagProperties().get("defConcepts");
        if (defConceptTags == null)
            return;
        for (ConDesLanTag defConceptTag: defConceptTags){
            String name = defConceptTag.getSimplePropertyValue("имя");
            String comment = defConceptTag.getSimplePropertyValue("комментарий");
            String role = defConceptTag.getSimplePropertyValue("роль");
            String defFrName = defConceptTag.getSimplePropertyValue("def-фрейм");
            AbstractSimpleFrame defFrame = (AbstractSimpleFrame) ActualData.getFrameByName(defFrName);
            ArrayList<String> properties = new ArrayList<>();
            if (defConceptTag.getComplexStringProperties().containsKey("свойства"))
                properties.addAll(defConceptTag.getComplexStringPropertyValue("свойства"));
            DefConcept conc = new DefConcept(defFrame, role);
            conc.setName(name);
            conc.setComment(comment);
            conc.setProperties(properties);
            ActualData.addConceptToHierarchy(conc);
        }
    }
    private static void loadExtensionals(ConDesLanTag extensionalsTag){
        HashMap<String, Extensional> predicateExtensionals = new HashMap<>();
        for (String predicate: extensionalsTag.getComplexTagProperties().keySet()){
            ConDesLanTag predicateExtensionalTag = extensionalsTag.getComplexTagPropertyValue(predicate).get(0);
            Extensional predicateExtensional;
            if (ActualData.getFrameWithThisPredicate(predicate) != null)
                predicateExtensional = new Extensional(ActualData.getFrameWithThisPredicate(predicate));
            else{
                predicateExtensional = new Extensional();
                predicateExtensional.setPredicate(predicate);
            }
            HashMap<String, Concept> roleConc = new HashMap<>();
            if (predicateExtensionalTag.getComplexTagPropertyValue("role-concept-accordance") == null || predicateExtensionalTag.getComplexTagPropertyValue("role-concept-accordance").isEmpty())
                continue;
            for (ConDesLanTag roleConceptAccordanceTag: predicateExtensionalTag.getComplexTagPropertyValue("role-concept-accordance")){
                String role = roleConceptAccordanceTag.getSimplePropertyValue("role");
                String concName = roleConceptAccordanceTag.getSimplePropertyValue("concept");
                Concept concept = ActualData.getConceptByName(concName);
                if (concept == null)
                    continue;
                if (!predicateExtensional.getRoles().contains(role))
                    predicateExtensional.getRoles().add(role);
                if (!predicateExtensional.getDomens().contains(concept))
                    predicateExtensional.getDomens().add(concept);
                roleConc.put(role, concept);
            }
            if (roleConc.isEmpty())
                return;
            predicateExtensional.setRoleConceptAccordance(roleConc);
            predicateExtensionals.put(predicate, predicateExtensional);
            if (predicateExtensionalTag.getComplexTagPropertyValue("extensions") == null)
                continue;
            for (ConDesLanTag ExtensionTag: predicateExtensionalTag.getComplexTagPropertyValue("extensions")){
                HashMap<String,Constant> extension = new HashMap<>();
                for (ConDesLanTag roleConstantTag: ExtensionTag.getComplexTagPropertyValue("role-constants")){
                    String role = roleConstantTag.getSimplePropertyValue("role");
                    if (!predicateExtensional.getRoles().contains(role))
                        continue;
                    String constName = roleConstantTag.getSimplePropertyValue("constant");
                    Constant constant = ActualData.getConstantInDomenByName(constName, roleConc.get(role));
                    if (constant == null)
                        continue;
                    extension.put(role, constant);
                }
                if (!extension.isEmpty())
                    predicateExtensional.addExtension(extension);
            }
        }
        ActualData.setPredicateExtensionals(predicateExtensionals);
    }
    
    private static ConDesLanTag getFramesTag(ArrayList<AbstractFrame> frames){
        ConDesLanTag framesTag = new ConDesLanTag("frames");
        for (AbstractFrame frame: frames){
            ConDesLanTag frameTag = frame.toConDesLanTag();
            if (frame instanceof EventFrame){
                framesTag.addComplexTagProperty("eventFrames", frameTag);
            }
            else if (frame instanceof CharacteristicFrame){
                framesTag.addComplexTagProperty("characteristicFrames", frameTag);
            }
            else if (frame instanceof NotFrame){
                framesTag.addComplexTagProperty("notFrames", frameTag);
            }
            else if (frame instanceof AndFrame){
                framesTag.addComplexTagProperty("andFrames", frameTag);
            }
            else if (frame instanceof OrFrame){
                framesTag.addComplexTagProperty("orFrames", frameTag);
            }
        }
        return framesTag;
    }
    private static ConDesLanTag getConceptsTag(ArrayList<Concept> concepts) throws IOException {
        ConDesLanTag rtrn = new ConDesLanTag("concepts");
        for (Concept conc: concepts){
            if (conc instanceof DefConcept){
                rtrn.addComplexTagProperty("defConcepts", conc.toConDesLanTag());
            }
            else {
                rtrn.addComplexTagProperty("basicConcepts", conc.toConDesLanTag());
            }
        }
        return rtrn;
    }

    private static ConDesLanTag getConstantsTag(HashMap<Concept, ArrayList<Constant>> constants) {
        ConDesLanTag rtrn = new ConDesLanTag("constants");
        for (Concept conc: constants.keySet()){
            ArrayList<Constant> constOfConc = constants.get(conc);
            for (Constant cnstnt: constOfConc)
                rtrn.addComplexStringProperty(conc.getName(), cnstnt.getName());
        }
        return rtrn;
    }

    private static ConDesLanTag getVariablesTag(HashMap<Concept, ArrayList<Variable>> variables) {
        ConDesLanTag rtrn = new ConDesLanTag("variables");
        for (Concept conc: variables.keySet()){
            ArrayList<Variable> varsOfConc = variables.get(conc);
            for (Variable var: varsOfConc)
                rtrn.addComplexStringProperty(conc.getName(), var.getName());
        }
        return rtrn;
    }

    private static ConDesLanTag getExtensionalsTag(HashMap<String, Extensional> extensionals) {
        ConDesLanTag rtrn = new ConDesLanTag("extensionals");
        for (String predicate: extensionals.keySet())
            rtrn.addComplexTagProperty(predicate, extensionals.get(predicate).toConDesLanTag());
        return rtrn;
    }
}
