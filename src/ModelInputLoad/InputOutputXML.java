/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelInputLoad;
import Frames.AbstractBinaryFrame;
import Frames.AbstractComplexFrame;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.DefConcept;
import Сoncepts.Variable;
/**
 *
 * @author Anatoly
 */
public class InputOutputXML {

    public static void save(String absolutePath) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        document.appendChild(document.createElement("data"));
        addFrames(document, ActualData.getFrames());
        addConcepts(document,ActualData.getConcepts());
        addConstants(document, ActualData.getConstants());
        addVariables(document, ActualData.getVariables());
        addExtensionals(document, ActualData.getAllExtensionals());
        writeDocument(document, absolutePath);
    }
    public static void load(String absolutePath) throws SAXException, IOException{
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(absolutePath);
            Node root = document.getDocumentElement();
            Node conceptNode = null;
            Node variableNode = null;
            Node constantNode = null;
            Node frameNode = null;
            Node extensionalsNode = null;
            for (int i = 0; i < root.getChildNodes().getLength(); i++){
                String nodeName = root.getChildNodes().item(i).getNodeName();
                switch (nodeName) {
                    case "frames":
                        frameNode = root.getChildNodes().item(i);
                        break;
                    case "concepts":
                        conceptNode = root.getChildNodes().item(i);
                        break;
                    case "constants":
                        constantNode = root.getChildNodes().item(i);
                        break;
                    case "variables":
                        variableNode = root.getChildNodes().item(i);
                        break;
                    case "extensionals":
                        extensionalsNode = root.getChildNodes().item(i);
                        break;
                }
            }
            ActualData.clear();
            loadConcepts(conceptNode);
            loadConstants(constantNode);
            loadVariables(variableNode);
            loadFrames(frameNode);
            loadDefConcepts(conceptNode);
            loadExtensionals(extensionalsNode);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InputOutputXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void addFrames(Document document, ArrayList<AbstractFrame> frames){
        Node root = document.getDocumentElement();
        Element framesElement = document.createElement("frames");
        root.appendChild(framesElement);
        for (AbstractFrame frame: frames){
            Element frameElement = document.createElement("Frame");
            framesElement.appendChild(frameElement);
            frameElement.setAttribute("name", frame.getName());
            if (frame instanceof AbstractSimpleFrame){
                frameElement.setAttribute("type", "simple");
                frameElement.setAttribute("quantors", ((AbstractSimpleFrame)frame).getQuantorsLine());
                frameElement.setAttribute("predicate", ((AbstractSimpleFrame)frame).getPredicate());
                Element body = document.createElement("Body");
                frameElement.appendChild(body);
                for (Slot slot: ((AbstractSimpleFrame)frame).getBody().getSlots()){
                    Element slotElem = document.createElement("Slot");
                    slotElem.setAttribute("role", slot.getRole());
                    slotElem.setAttribute("argument", slot.getArgument().getName());
                    slotElem.setAttribute("domen", slot.getDomen().getName());
                    body.appendChild(slotElem);
                }
            }
            else if (frame instanceof NotFrame){
                frameElement.setAttribute("type", "notFrame");
                frameElement.setAttribute("operand", ((NotFrame)frame).getOperand().getName());
            }
            else if (frame instanceof AbstractBinaryFrame){
                frameElement.setAttribute("firstOperand", ((AbstractBinaryFrame)frame).getFirstOperand().getName());
                frameElement.setAttribute("secondOperand", ((AbstractBinaryFrame)frame).getSecondOperand().getName());
                frameElement.setAttribute("type", ((AbstractBinaryFrame)frame).getOperation());
            }
        }
    }
    public static void addConcepts(Document document, ArrayList<Concept> conceptsArr){
        Node root = document.getDocumentElement();
        Element concepts = document.createElement("concepts");
        root.appendChild(concepts);
        for (Concept concept: conceptsArr){
            if (!(concept instanceof DefConcept)){
                Element conceptElem = document.createElement("Concept");
                conceptElem.setAttribute("name", concept.getName());
                conceptElem.setAttribute("comment", concept.getComment());
                Element props = document.createElement("Properties");
                for (String property: concept.getProperties()){
                    Element propElem = document.createElement("Property");
                    propElem.setAttribute("val", property);
                    props.appendChild(propElem);
                }
                conceptElem.appendChild(props);
                concepts.appendChild(conceptElem);
            }
            else if (concept instanceof DefConcept){
                Element defConceptElem = document.createElement("DefConcept");
                defConceptElem.setAttribute("name", concept.getName());
                defConceptElem.setAttribute("comment", concept.getComment());
                defConceptElem.setAttribute("defFrame", ((DefConcept)concept).getDefFrame().getName());
                defConceptElem.setAttribute("role", ((DefConcept)concept).getRole());
                Element props = document.createElement("Properties");
                for (String property: concept.getProperties()){
                    Element propElem = document.createElement("Property");
                    propElem.setAttribute("val", property);
                    props.appendChild(propElem);
                }
                defConceptElem.appendChild(props);
                concepts.appendChild(defConceptElem);
            }
        }
    }
    public static void addConstants(Document document, HashMap<Concept, ArrayList<Constant>> concepts){
        Node root = document.getDocumentElement();
        Node constants = document.createElement("constants");
        root.appendChild(constants);
        for (Concept concept: concepts.keySet()){
            if (concept == null)
                continue;
            Element concElem = document.createElement("Domen");
            concElem.setAttribute("name",concept.getName());
            if (!concepts.get(concept).isEmpty()){
                for (Constant cnst: concepts.get(concept)){
                    Element constElem = document.createElement("Constant");
                    constElem.setAttribute("name",cnst.getName());
                    concElem.appendChild(constElem);
                }
            }
            constants.appendChild(concElem);
        }
    }
    public static void addVariables(Document document, HashMap<Concept, ArrayList<Variable>> variables){
        Node root = document.getDocumentElement();
        Node vars = document.createElement("variables");
        root.appendChild(vars);
        for (Concept concept: variables.keySet()){
            if (concept == null)
                continue;
            Element concElem = document.createElement("Domen");
            concElem.setAttribute("name",concept.getName());
            if (!variables.get(concept).isEmpty()){
                for (Variable var: variables.get(concept)){
                    Element varElem = document.createElement("Variable");
                    varElem.setAttribute("name",var.getName());
                    concElem.appendChild(varElem);
                }
            }
            vars.appendChild(concElem);
        }
    }
    public static void addExtensionals(Document document, HashMap<String, Extensional> ext){
        Node root = document.getDocumentElement();
        Node extensionals = document.createElement("extensionals");
        root.appendChild(extensionals);
        for (String predicate: ext.keySet()){
            Element predElem = document.createElement("predicate");
            extensionals.appendChild(predElem);
            predElem.setAttribute("val", predicate);
            Extensional extens = ext.get(predicate);
            HashMap<String, Concept> roleConceptAcc = extens.getRoleConceptAccordance();
            Element roleConcAccElem = document.createElement("role-concept-accordance");
            predElem.appendChild(roleConcAccElem);
            for (String role: roleConceptAcc.keySet()){
                Element roleConcElem = document.createElement("role-concept");
                roleConcElem.setAttribute("role", role);
                roleConcElem.setAttribute("concept", roleConceptAcc.get(role).getName());
                roleConcAccElem.appendChild(roleConcElem);
            }
            for (HashMap<String, Constant> extension: extens.getExtensions()){
                Element extElem = document.createElement("extension");
                predElem.appendChild(extElem);
                for (String role: extens.getRoles()){
                    Element extensionAtom = document.createElement("roleConstantNode"); 
                    extElem.appendChild(extensionAtom);
                    extensionAtom.setAttribute("role", role);
                    extensionAtom.setAttribute("constant", extension.get(role).getName());
                }
            }
        }
    }
    public static void loadConcepts(Node conceptsNode){
        if (conceptsNode == null)
            return;
        for (int i = 0; i < conceptsNode.getChildNodes().getLength(); i++){
            Node concept = conceptsNode.getChildNodes().item(i);
            if (concept.getNodeName().equals("Concept")){
                String name =  concept.getAttributes().getNamedItem("name").getNodeValue();
                String comment = concept.getAttributes().getNamedItem("comment").getNodeValue();
                ArrayList<String> properties = new ArrayList<>();
                Node propertiesNode = concept.getChildNodes().item(0);
                for (int l = 0; l < propertiesNode.getChildNodes().getLength();l++){
                    String prop = propertiesNode.getChildNodes().item(l).getAttributes().getNamedItem("val").getNodeValue();
                    properties.add(prop);
                }
                Concept conc = new Concept(name, comment, properties);
                ActualData.addConceptToHierarchy(conc);
            }
        }
    }
    public static void loadExtensionals(Node ExtensionalsNode){
        if (ExtensionalsNode == null)
            return;
        HashMap<String, Extensional> predicateExtensionals = new HashMap<>();
        for (int i = 0; i < ExtensionalsNode.getChildNodes().getLength(); i++){
            Node predNode = ExtensionalsNode.getChildNodes().item(i);
            String predicate = predNode.getAttributes().getNamedItem("val").getNodeValue();
            Extensional ext;
            if (ActualData.getFrameWithThisPredicate(predicate) != null)
                ext = new Extensional(ActualData.getFrameWithThisPredicate(predicate));
            else{
                ext = new Extensional();
                ext.setPredicate(predicate);
            }
            predicateExtensionals.put(predicate, ext);
            Node roleConcNodeRoot = predNode.getChildNodes().item(0);
            HashMap<String, Concept> roleConc = new HashMap<>();
            for (int k = 0; k < roleConcNodeRoot.getChildNodes().getLength(); k++){
                Node roleConceptNode = roleConcNodeRoot.getChildNodes().item(k);
                String role = roleConceptNode.getAttributes().getNamedItem("role").getNodeValue();
                String concName = roleConceptNode.getAttributes().getNamedItem("concept").getNodeValue();
                Concept concept = ActualData.getConceptByName(concName);
                if (!ext.getRoles().contains(role))
                    ext.getRoles().add(role);
                if (!ext.getDomens().contains(concept))
                    ext.getDomens().add(concept);
                roleConc.put(role, concept);
            }
            ext.setRoleConceptAccordance(roleConc);
            for (int k = 1; k < predNode.getChildNodes().getLength(); k++){
                Node extensionNode = predNode.getChildNodes().item(k);
                HashMap<String,Constant> extension = new HashMap<>();
                for (int j = 0; j < extensionNode.getChildNodes().getLength(); j++){
                    Node atomNode = extensionNode.getChildNodes().item(j);
                    String role = atomNode.getAttributes().getNamedItem("role").getNodeValue();
                    String constName = atomNode.getAttributes().getNamedItem("constant").getNodeValue();
                    Constant constant = ActualData.getConstantInDomenByName(constName, roleConc.get(role));
                    extension.put(role, constant);
                }
                ext.addExtension(extension);
            }
        }
        ActualData.setPredicateExtensionals(predicateExtensionals);
    }
    private static void loadDefConcepts(Node conceptsNode){
        if (conceptsNode == null)
            return;
        for (int i = 0; i < conceptsNode.getChildNodes().getLength(); i++){
            Node concept = conceptsNode.getChildNodes().item(i);
            if (concept.getNodeName().equals("DefConcept")){
                String name =concept.getAttributes().getNamedItem("name").getNodeValue();
                String comment = concept.getAttributes().getNamedItem("comment").getNodeValue();
                String role = concept.getAttributes().getNamedItem("role").getNodeValue();
                String defFr = concept.getAttributes().getNamedItem("defFrame").getNodeValue();
                AbstractSimpleFrame defFrame = (AbstractSimpleFrame) ActualData.getFrameByName(defFr);
                ArrayList<String> properties = new ArrayList<>();
                Node propertiesNode = concept.getChildNodes().item(0);
                for (int l = 0; l < propertiesNode.getChildNodes().getLength();l++){
                    String prop = propertiesNode.getChildNodes().item(l).getAttributes().getNamedItem("val").getNodeValue();
                    properties.add(prop);
                }
                DefConcept conc = new DefConcept(defFrame, role);
                conc.setName(name);
                conc.setComment(comment);
                conc.setProperties(properties);
                ActualData.addConceptToHierarchy(conc);
            }
        }
    }
    private static void loadVariables(Node varNode){
        if (varNode == null)
            return;
        HashMap<Concept, ArrayList<Variable>> newVariables = new HashMap<>();
        for (int i = 0; i < varNode.getChildNodes().getLength(); i++){
            Node concept = varNode.getChildNodes().item(i);
            Concept domen = ActualData.getConceptByName(concept.getAttributes().getNamedItem("name").getNodeValue());
            ArrayList<Variable> vars = new ArrayList<>();
            for (int k = 0; k < concept.getChildNodes().getLength(); k++){
                Node variableNode = concept.getChildNodes().item(k);
                vars.add(new Variable(variableNode.getAttributes().getNamedItem("name").getNodeValue(), domen));
            }
            newVariables.put(domen, vars);
        }
        ActualData.addNewVariables(newVariables);
    }
    private static void loadConstants(Node constNode){
        if (constNode == null)
            return;
        HashMap<Concept, ArrayList<Constant>> newConstants = new HashMap<>();
        for (int i = 0; i < constNode.getChildNodes().getLength(); i++){
            Node concept = constNode.getChildNodes().item(i);
            Concept domen = ActualData.getConceptByName(concept.getAttributes().getNamedItem("name").getNodeValue());
            ArrayList<Constant> constants = new ArrayList<>();
            newConstants.put(domen, constants);
            for (int k = 0; k < concept.getChildNodes().getLength(); k++){
                Node constantNode = concept.getChildNodes().item(k);
                constants.add(new Constant(constantNode.getAttributes().getNamedItem("name").getNodeValue(), domen));
            }
        }
        ActualData.addNewConstants(newConstants);
    }
    private static void loadFrames(Node frNode){
        if (frNode == null)
            return;
        ArrayList<Node> notAddedFrames = new ArrayList<>();
        for (int i = 0; i < frNode.getChildNodes().getLength(); i++){
            Node frame = frNode.getChildNodes().item(i);
            if (frame.getAttributes().getNamedItem("type").getNodeValue().equals("simple")){
                AbstractSimpleFrame fr;
                String name = frame.getAttributes().getNamedItem("name").getNodeValue();
                String predicate = frame.getAttributes().getNamedItem("predicate").getNodeValue();
                String quantors = frame.getAttributes().getNamedItem("quantors").getNodeValue();
                Body frameBody = new Body();
                Node body = frame.getFirstChild();
                for (int k = 0; k < body.getChildNodes().getLength(); k++){
                    Node slotNode = body.getChildNodes().item(k);
                    Slot slot = new Slot();
                    boolean argIsVar = false;
                    String argName = slotNode.getAttributes().getNamedItem("argument").getNodeValue();
                    if (!((argName.charAt(0) == '\'') && (argName.charAt(argName.length()-1) == '\'')))
                        argIsVar = true;
                    String domenName = slotNode.getAttributes().getNamedItem("domen").getNodeValue();
                    Concept domen = ActualData.getConceptByName(domenName);
                    slot.setDomen(domen);
                    String role = slotNode.getAttributes().getNamedItem("role").getNodeValue();
                    slot.setRole(role);
                    if (argIsVar)
                        slot.setArgument(ActualData.getVariableInDomenByName(argName, domen));
                    else 
                        slot.setArgument(ActualData.getConstantInDomenByName(argName, domen));
                    frameBody.addSlot(slot);
                }
                ArrayList<Quantor> qntrs = Quantor.getQuantorArray(quantors, frameBody.getAllVariablesInBody());
                if (frameBody.getRoleConceptAccordance().containsKey("характеристика") &&
                        frameBody.getRoleConceptAccordance().containsKey("значение") &&
                        frameBody.getSlots().size() == 2)
                    fr = new CharacteristicFrame(name, predicate, qntrs, frameBody);
                else 
                    fr = new EventFrame(name, predicate, qntrs, frameBody);
                ActualData.addFrameToHierarchy(fr);
            }
            else 
                notAddedFrames.add(frame);
            while (!notAddedFrames.isEmpty()){
                for (Node frameNode: notAddedFrames){
                    if (frameNode.getAttributes().getNamedItem("type").getNodeValue().equals("notFrame")){
                        String operandName = frameNode.getAttributes().getNamedItem("operand").getNodeValue();
                        if (!ActualData.avalibleFrameName(operandName)){
                            String notFrameName = frameNode.getAttributes().getNamedItem("name").getNodeValue();
                            NotFrame newFrame = new NotFrame(notFrameName, ActualData.getFrameByName(operandName));
                            ActualData.addFrameToHierarchy(newFrame);
                            notAddedFrames.remove(frameNode);
                            break;
                        }
                    }
                    else if (frameNode.getAttributes().getNamedItem("type").getNodeValue().equals("AND")){
                        String firstOperandName = frameNode.getAttributes().getNamedItem("firstOperand").getNodeValue();
                        String secondOperandName = frameNode.getAttributes().getNamedItem("secondOperand").getNodeValue();
                        if (!ActualData.avalibleFrameName(firstOperandName) && !ActualData.avalibleFrameName(secondOperandName)){
                            String andFrameName = frameNode.getAttributes().getNamedItem("name").getNodeValue();
                            AbstractFrame frst = ActualData.getFrameByName(firstOperandName);
                            AbstractFrame scnd = ActualData.getFrameByName(secondOperandName);
                            AndFrame newFrame = new AndFrame(andFrameName, frst, scnd);
                            ActualData.addFrameToHierarchy(newFrame);
                            notAddedFrames.remove(frameNode);
                            break;
                        }
                    }
                    else if (frameNode.getAttributes().getNamedItem("type").getNodeValue().equals("OR")){
                        String firstOperandName = frameNode.getAttributes().getNamedItem("firstOperand").getNodeValue();
                        String secondOperandName = frameNode.getAttributes().getNamedItem("secondOperand").getNodeValue();
                        if (!ActualData.avalibleFrameName(firstOperandName) && !ActualData.avalibleFrameName(secondOperandName)){
                            String andFrameName = frameNode.getAttributes().getNamedItem("name").getNodeValue();
                            AbstractFrame frst = ActualData.getFrameByName(firstOperandName);
                            AbstractFrame scnd = ActualData.getFrameByName(secondOperandName);
                            OrFrame newFrame = new OrFrame(andFrameName, frst, scnd);
                            ActualData.addFrameToHierarchy(newFrame);
                            notAddedFrames.remove(frameNode);
                            break;
                        }
                    }
                }
            }
        }
    }
    private static void writeDocument(Document document, String absolutePath) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(absolutePath);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
