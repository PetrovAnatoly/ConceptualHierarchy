/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

import java.util.ArrayList;

/**
 *
 * @author Anatoly
 */
public class Concept extends AbstractConcept{   
    //constructors
    public Concept(){}
    public Concept(String argName){
        name = argName;
        comment = "";
    }
    public Concept(String argName, String argComment){
        name = argName;
        comment = argComment;
    }
    //fields
    protected String comment;
    public String getComment() { return comment;}
    public void setComment(String cmmnt) { comment = cmmnt;}
    protected ArrayList<String> properties = new ArrayList();
    public ArrayList<String> getProperties() { return properties;}
    public void setProperties(ArrayList<String> newProperties) { properties = newProperties;}
    public void addAllProperties(ArrayList<String> props){
        for (String prop: props)
            if (!properties.contains(prop))
                properties.add(prop);
    }
    public Concept (String s, ArrayList<String> argProperties){
        name = s;
        for (String prop : argProperties) {
            properties.add(prop.trim());
        }
    }
    public Concept (String nameArg, String commentArg, ArrayList<String> argProperties){
        name = nameArg;
        comment = commentArg;
        for (String prop : argProperties) {
            properties.add(prop.trim());
        }
    }
    //true, если свойства концепта this покрывают свойства концепта arg
    public boolean ISA(Concept arg){
        if (arg instanceof DefConcept)
            return false;
        if (properties.isEmpty())
            return false;
        if (arg.properties.isEmpty()) 
            return false;
        for (String argProperty: arg.properties){
            for (int i = 0; i < properties.size(); i++){
                if (argProperty.equals(properties.get(i))) break; 
                if (i == properties.size() - 1) return false;
            }   
        }
        return true;
    }
    
    public boolean equal(Concept arg){
        return ISA(arg) && arg.ISA(this); 
    }
    public String propertiesString(){
        String s = "(";
        for (int i = 0; i < properties.size() - 1; i++) 
            s+=properties.get(i) + ",";
        if (!properties.isEmpty()) 
            s+=properties.get(properties.size()-1);
        s+=")";
        return s;
    }
    //methods
    
    public static void main(String[] args){
        Concept frst = new Concept();
        ArrayList<String> pr= new ArrayList();
        pr.add("q");
        frst.setProperties(pr);
        Concept scnd = new Concept();
        ArrayList<String> pr2= new ArrayList();
        pr2.add("q");
        pr2.add("w");
        scnd.setProperties(pr2);
        System.out.print(frst.ISA(scnd));
        System.out.print(scnd.ISA(frst));
    }
}
