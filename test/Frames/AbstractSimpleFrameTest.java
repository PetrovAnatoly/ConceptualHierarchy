/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Frames.Structure.Body;
import Frames.Structure.Quantor;
import Frames.Structure.Slot;
import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;
import Сoncepts.Concept;
import Сoncepts.Constant;
import Сoncepts.Variable;

/**
 *
 * @author Anatoly
 */
public class AbstractSimpleFrameTest extends TestCase {
    
    public AbstractSimpleFrameTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("* UtilsJUnit3Test: setUp() method");
    }

    /**
     * Test of getPredicate method, of class AbstractSimpleFrame.
     */
    public void testGetPredicate() {
        System.out.println("getPredicate");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        String expResult = "";
        String result = instance.getPredicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBody method, of class AbstractSimpleFrame.
     */
    public void testGetBody() {
        System.out.println("getBody");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        Body expResult = null;
        Body result = instance.getBody();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantors method, of class AbstractSimpleFrame.
     */
    public void testGetQuantors() {
        System.out.println("getQuantors");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        ArrayList<Quantor> expResult = null;
        ArrayList<Quantor> result = instance.getQuantors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantorsLine method, of class AbstractSimpleFrame.
     */
    public void testGetQuantorsLine() {
        System.out.println("getQuantorsLine");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        String expResult = "";
        String result = instance.getQuantorsLine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ISA method, of class AbstractSimpleFrame.
     */
    public void testISA_AbstractSimpleFrame() {
        System.out.println("ISA_AbstractSimpleFrame");
        AbstractSimpleFrame arg = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        ArrayList<Concept> concepts = new ArrayList<>();
        HashMap<Concept, ArrayList<Constant>> constants = new HashMap<>();
        ArrayList<AbstractSimpleFrame> frames = new ArrayList<>();
        ArrayList<Body> bodies = new ArrayList();
        ArrayList<Slot> slots = new ArrayList<>();
        
        Concept human = new Concept("Человек");
        Concept article = new Concept("Товар");
        Concept shop = new Concept("Магазин");
        Concept conc=new Concept("conc");
        
        Variable x1 = new Variable("x1", human);
        Variable x2= new Variable("x2", human);
        Variable y1 = new Variable("y1", article);
        Variable y2= new Variable("y2", article);
        Variable z1 = new Variable("z1", shop);
        Variable z2 = new Variable("z2", shop);
        Constant ivanov = new Constant("Иванов", human);
        Constant petrov = new Constant("Петров", human);
        Constant pen = new Constant("Ручка", article);
        Constant apple = new Constant("Яблоко", article);
        Constant shop1 = new Constant("Магазин 1", shop);
        Constant shop2 = new Constant("Магазин 1", human);
        
        for (int i = 0; i<3; i++){
            
        }
        /*Slot slot1 = new Slot("agent", x, conc);
        Slot slot11 = new Slot("object", x1, conc);
        Slot slot2 = new Slot("agent", y, conc);
        Slot slot22 = new Slot("object", y1, conc);
        Slot slot3 = new Slot("a", z, conc);
        Slot slot4 = new Slot("l", w, conc);
        ArrayList<Slot> body1 = new ArrayList();
        body1.add(slot1);
        body1.add(slot11);  
        Body bodyy1=new Body(body1);
        ArrayList<Slot> body2 = new ArrayList();
        body2.add(slot2);
        body2.add(slot22);
        Body bodyy2=new Body(body2);
        ArrayList<Slot> body3 = new ArrayList();
        body3.add(slot3);
        Body bodyy3=new Body(body3);
        ArrayList<Slot> body4 = new ArrayList();
        body4.add(slot4);
        Body bodyy4=new Body(body4);
        Quantor qntr= new Quantor("[]",3,y);
        ArrayList<Quantor> qntrs=new ArrayList(); qntrs.add(qntr);
        AbstractSimpleFrame fr1 = new AbstractSimpleFrame("1name","pred", new ArrayList(), bodyy1);
        AbstractSimpleFrame fr2 = new AbstractSimpleFrame("2name","pred", qntrs, bodyy2);
        AbstractSimpleFrame fr3 = new AbstractSimpleFrame("3name","pred", new ArrayList(), bodyy3);
        AbstractSimpleFrame fr4 = new AbstractSimpleFrame("4name","pred2", new ArrayList(), bodyy4);
        AndFrame fr1AndFr2= new AndFrame("12", fr1, fr2);
        AndFrame fr3AndFr4= new AndFrame("34", fr3, fr4);
        AndFrame fr2AndFr3= new AndFrame("23", fr2, fr3);
        AndFrame fr1AndFr4= new AndFrame("1234", fr1, fr4);
        AndFrame fr123 = new AndFrame("123", fr1AndFr2, fr3);
        AndFrame fr1234Frame = new AndFrame("1234", fr1AndFr2, fr3AndFr4);
        AndFrame fr2334Frame = new AndFrame("1234", fr2AndFr3, fr3AndFr4);
        AndFrame fr2314Frame = new AndFrame("1234", fr2AndFr3, fr1AndFr4);
        
        OrFrame fr1OrFr2 = new OrFrame("1or2", fr1,fr2); 
        OrFrame fr123Or2 = new OrFrame("123or2", fr123,fr2); 
        System.out.println("fr1 ISA fr1 = " + fr1.ISA(fr1));
        System.out.println("--------------------------------------------");
        System.out.println("fr2 ISA fr2 = " + fr2.ISA(fr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr1AndFr2 ISA fr1 = " + fr1AndFr2.ISA(fr1));
        System.out.println("--------------------------------------------");
        System.out.println("fr1AndFr2 ISA fr2 = " + fr2.ISA(fr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr1AndFr2 ISA fr3 = " + fr1AndFr2.ISA(fr3));
        System.out.println("--------------------------------------------");
        System.out.println("fr3AndFr4 ISA fr3 = " + fr3AndFr4.ISA(fr3));
        System.out.println("--------------------------------------------");
        System.out.println("fr3AndFr4 ISA fr4 = " + fr3AndFr4.ISA(fr4));
        System.out.println("--------------------------------------------");
        System.out.println("fr3AndFr4 ISA fr1 = " + fr3AndFr4.ISA(fr1));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr3 = " + fr1234Frame.ISA(fr3));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr1 = " + fr1234Frame.ISA(fr1));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr1AndFr2 = " + fr1234Frame.ISA(fr1AndFr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr3AndFr4 = " + fr1234Frame.ISA(fr3AndFr4));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr2AndFr3 = " + fr1234Frame.ISA(fr2AndFr3));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr123 = " + fr1234Frame.ISA(fr123));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr2334 = " + fr1234Frame.ISA(fr2334Frame));
        System.out.println("--------------------------------------------");
        System.out.println("fr1234 ISA fr2314 = " + fr1234Frame.ISA(fr2314Frame));
        System.out.println("--------------------------------------------");
        System.out.println("fr2334 ISA fr1234 = " + fr2334Frame.ISA(fr1234Frame));
        System.out.println("--------------------------------------------");
        System.out.println("fr123 ISA fr4 = " + fr123.ISA(fr4));
        System.out.println("--------------------------------------------");
        System.out.println("fr123 ISA fr2 = " + fr123.ISA(fr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr123 ISA fr1234 = " + fr123.ISA(fr1234Frame));
        System.out.println("--------------------------------------------");
        System.out.println("1or2 ISA fr1 = " + fr1OrFr2.ISA(fr1));
        System.out.println("--------------------------------------------");
        System.out.println("1or2 ISA fr2 = " + fr1OrFr2.ISA(fr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr123Or2 ISA fr2 = " + fr123Or2.ISA(fr2));
        System.out.println("--------------------------------------------");
        System.out.println("fr123Or2 ISA fr1234Frame = " + fr123Or2.ISA(fr1234Frame));
        System.out.println("--------------------------------------------");
        System.out.println("fr123Or2 ISA fr123 = " + fr123Or2.ISA(fr123));
        System.out.println("--------------------------------------------");
        System.out.println("fr123 ISA fr123Or2 = " + fr123.ISA(fr123Or2));
        System.out.println("--------------------------------------------");
        
        
        
        */
        boolean expResult = false;
        boolean result = instance.ISA(arg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ISA method, of class AbstractSimpleFrame.
     */
    public void testISA_OrFrame() {
        System.out.println("ISA");
        OrFrame arg = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.ISA(arg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMergeFrame method, of class AbstractSimpleFrame.
     */
    public void testGetMergeFrame() {
        System.out.println("getMergeFrame");
        AbstractSimpleFrame arg = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        AbstractSimpleFrame expResult = null;
        AbstractSimpleFrame result = instance.getMergeFrame(arg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isContrary method, of class AbstractSimpleFrame.
     */
    public void testIsContrary() {
        System.out.println("isContrary");
        AbstractSimpleFrame arg = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.isContrary(arg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariableRoleAccordance method, of class AbstractSimpleFrame.
     */
    public void testGetVariableRoleAccordance() {
        System.out.println("getVariableRoleAccordance");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        HashMap<Variable, String> expResult = null;
        HashMap<Variable, String> result = instance.getVariableRoleAccordance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conceptIsUsed method, of class AbstractSimpleFrame.
     */
    public void testConceptIsUsed() {
        System.out.println("conceptIsUsed");
        Concept concept = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.conceptIsUsed(concept);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of constantIsUsed method, of class AbstractSimpleFrame.
     */
    public void testConstantIsUsed() {
        System.out.println("constantIsUsed");
        Constant constant = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.constantIsUsed(constant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extensionIsCorrect method, of class AbstractSimpleFrame.
     */
    public void testExtensionIsCorrect() {
        System.out.println("extensionIsCorrect");
        ArrayList<String> constants = null;
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.extensionIsCorrect(constants);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isClosed method, of class AbstractSimpleFrame.
     */
    public void testIsClosed() {
        System.out.println("isClosed");
        AbstractSimpleFrame instance = new AbstractSimpleFrame();
        boolean expResult = false;
        boolean result = instance.isClosed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
