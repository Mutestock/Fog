package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Bjørnvad
 */
public class FittingsAndScrewsCalcIT {
    
    public FittingsAndScrewsCalcIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateParts method, of class FittingsAndScrewsCalc.
     */
    @Test
    public void testCalculateParts() {
        System.out.println("calculateParts");
        Carport carport = null;
        LinkedList<Part> boM = null;
        LinkedList<Part> expResult = null;
        LinkedList<Part> result = FittingsAndScrewsCalc.calculateParts(carport, boM);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
