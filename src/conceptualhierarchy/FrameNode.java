/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Frames.AbstractFrame;
import java.util.ArrayList;

/**
 *
 * @author Anatoly
 */
public class FrameNode {
    public FrameNode(AbstractFrame arg){
        value = arg;
    }
    
    //fields
    protected AbstractFrame value;
    protected ArrayList<FrameNode> childNodes = new ArrayList();
    protected ArrayList<FrameNode> parents = new ArrayList();
    
    //methods
    public AbstractFrame getValue() { return value;}
    public ArrayList<FrameNode> getChildNodes() { return childNodes;}
    public ArrayList<FrameNode> getParents() { return parents;}
    public boolean addChild(FrameNode arg) {
        if (childNodes.contains(arg))
            return false;
        else{
            childNodes.add(arg);
            return false;
        }
    };
    public boolean removeChild(FrameNode arg) {
        if (childNodes.contains(arg)){
            childNodes.remove(arg);
            return true;
        }
        else
            return false;
    };
    public boolean addParent(FrameNode arg){
        if (parents.contains(arg))
            return false;
        else{
            parents.add(arg);
            return false;
        }
    };
    public boolean removeParent(FrameNode arg){
        if (parents.contains(arg)){
            parents.remove(arg);
            return true;
        }
        else
            return false;
    };
    
    public boolean isDescedentOf(FrameNode arg){
        for (FrameNode i: arg.childNodes)
            if (this == i || isDescedentOf(i))
                return true;
        return false;
    };
    
    public void reformIfNeeded(FrameNode arg){
        ArrayList<FrameNode> toRemoveSet = new ArrayList();
        boolean childNodesContainsArg = childNodes.contains(arg);
        for (FrameNode i: childNodes)
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
    
    
    private static int shift = 0; 
    public void showAsTree()
    {
        for (int i = 0; i < shift; i++) System.out.print("\t");
        System.out.println(value.getName());
        shift++;
        for (FrameNode ch: childNodes) 
            ch.showAsTree();
        shift--;
    }
}
