/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelInputLoad;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Anatoly
 */
public class ConDesLanTag {
    public ConDesLanTag(String tag){
        tagName = tag;
    }
    private String tagName;
    private HashMap<String, String> simpleProperties = new HashMap<>();
    private HashMap<String, ArrayList<ConDesLanTag>> complexTagProperties = new HashMap<>();
    private HashMap<String, ArrayList<String>> complexStringProperties = new HashMap<>();
    
    public void setName(String name){
        tagName = name;
    }
    public String getName(){
        return tagName;
    }
    public void addSimpleProperty(String property, String value){
        simpleProperties.put(property, value);
    }
    public HashMap<String, String> getSimpleProperties(){
        return simpleProperties;
    }
    public void addComplexTagProperty(String property, ConDesLanTag value){
        if (complexTagProperties.containsKey(property))
            complexTagProperties.get(property).add(value);
        else{
            ArrayList<ConDesLanTag> values =  new ArrayList<>();
            values.add(value);
            complexTagProperties.put(property, values);
        }
    }
    public HashMap<String, ArrayList<ConDesLanTag>> getComplexTagProperties(){
        return complexTagProperties;
    }
    public void addComplexStringProperty(String property, String value){
        if (complexStringProperties.containsKey(property))
            complexStringProperties.get(property).add(value);
        else{
            ArrayList<String> values =  new ArrayList<>();
            values.add(value);
            complexStringProperties.put(property, values);
        }
    }
    public HashMap<String, ArrayList<String>> getComplexStringProperties(){
        return complexStringProperties;
    }
    private static int tabShift = 0;
    public String getConDesLanStructure(){
        //well be soon
        String rtrn = "";
        tabShift = 0;
        rtrn+=this.toCDSWithShift(tabShift);
        return rtrn;
    }
    private String toCDSWithShift(int shift){
        String rtrn = "";
        for (int i = 0; i < shift; i++)
            rtrn+="\t";
        rtrn +="<" + tagName + "\n";
        tabShift = ++shift;
        for (String property: simpleProperties.keySet()){
            for (int i = 0; i < shift; i++)
                rtrn+="\t";
            rtrn+=property + ":" + "\"" + simpleProperties.get(property) + "\"\n";
        }
        for (String property: complexTagProperties.keySet()){
            for (int i = 0; i < shift; i++)
                rtrn+="\t";
            rtrn+=property + ":" + "[\n";
            ArrayList<ConDesLanTag> values = complexTagProperties.get(property);
            tabShift++;
            for (ConDesLanTag tag: values){
                rtrn+=tag.toCDSWithShift(tabShift);
            }
            tabShift--;
            for (int i = 0; i < shift; i++)
                rtrn+="\t";
            rtrn+="]\n";
        }
        for (String property: complexStringProperties.keySet()){
            for (int i = 0; i < shift; i++)
                rtrn+="\t";
            rtrn+=property + ":" + "[\n";
            ArrayList<String> values = complexStringProperties.get(property);
            tabShift++;
            for (String value: values){
                for (int i = 0; i < tabShift; i++)
                    rtrn+="\t";
                rtrn+="\""+value+"\"\n";
            }
            tabShift--;
            for (int i = 0; i < shift; i++)
                rtrn+="\t";
            rtrn+="]\n";
        }
        tabShift = --shift;
        for (int i = 0; i < shift; i++)
            rtrn+="\t";
        rtrn+=">\n";
        return rtrn;
    }
    static ConDesLanTag parseString(String s){
        s = s.trim();
        ConDesLanTag rtrn = new ConDesLanTag("INPUT_STRING_IS_INCORRECT");
        int inputLength = s.length();
        if (s.charAt(0) != '<' || s.charAt(inputLength-1) != '>')
            return rtrn;
        else s = s.substring(1).substring(0, inputLength-2);
        inputLength = inputLength - 2;
        //System.out.println("\n--------------------------------------\n");
        //System.out.println(s);
        //System.out.println("\n--------------------------------------\n");
        HashMap<String, String> complexProperties = new HashMap<>();
        int counter = 0;
        boolean nameHasBeenReaded = false;
        String name = "";
        boolean propertyNameHasBeenReaded = false;
        String propertyName = "";
        String propertyValue = "";
        while (counter < inputLength){
            if (!nameHasBeenReaded){
                while ((s.charAt(counter) != ' ') && (s.charAt(counter) != '\n') && (s.charAt(counter) != '\t')){
                    name += s.charAt(counter);
                    counter++;
                }
                nameHasBeenReaded = true;
                rtrn.setName(name);
                while (s.charAt(counter) == '\n' || s.charAt(counter) == '\t' || s.charAt(counter) == ' ')
                    counter++;
            }
            else {
                while (!propertyNameHasBeenReaded){
                    if (s.charAt(counter) == ':')
                        propertyNameHasBeenReaded = true;
                    else 
                        propertyName += s.charAt(counter);
                    counter++;
                    if (counter == inputLength)
                        break;
                }
                if (counter == inputLength)
                    break;
                if (s.charAt(counter) == '\"'){
                    counter++;
                    while (s.charAt(counter) != '\"'){
                        propertyValue += s.charAt(counter);
                        counter++;
                    }
                    counter++;
                    rtrn.simpleProperties.put(propertyName, propertyValue);
                    propertyName = "";
                    propertyValue = "";
                    while (counter < inputLength && (s.charAt(counter) == '\n' || s.charAt(counter) == '\t' || s.charAt(counter) == ' '))
                        counter++;
                    if (counter == inputLength)
                        break;
                    propertyNameHasBeenReaded = false;
                }
                else if (s.charAt(counter) == '['){
                    counter++;
                    int depth = 1; 
                    while (depth > 0){
                        if (s.charAt(counter) == '[')
                            depth++;
                        else if (s.charAt(counter) == ']')
                            depth--;
                        if (depth > 0)
                            propertyValue += s.charAt(counter);
                        counter++;
                    }
                    complexProperties.put(propertyName, propertyValue);
                    propertyNameHasBeenReaded = false;
                    propertyName = "";
                    propertyValue = "";
                }
            }
        }
        System.out.println(complexProperties);
        for (String property: complexProperties.keySet()){
            String value = complexProperties.get(property);
            ArrayList<String> atomValues = getStrAtomValuesOfComplexProperty(value);
            System.out.println("---------------------------" + property + "---------------------------");
            for (String atomValue: atomValues){
                if (atomValue.equals("") || atomValue.charAt(0) != '<'){
                    System.out.println("---------------------------value---------------------------");
                    System.out.println(atomValue);
                    rtrn.addComplexStringProperty(property, atomValue);
                }
                else if (atomValue.charAt(0) == '<'){
                    ConDesLanTag valueTag = parseString(atomValue);
                    rtrn.addComplexTagProperty(property, valueTag);
                    System.out.println("---------------------------value---------------------------");
                    System.out.println(valueTag.getConDesLanStructure());
                }
            }
        }
        return rtrn;
    }
    
