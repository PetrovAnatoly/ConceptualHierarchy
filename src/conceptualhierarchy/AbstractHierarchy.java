/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import Сoncepts.Concept;

/**
 *
 * @author Anatoly
 */
public abstract class AbstractHierarchy {
    //fields
    private String root;
    private ConceptNode conceptBranch = new ConceptNode(new Concept("Concepts"));  
//    private FrameNode frameBranch = 
    //methods
    public abstract boolean add(HierarchyUnitInterface arg);
}
