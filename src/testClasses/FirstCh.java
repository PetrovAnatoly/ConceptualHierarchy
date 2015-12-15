/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClasses;

/**
 *
 * @author Anatoly
 */
public class FirstCh extends Abstr{

    @Override
    public boolean rel(Abstr arg) {
        return false;
    }
    public boolean rel(FirstCh arg) { return true;}
}