    private static ArrayList<String> getStrAtomValuesOfComplexProperty(String s){
        ArrayList<String> rtrn = new ArrayList<>();
        int inputLength = s.length();
        int counter = 0;
        boolean valueIsReading = false;
        String atomValue = "";
        while ( counter < inputLength){
            if (!valueIsReading){
                if (counter == inputLength)
                    return rtrn;
                while (s.charAt(counter) == '\n' || s.charAt(counter) == '\t' || s.charAt(counter) == ' '){
                    counter++;
                    if (counter == inputLength)
                        return rtrn;
                }
                valueIsReading = true;
            }
            else {
                if (s.charAt(counter) == '\"'){
                    counter++;
                    while (s.charAt(counter) != '\"'){
                        atomValue += s.charAt(counter);
                        counter++;
                    }
                    counter++;
                    valueIsReading = false;
                    atomValue = atomValue.trim();
                    rtrn.add(atomValue);
                    atomValue = "";
                }
                else if (s.charAt(counter) == '<'){
                    int depth = 0; 
                    do {
                        atomValue += s.charAt(counter);
                        if (s.charAt(counter) == '<')
                            depth++;
                        else if (s.charAt(counter) == '>')
                            depth--;
                        counter++;
                    }
                    while (depth > 0);
                    atomValue = atomValue.trim();
                    rtrn.add(atomValue);
                    atomValue = "";
                    valueIsReading = false;
                }
            }
        }
        return rtrn;
    } 
    public static ConDesLanTag parse(String s){
        while (s.charAt(0) != '<'){
            if (s.length() == 0)
                return new ConDesLanTag(null);
            s = s.substring(1);
        }
        s = s.substring(1);
        String name = "";
        while (s.charAt(0) != '\n' && s.charAt(0) != '\t' && s.charAt(0) != '>'){
            name+=s.charAt(0);
            if (s.length() == 0)
                return new ConDesLanTag(null);
            s = s.substring(1);
        }
        s = s.substring(1);
        ConDesLanTag rtrn = new ConDesLanTag(name);
        boolean propertyNameIsStarted = false;
        String propertyName = "";
        for (int i = 0; i < s.length(); i++){
            if (propertyNameIsStarted){
                if (s.charAt(i) == ':'){
                    i++;
                    if (s.charAt(i) == '\"'){
                        String propertyValue = "";
                        i++;
                        while (s.charAt(i) != '\"'){
                            propertyValue += s.charAt(i);
                            i++;
                        }
                        i++;
                        rtrn.addSimpleProperty(propertyName, propertyValue);
                    }
                    else if (s.charAt(i) == '['){
                        i++;
                        if (s.charAt(i) == '\"'){
                            String propertyValue = "";
                            i++;
                            boolean strIsStarted = true;
                            while (s.charAt(i) != ']'){
                                if (strIsStarted){
                                    if (s.charAt(i) != '\"')
                                        propertyValue+= s.charAt(i);
                                    else{
                                        rtrn.addComplexStringProperty(propertyName, propertyValue);
                                        strIsStarted = false;
                                    }
                                }
                                else if (s.charAt(i) == '\"')
                                    strIsStarted = true;
                                i++;
                            }
                            i++;
                        }
                        else if (s.charAt(i) == '<'){
                            String propertyTagStr = "";
                            int depthCounter = 1;
                            boolean tagIsStarted = false;
                            while (s.charAt(i) != ']'){
                                if (!tagIsStarted){
                                    if (s.charAt(i) == '<'){
                                        propertyTagStr += s.charAt(i);
                                        depthCounter++;
                                        tagIsStarted = true;
                                    }
                                }
                                else {
                                    propertyTagStr += s.charAt(i);
                                    if (s.charAt(i) == '>')
                                        depthCounter--;
                                    if (s.charAt(i) == '<')
                                        depthCounter++;
                                    if (depthCounter == 0){
                                        rtrn.addComplexTagProperty(propertyName, parseString(propertyTagStr));
                                        propertyTagStr = "";
                                        tagIsStarted = false;
                                    }
                                }
                                i++;
                            }
                            i++;
                        }
                        i++;
                    }
                    propertyNameIsStarted = false;
                    propertyName = "";
                }
                else 
                    propertyName += s.charAt(i);
                
            }
            else if (s.charAt(i) != '\n' && s.charAt(i) != '\t'){
                if (s.charAt(i) == '>')
                    return rtrn;
                propertyNameIsStarted = true;
                propertyName += s.charAt(i);
            }
        }
        return rtrn;
    }
}
