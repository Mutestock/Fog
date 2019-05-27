package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import data.help_classes.Roof;
import data.help_classes.Shed;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gamer
 */
public class WoodCalcTest {

    private final Roof roof;
    private final Shed shed;
    private final Carport carport;
    
    public WoodCalcTest() {
        this.roof = new Roof(3, "OrphanTears", 15);
        this.shed = new Shed(3, 250, 250, "Screaming faces");
        this.carport = new Carport(3, 450, 450, roof, shed);
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
     * Test of calculateParts method, of class WoodCalc.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeExpressions() {
        Carport carport = new Carport(-1,-1,-1,roof,shed);
    }
    
    @Test
    public void testIfEmpty() {
        List test = WoodCalc.calculateParts(carport);
        assertTrue(!test.isEmpty());
    }
    
    @Test
    public void testListSize() {
        List test = WoodCalc.calculateParts(carport);
        assertTrue(test.size() == 15);
    }
    
    
}
