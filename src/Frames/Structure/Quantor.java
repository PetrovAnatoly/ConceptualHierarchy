/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Structure;

import java.util.ArrayList;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class Quantor {
    public Quantor(){
        type = "noQuantor";
    }
    public Quantor(String arg, Variable variable){
        if (arg.charAt(0) == 'A')
            type = "A";
        else if (arg.contains("[") && arg.contains("]")){
            type = "[]";
            value = new Integer(arg.substring(arg.indexOf("[")+1,arg.indexOf("]")));
        }
        else if (arg.contains("[") && arg.contains("}")){
            type = "[}";
            value = new Integer(arg.substring(arg.indexOf("[")+1,arg.indexOf("}")));
        }
        else if (arg.contains("{") && arg.contains("]")){
            type = "{]";
            value = new Integer(arg.substring(arg.indexOf("{")+1,arg.indexOf("[")));
        }
        else
            type = "noQuantor";
        var = variable;
    }
    public Quantor(String tp, int val, Variable vr){
        type = tp; 
        value = val;
        var = vr;
    }
    public static boolean correctQuantorString(String arg){
        return true;
    }
    String type; //type is A, [], [} or {]
    int value;
    Variable var;
    
    public static ArrayList<Quantor> getQuantorArray(String arg, ArrayList<Variable> variables){
        String quantor = "";
        ArrayList<Quantor> rtrn = new ArrayList();
        for (int i = 0; i < arg.length(); i++) 
        {
            if (arg.charAt(i) == '.') { 
                for (Variable vrbl: variables){
                    if (quantor.endsWith(vrbl.getName())){
                        rtrn.add(new Quantor(quantor.replace(vrbl.getName(), ""), vrbl));
                    }
                }
                quantor = "";
            }
            else quantor+=arg.charAt(i);
        }
        return rtrn;
    }
    
    public String getType() { return type;}
    public Variable getVariable() { return var;}
    @Override
    public String toString(){
        switch (type) {
            case "A":
                return "A"+var.getName()+".";
            case "[}":
                return '[' + Integer.toString(value) + '}'+var.getName()+".";
            case "{]":
                return '{' + Integer.toString(value) + ']'+var.getName()+".";
            case "[]":
                return '[' + Integer.toString(value) + ']'+var.getName()+".";
            default: return "";
        }
    }
    public boolean covered(Quantor arg){
        switch (arg.type) {
            case "A":
                return type.equals("A");
            case "[}":
                if (type.equals("A"))
                    return true;
                else if (type.equals("[]") || type.equals("[}"))
                    return (value >= arg.value);
                else
                    return false;
            case "{]":
                if (type.equals("[]") || type.equals("{]"))
                    return value<=arg.value;
                else
                    return false;
            case "[]":
                return (type.equals("[]") && value==arg.value);
            default: return true;
        }
    }
    public boolean isContrary(Quantor arg){
        if (type.equals("A")){
            if (arg.type.equals("A") || arg.type.equals("[}")){
                mergeResult = "A";
                return false;
            }
            else 
                return true;
        }
        else if (type.equals("[}")){
            switch (arg.type) {
                case "[]":
                    if (arg.value<value)
                        return true;
                    else {
                        mergeResult = "[" + String.valueOf(arg.value) + "]";
                        return false;
                    }
                case "[}":
                    if (arg.value<value){
                        mergeResult = "[" + String.valueOf(value) + "}";
                        return false;
                    }
                    else {
                        mergeResult = "[" + String.valueOf(arg.value) + "}";
                        return false;
                }
                case "{]":
                    if (arg.value<value)
                        return true;
                    else if (arg.value == value){
                        mergeResult = "[" + String.valueOf(arg.value) + "]";
                        return false;
                    }
                    else if (arg.value>value){
                        mergeResult = "[" + String.valueOf(value) + "-" + String.valueOf(arg.value) + "]";
                        return false;
                }
                case "A":{
                    mergeResult = "A";
                    return false;
                }
            }
        }
        else if (type.equals("{]")){
            switch (arg.type) {
                case "[]":
                    if (arg.value>value)
                        return true;
                    else {
                        mergeResult = "[" + String.valueOf(arg.value) + "]";
                        return false;
                    }
                case "{]":
                    if (arg.value>value){
                        mergeResult = "{" + String.valueOf(value) + "]";
                        return false;
                    }
                    else {
                        mergeResult = "[" + String.valueOf(arg.value) + "}";
                        return false;
                }
                case "[}":
                    if (arg.value>value)
                        return true;
                    else if (arg.value == value){
                        mergeResult = "[" + String.valueOf(arg.value) + "]";
                        return false;
                    }
                    else if (arg.value<value){
                        mergeResult = "[" + String.valueOf(arg.value) + "-" + String.valueOf(value) + "]";
                        return false;
                }
                case "A":{
                    return true;
                }
            }
        }
        else if (type.equals("[]")){
            switch (arg.type) {
                case "A":
                    return true;
                case "[]":
                    if (arg.value != value)
                        return true;
                    else {
                        mergeResult = "[" + String.valueOf(value) + "]";
                        return false;
                    }
                case "[}":
                    if (arg.value > value)
                        return true;
                    else {
                        mergeResult = "[" + String.valueOf(value) + "]";
                        return false;
                }
                case "{]":
                    if (arg.value < value)
                        return true;
                    else {
                        mergeResult = "[" + String.valueOf(value) + "]";
                        return false;
                }
            }
        }
        return true;
    }
    public static String mergeResult;
    public static void main(String[] args){
        
    }
}


