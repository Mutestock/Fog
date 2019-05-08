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
public class OfferTest {
    
    private final Request request;
    
    public OfferTest() {
        Roof roof = new Roof(1, "Plastic", 0);
        Shed shed = new Shed(1, 190, 200, "Plastic");
        Carport carport = new Carport(1, 250, 250, roof, shed);
        Customer customer = new Customer(1, "Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
        request = new Request(1, LocalDateTime.now(), "Blblabla.", carport, customer);
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
        Offer offer = new Offer(1, null, 1000.0, 200.0, request);
    }

    /**
     * Test of getPrice method, of class Offer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetPrice() {
        Offer offer = new Offer(1, LocalDateTime.now(), -1.0, 200.0, request);
    }

    /**
     * Test of getShipping_costs method, of class Offer.
     */
//    @Test (expected = IllegalArgumentException.class)
//    public void testGetShippingCosts() {
//        Offer offer = new Offer(1, LocalDateTime.now(), 2000.0, -1.0, request);
//    }
    
}
