/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon Asholt Norup
 */
public class RoofTest {
    
    public RoofTest() {
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

    @Test
    public void testInitRoof01() {
        Roof correctRoof01 = new Roof("Eternittag B6 - Teglrød", 25);
        Roof correctRoof02 = new Roof("Eternittag B7 - Sort", 0);
        assertEquals(true, correctRoof01.getRaised());
        assertEquals(false, correctRoof02.getRaised());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitRoof02() {
        Roof wrongRoof01 = new Roof("", 25);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitRoof03() {
        Roof wrongRoof02 = new Roof(null, 25);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitRoof04() {
        Roof wrongRoof03 = new Roof("Eternittag B7 - Sort", -22);
    }
    
}
