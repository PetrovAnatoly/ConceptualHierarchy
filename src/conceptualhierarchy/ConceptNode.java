/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import java.util.ArrayList;
import Сoncepts.AbstractConcept;
import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public class ConceptNode{
    //constructors
    public ConceptNode(Concept arg)
    {
        value = arg; 
    }
    
    //fields
    protected Concept value;
    protected ArrayList<ConceptNode> childNodes = new ArrayList();
    protected ArrayList<ConceptNode> parents = new ArrayList();
    
    //methods
    public boolean addChild(ConceptNode arg) {
        if (childNodes.contains(arg))
            return false;
        else{
            childNodes.add(arg);
            return false;
        }
    };
    public boolean removeChild(ConceptNode arg) {
        if (childNodes.contains(arg)){
            childNodes.remove(arg);
            return true;
        }
        else
            return false;
    };
    public boolean addParent(ConceptNode arg){
        if (parents.contains(arg))
            return false;
        else{
            parents.add(arg);
            return false;
        }
    };
    public boolean removeParent(ConceptNode arg){
        if (parents.contains(arg)){
            parents.remove(arg);
            return true;
        }
        else
            return false;
    };

    public ArrayList<ConceptNode> getChildNodes() { return childNodes;}

    public Concept getValue() { return value;}
    
    public boolean isDescedentOf(ConceptNode arg){
        for (ConceptNode i: arg.childNodes)
            if (this == i || isDescedentOf(i))
                return true;
        return false;
    };
    public void reformIfNeeded(ConceptNode arg){
        ArrayList<ConceptNode> toRemoveSet = new ArrayList();
        boolean childNodesContainsArg = childNodes.contains(arg);
        for (ConceptNode i: childNodes)
            if (!childNodesContainsArg || arg!=i){
                if (i.value.ISA(arg.value)){
                    if (!arg.childNodes.contains(arg)){
                        arg.addChild(i);
                        i.addParent(arg);
                    }
                    if (childNodesContainsArg){
                        i.removeParent(this);
                        toRemoveSet.add(i);
                    }
                    arg.reformIfNeeded(i);
                }
                else 
                    i.reformIfNeeded(arg);
            }
        childNodes.removeAll(toRemoveSet);
    }
}
