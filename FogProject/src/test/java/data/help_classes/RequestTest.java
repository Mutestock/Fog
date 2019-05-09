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
    private final Customer customer;
    
    public RequestTest() {
        Roof roof = new Roof(1, "Plastic", 0);
        Shed shed = new Shed(1, 190, 190, "Plastic");
        carport = new Carport(1, 500, 250, roof, shed);
        customer = new Customer(1, "Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
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
        Request request = new Request(1, null, "Blablabla", carport, customer);
    }  

    /**
     * Test of getComments method, of class Request.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetComments() {
        Request request = new Request(1, LocalDateTime.now(), null, carport, customer);
    }
    
}
