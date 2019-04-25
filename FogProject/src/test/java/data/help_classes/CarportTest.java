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
        this.shed = new Shed(210, 240, "Plastic");
        this.roof = new Roof("Eternittag B6 - Teglrød", 25);
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
        Carport carport = new Carport(200, 300, 250, roof, shed);
    }

    /**
     * Test of getWidth method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetWidth() {
        Carport carport = new Carport(300, 0, 250, roof, shed);
    }

    /**
     * Test of getHeight method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetHeight() {
        Carport carport = new Carport(300, 300, -200, roof, shed);
    }

    /**
     * Test of getRoof method, of class Carport.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetRoof() {
        Carport carport = new Carport(300, 300, 250, null, shed);
    }
    
}
