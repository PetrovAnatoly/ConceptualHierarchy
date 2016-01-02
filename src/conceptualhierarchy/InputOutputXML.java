/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;
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

    public static void save() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        document.appendChild(document.createElement("data"));
        addFrames(document, ActualData.getFrames());
        addConcepts(document,ActualData.getConcepts());
        addConstants(document, ActualData.getConstants());
        addVariables(document, ActualData.getVariables());
        //addExtensionals(document, ActualData.getAllExtensionals());
        writeDocument(document);
    }
    public static void load() throws SAXException, IOException{
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("other.xml");
            Node root = document.getDocumentElement();
            Node conceptNode = null;
            Node variableNode = null;
            Node constantNode = null;
            Node frameNode = null;
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
                }
            }
            loadConcepts(conceptNode);
            loadConstants(constantNode);
            loadVariables(variableNode);
            loadFrames(frameNode);
            loadDefConcepts(conceptNode);
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
    private static void addSimpleFrame(Document document, AbstractSimpleFrame frame){
        Node root = document.getDocumentElement();
        Node frames = null;
        if (root.getChildNodes().getLength() != 0) 
            frames = root.getChildNodes().item(0);
        if (frames == null){
            frames = document.createElement("frames");
            root.appendChild(frames);
        }
        Element frameElem = document.createElement("Frame");
        Element name = document.createElement("Name");
        name.setTextContent(frame.getName());
        Element predicate = document.createElement("Predicate");
        predicate.setTextContent(frame.getPredicate());
        Element quantors = document.createElement("Quantors");
        quantors.setTextContent(frame.getQuantorsLine());
        Element body = document.createElement("Body");
        for (Slot slot: frame.getBody().getSlots()){
            Element slotElem = document.createElement("Slot");
            Element slotRole = document.createElement("Role");
            slotRole.setTextContent(slot.getRole());
            Element slotArg = document.createElement("Argument");
            slotArg.setTextContent(slot.getArgument().getName());
            Element slotDomen = document.createElement("Domen");
            slotDomen.setTextContent(slot.getDomen().getName());
            slotElem.appendChild(slotRole);
            slotElem.appendChild(slotArg);
            slotElem.appendChild(slotDomen);
            body.appendChild(slotElem);
        }
        frameElem.appendChild(name);
        frameElem.appendChild(predicate);
        frameElem.appendChild(quantors);
        frameElem.appendChild(body);
        frames.appendChild(frameElem);
    }
    private static void addConcept(Document document, Concept concept){
        Node root = document.getDocumentElement();
        Node concepts = null;
        if (root.getChildNodes().getLength() != 0) 
            concepts = root.getAttributes().getNamedItem("concepts");
        if (concepts == null){
            concepts = document.createElement("concepts");
            root.appendChild(concepts);
        }
        Element conceptElem = document.createElement("Concept");
        Element name = document.createElement("Name");
        name.setTextContent(concept.getName());
        Element comment = document.createElement("Comment");
        comment.setTextContent(concept.getComment());
        Element props = document.createElement("Properties");
        for (String property: concept.getProperties()){
            Element propElem = document.createElement("Property");
            propElem.setTextContent(property);
            props.appendChild(propElem);
        }
        if (concept instanceof DefConcept){
            //доделать или переделать
        }
        conceptElem.appendChild(name);
        conceptElem.appendChild(comment);
        conceptElem.appendChild(props);
        concepts.appendChild(conceptElem);
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
    public static AbstractSimpleFrame getFrame() throws ParserConfigurationException, SAXException, IOException{
        AbstractSimpleFrame fr = new AbstractSimpleFrame();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("other.xml");
        Node root = document.getDocumentElement();
        NodeList frames = root.getChildNodes();
        Node frame = frames.item(0);
        NodeList childs = frame.getChildNodes();
        Node body = null;
        Node quantors;
        for (int i = 0; i < childs.getLength(); i++){
            Node node = childs.item(i);
            switch (node.getNodeName()) {
                case "Name":
                    fr.setName(node.getTextContent());
                    break;
                case "Predicate":
                    fr.setPredicate(node.getTextContent());
                    break;
                case "Body":
                    body = node;
                    break;
                case "Quantors":
                    quantors = node;
                    break;
            }
        }
        for (int i = 0; i< body.getChildNodes().getLength();i++){
        }
        return fr;
    }
    public static void main(String[] args) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("BookCatalog.xml");
 
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            
            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }
 
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
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
            predElem.setAttribute("val", predicate);
            
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
    private static void writeDocument(Document document) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }}
}
