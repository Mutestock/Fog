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
    
    private final Carport carport;
    
    public OfferTest() {
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
        Offer offer = new Offer(null, 1000.0, 200.0, carport);
    }

    /**
     * Test of getPrice method, of class Offer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetPrice() {
        Offer offer = new Offer(LocalDateTime.now(), -1.0, 200.0, carport);
    }

    /**
     * Test of getShipping_costs method, of class Offer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetShippingCosts() {
        Offer offer = new Offer(LocalDateTime.now(), 2000.0, -1.0, carport);
    }
    
}
