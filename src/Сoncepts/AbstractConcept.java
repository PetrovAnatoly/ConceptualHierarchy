/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Сoncepts;

public abstract class AbstractConcept {
    //fields
    protected String name;
    //methods
    public String getName(){ return name;}
    public void setName(String arg) { name = arg;}
    public abstract boolean ISA(AbstractConcept arg);
}
