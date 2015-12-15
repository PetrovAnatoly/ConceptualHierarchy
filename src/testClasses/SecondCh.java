/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClasses;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Anatoly
 */
public class SecondCh extends Abstr{

    @Override
    public boolean rel(Abstr arg) {
        return false;}
    public boolean rel(SecondCh arg)
    {
        return true;
    }
    public static void main(String[] args)
    {
        ArrayList<String> arr=new ArrayList();
        arr.add("11");
        String s = "123";
        arr.add(s);
        ArrayList<String> arr2=new ArrayList();
        arr2.add("11");
        arr2.add("123");
        System.out.print(arr.contains("11"));
        System.out.print(arr.contains("123"));
        System.out.print(arr.containsAll(arr2));
        
    }
}
