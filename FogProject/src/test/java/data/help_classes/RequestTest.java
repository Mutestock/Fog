/*
 */
package data.help_classes;

import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class RequestTest {
    
    private final Carport carport;
    
    public RequestTest() {
        Roof roof = new Roof("Plastic", 0);
        Shed shed = new Shed(190, 200, "Plastic");
        carport = new Carport(250, 250, 250, roof, shed);
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
     * Test of getSent method, of class Offer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetSent() {
        Request request = new Request(null, "Blablabla", carport);
    }

    /**
     * Test of getComments method, of class Request.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetComments() {
        Request request = new Request(LocalDateTime.now(), null, carport);
    }
    
}
