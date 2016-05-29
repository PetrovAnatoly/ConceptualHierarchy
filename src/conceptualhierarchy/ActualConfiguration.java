/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 *
 * @author Anatoly
 */
public class ActualConfiguration implements java.io.Serializable{
    public ActualConfiguration(){
        boolSettings.put("framesMultipleInheritance", true);
        boolSettings.put("conceptsMultipleInheritance", true);
        
    }
    private HashMap<String, String> strSettings = new HashMap<>();
    private HashMap<String, Boolean> boolSettings = new HashMap<>();
    
    public HashMap<String, String> getStrSettings(){ return strSettings;}
    public HashMap<String, Boolean> getBoolSettings(){ return boolSettings;}
    
    public void putStrSetting(String setting, String value) { strSettings.put(setting, value);}
    public void putBoolSetting(String setting, boolean value) { boolSettings.put(setting, value);}
    public void save() throws IOException{
        File out = new File("configuration");
        OutputStream os = new FileOutputStream(out);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this);
    }
    public static ActualConfiguration load() throws IOException, ClassNotFoundException{
        File in = new File("configuration");
        if (!in.exists())
            return new ActualConfiguration();
        InputStream is = new FileInputStream(in);
        ObjectInputStream ois = new ObjectInputStream(is);
        ActualConfiguration rtrn = (ActualConfiguration)ois.readObject();
        return rtrn;
    }
}
