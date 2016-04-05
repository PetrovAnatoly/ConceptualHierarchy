/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelInputLoad;

import Frames.AbstractFrame;
import Frames.AndFrame;
import Frames.CharacteristicFrame;
import Frames.EventFrame;
import Frames.NotFrame;
import Frames.OrFrame;
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
        ArrayList<ConDesLanTag> content = cdl.getComplexTagPropertyValue("содержимое");
        if (content == null)
            throw new IOException();
        ConDesLanTag conceptsTag = null, constantsTag = null, variablesTag = null, framesTag = null, extensionalsTag = null;
        for (ConDesLanTag tag: content){
            switch (tag.getName()) {
                case "concepts":
                    conceptsTag = tag;
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
        loadConstants(constantsTag);
        loadVariables(variablesTag);
        loadFrames(framesTag);
        loadDefConcepts(constantsTag);
        loadExtensionals(extensionalsTag);
    }
    private static void loadBasicConcepts(ConDesLanTag conceptsTag){
        ArrayList<ConDesLanTag> basicConceptTags = conceptsTag.getComplexTagPropertyValue("basicConcepts");
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
        
    }
    private static void loadVariables(ConDesLanTag variablesTag){
        
    }
    private static void loadFrames(ConDesLanTag framesTag){
        
    }
    private static void loadDefConcepts(ConDesLanTag conceptsTag){
        ArrayList<ConDesLanTag> defConceptTags = conceptsTag.getComplexTagProperties().get("defConcepts");
        
    }
    private static void loadExtensionals(ConDesLanTag extensionalsTag){
        
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
