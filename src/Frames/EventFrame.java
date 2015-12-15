/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Frames.Structure.Body;
import Frames.Structure.Quantor;
import java.util.ArrayList;

/**
 *
 * @author Anatoly  
 */
public class EventFrame extends AbstractSimpleFrame{

    public EventFrame(String frameName, String framePredicate, 
            ArrayList<Quantor> frameQuantors, Body frameBody) {
        super(frameName, framePredicate, frameQuantors, frameBody);
    }
}
