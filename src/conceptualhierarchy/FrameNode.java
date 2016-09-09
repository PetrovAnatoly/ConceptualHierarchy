/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Frames.AbstractFrame;
import java.util.ArrayList;
import java.util.HashSet;

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
    public HashSet<FrameNode> getAllDescendants(){
        HashSet<FrameNode> rtrnSet = new HashSet<>();
        rtrnSet.addAll(childNodes);
        for (FrameNode child: childNodes)
            rtrnSet.addAll(child.getAllDescendants());
        return rtrnSet;
    }
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
        if (arg.childNodes.contains(this))
            return true;
        for (FrameNode i: arg.childNodes)
            if (isDescedentOf(i))
                return true;
        return false;
    };
    
   /* static HashSet<FrameNode> visitedNodes = new HashSet<>();
    static HashSet<FrameNode> isaNodesToSkip = new HashSet<>();
    HashSet<AbstractFrame> getMinimisedIsaSetForFrame(AbstractFrame arg){
        HashSet<AbstractFrame> rtrnSet = new HashSet<>();
        for (FrameNode node: childNodes){
            if (visitedNodes.contains(node))
                continue;
            AbstractFrame nodeValue = node.getValue();
            if (arg != nodeValue && arg.ISA(nodeValue)){
                if (node.parents.size()>1)
                    isaNodesToSkip.add(node);
                HashSet<AbstractFrame> isaSetAmongChilds = node.getMinimisedIsaSetForFrame(arg);
                if (isaSetAmongChilds.isEmpty()){
                    boolean b = true;
                    for (FrameNode ch: node.childNodes)
                        if (isaNodesToSkip.contains(ch)){
                            b = false;
                            isaNodesToSkip.add(node);
                            break;
                        }
                    if (b)
                        rtrnSet.add(nodeValue); 
                }
                else 
                    rtrnSet.addAll(isaSetAmongChilds);
            }
            else 
                visitedNodes.addAll(node.getAllDescendants());
            visitedNodes.add(node);
        }
        return rtrnSet;
    }
    */
    public void reformIfNeeded(FrameNode arg){
        ArrayList<FrameNode> toRemoveSet = new ArrayList();
        boolean childNodesContainsArg = childNodes.contains(arg);
        boolean isDescedentOfArg = isDescedentOf(arg);
        for (FrameNode i: (ArrayList<FrameNode>)childNodes.clone()){
            if (!childNodesContainsArg || arg!=i){
                if (i.value.ISA(arg.value)){
                    if (isDescedentOfArg){
                        arg.removeChild(i);
                        i.removeParent(arg);
                    }
                    else if (!i.isDescedentOf(arg)){
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
