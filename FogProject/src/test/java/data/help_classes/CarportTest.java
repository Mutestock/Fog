/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class CarportTest {
    
    private final Shed shed;
    private final Roof roof;

    public CarportTest() {
        this.shed = new Shed(1, 210, 240, "Plastic");
        this.roof = new Roof(1, "Eternittag B6 - Teglrød", 25);
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
     * Test of getLength method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetLength() {
        Carport carport = new Carport(1, 200, 300, roof, shed);
    }

    /**
     * Test of getWidth method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetWidth() {
        Carport carport = new Carport(1, 300, 0, roof, shed);
    }

    /**
     * Test of getRoof method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetRoof() {
        Carport carport = new Carport(1, 300, 300, null, shed);
    }
    
}
