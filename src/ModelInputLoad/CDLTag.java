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
public class CDLTag {
    public CDLTag(String tag){
        tagName = tag;
    }
    private String tagName;
    private final HashMap<String, String> simpleProperties = new HashMap<>();
    private final HashMap<String, ArrayList<CDLTag>> complexTagProperties = new HashMap<>();
    private final HashMap<String, ArrayList<String>> complexStringProperties = new HashMap<>();
    
    public void setName(String name){
        tagName = name;
    }
    public String getName(){
        return tagName;
    }
    public void addSimpleProperty(String property, String value){
        simpleProperties.put(property, value);
    }
    public String getSimplePropertyValue(String property){
        return simpleProperties.get(property);
    }
    public ArrayList<String> getComplexStringPropertyValue(String property){
        return complexStringProperties.get(property);
    }
    public ArrayList<CDLTag> getComplexTagPropertyValue(String property){
        return complexTagProperties.get(property);
    }
    public HashMap<String, String> getSimpleProperties(){
        return simpleProperties;
    }
    public void addComplexTagProperty(String property, CDLTag value){
        if (complexTagProperties.containsKey(property))
            complexTagProperties.get(property).add(value);
        else{
            ArrayList<CDLTag> values =  new ArrayList<>();
            values.add(value);
            complexTagProperties.put(property, values);
        }
    }
    public HashMap<String, ArrayList<CDLTag>> getComplexTagProperties(){
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
            ArrayList<CDLTag> values = complexTagProperties.get(property);
            tabShift++;
            for (CDLTag tag: values){
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
    /*static CDLTag parseStr(String s){
        s = s.trim();
        CDLTag rtrn = new CDLTag("INPUT_STRING_IS_INCORRECT");
        int inputLength = s.length();
        if (s.charAt(0) != '<' || s.charAt(inputLength-1) != '>')
            return rtrn;
        else s = s.substring(1).substring(0, inputLength-2);
        inputLength = inputLength - 2;
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
                if (counter == inputLength)
                    return rtrn;
                while (s.charAt(counter) == '\n' || s.charAt(counter) == '\t' || s.charAt(counter) == ' '){
                    counter++;
                    if (counter == inputLength)
                        return rtrn;
                }
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
                    rtrn.simpleProperties.put(propertyName.trim(), propertyValue);
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
                        if (s.charAt(counter) == '[' || s.charAt(counter) == '{')
                            depth++;
                        else if (s.charAt(counter) == ']' || s.charAt(counter) == '}')
                            depth--;
                        if (depth > 0)
                            propertyValue += s.charAt(counter);
                        counter++;
                    }
                    complexProperties.put(propertyName.trim(), propertyValue);
                    propertyNameHasBeenReaded = false;
                    propertyName = "";
                    propertyValue = "";
                }
            }
        }
        for (String property: complexProperties.keySet()){
            String value = complexProperties.get(property);
            ArrayList<String> atomValues = getStrAtomValuesOfComplexProperty(value);
            for (String atomValue: atomValues){
                if (atomValue.equals("") || atomValue.charAt(0) != '<')
                    rtrn.addComplexStringProperty(property, atomValue);
                else if (atomValue.charAt(0) == '<'){
                    CDLTag valueTag = parseString(atomValue);
                    rtrn.addComplexTagProperty(property, valueTag);
                }
            }
        }
        return rtrn;
    }*/
    private static int counter = 0;
    private static String cdlStr;
    public static CDLTag parseString(String s){
        cdlStr = s.trim();
        counter = 0;
        CDLTag rtrn = parse();
        System.out.println("cdl file is parsed");
        return rtrn;
    }
    private static CDLTag parse(){
        CDLTag rtrn = new CDLTag("");
        skipSpaces();
        if (cdlStr.charAt(counter) != '<')
            return rtrn;
        else
            counter++;
        while ((cdlStr.charAt(counter) != ' ') && (cdlStr.charAt(counter) != '\n') && (cdlStr.charAt(counter) != '\t')){
            rtrn.tagName += cdlStr.charAt(counter);
            counter++;
        }
        //System.out.println("tagName:" + rtrn.tagName);
        skipSpaces();
        while (cdlStr.charAt(counter) != '>'){
            String propName = "";
            while (cdlStr.charAt(counter) != ':'){
                propName+=cdlStr.charAt(counter);
                counter++;
                if (counter == cdlStr.length())
                    break;
            }
            //System.out.println("propName:" + propName);
            counter++;
            if (cdlStr.charAt(counter) == '\"'){
                counter++;
                String simplePropVal = "";
                while (cdlStr.charAt(counter) != '\"'){
                    simplePropVal += cdlStr.charAt(counter);
                    counter++;
                }
                counter++;
                //System.out.println("propVal:" + simplePropVal);
                rtrn.addSimpleProperty(propName, simplePropVal);
            }
            if (cdlStr.charAt(counter) == '['){
                //System.out.println("complexPropReached");
                counter++;
                skipSpaces();
                if (cdlStr.charAt(counter) == '\"'){
                    //System.out.println("complexStrPropReached");
                    skipSpaces();
                    ArrayList<String> props = readSimpleProps();
                    //System.out.println("propVal:" + props);
                    rtrn.complexStringProperties.put(propName, props);
                }
                else if (cdlStr.charAt(counter) == '<'){
                    //System.out.println("complexTagPropReached");
                    ArrayList<CDLTag> props = new ArrayList<>();
                    while (cdlStr.charAt(counter) != ']'){
                        CDLTag prop = parse();
                        //System.out.println("propVal:" + prop.getConDesLanStructure());
                        props.add(prop);
                        skipSpaces();
                    }
                    rtrn.complexTagProperties.put(propName, props);
                    counter++;
                }
                else 
                    skipSpaces();
            }
            skipSpaces();
        }
        counter++;
        return rtrn;
    }
    private static ArrayList<String> readSimpleProps(){
        ArrayList<String> rtrn = new ArrayList<>();
        int depth = 1;
        while (depth!=0){
            if (cdlStr.charAt(counter) == '\"'){
                counter++;
                String atomValue = "";
                while (cdlStr.charAt(counter) != '\"'){
                    atomValue += cdlStr.charAt(counter);
                    counter++;
                }
                rtrn.add(atomValue);
                counter++;
            }
            skipSpaces();
            if (cdlStr.charAt(counter) == ']')
                depth--;
        }
        counter++;
        return rtrn;
    }
    private static void skipSpaces(){
        while ((cdlStr.charAt(counter) == ' ') || 
                (cdlStr.charAt(counter) == '\n') || 
                (cdlStr.charAt(counter) == '\t'))
            counter++;
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
                    } while (depth > 0);
                    atomValue = atomValue.trim();
                    rtrn.add(atomValue);
                    atomValue = "";
                    valueIsReading = false;
                }
            }
        }
        return rtrn;
    }
}
