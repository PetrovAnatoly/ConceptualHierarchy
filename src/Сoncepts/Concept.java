/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

import ModelInputLoad.CDLTag;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Anatoly
 */
public class Concept extends AbstractConcept{   
    public Concept(){}
    public Concept(String argName){
        name = argName;
        comment = "";
    }
    public Concept(String argName, String argComment){
        name = argName;
        comment = argComment;
    }
    protected String comment;
    public String getComment() { return comment;}
    public void setComment(String cmmnt) { comment = cmmnt;}
    protected ArrayList<String> characteristics = new ArrayList();
    protected HashMap<String, String> properties = new HashMap<>();
    public ArrayList<String> getCharacteristics() { return characteristics;}
    public void setCharacteristics(ArrayList<String> newProperties) { characteristics = newProperties;}
    public void addAllCharacteristics(ArrayList<String> props){
        for (String prop: props)
            if (!characteristics.contains(prop))
                characteristics.add(prop);
    }
    public Concept (String s, ArrayList<String> argProperties){
        name = s;
        for (String prop : argProperties) {
            characteristics.add(prop.trim());
        }
    }
    public Concept (String nameArg, String commentArg, ArrayList<String> argProperties){
        name = nameArg;
        comment = commentArg;
        for (String prop : argProperties) {
            characteristics.add(prop.trim());
        }
    }
    //true, если свойства концепта this покрывают свойства концепта arg
    public boolean ISA(Concept arg){
        if (arg instanceof DefConcept)
            return false;
        if (characteristics.isEmpty())
            return false;
        if (arg.characteristics.isEmpty()) 
            return false;
        for (String argProperty: arg.characteristics){
            for (int i = 0; i < characteristics.size(); i++){
                if (argProperty.equals(characteristics.get(i))) break; 
                if (i == characteristics.size() - 1) return false;
            }   
        }
        return true;
    }
    
    public boolean equal(Concept arg){
        return ISA(arg) && arg.ISA(this); 
    }
    public String characteristicsString(){
        String s = "(";
        for (int i = 0; i < characteristics.size() - 1; i++) 
            s+=characteristics.get(i) + ",";
        if (!characteristics.isEmpty()) 
            s+=characteristics.get(characteristics.size()-1);
        s+=")";
        return s;
    }

    public char[] toConDesLan() {
        String s = "";
        s+="<концепт\n";
        s+="имя:\""+ name +"\"\n";
        s+="свойтва:[";
        for (String prop: characteristics)
            s+="\"" + prop + "\"\n";
        s+="]\n";
        return s.toCharArray();
    }

    public CDLTag toConDesLanTag() {
        CDLTag rtrn = new CDLTag("концепт");
        rtrn.addSimpleProperty("имя", name);
        rtrn.addSimpleProperty("комментарий", comment);
        for (String prop: characteristics)
            rtrn.addComplexStringProperty("свойства", prop);
        return rtrn;
    }
}
