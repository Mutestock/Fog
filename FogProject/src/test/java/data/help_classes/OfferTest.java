/*
 */
package data.help_classes;

import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class OfferTest {

    private final Request request;
    private final Roof roof;
    private final Shed shed;
    private final Carport carport;
    private final Customer customer;
    private final Offer offer;

    public OfferTest() {
        this.roof = new Roof(0, "Plastic", 0);
        this.shed = new Shed(0, 190, 190, "Plastic");
        this.carport = new Carport(0, 500, 250, roof, shed);
        this.customer = new Customer(1, "Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
        this.request = new Request(1, LocalDateTime.now(), "Blblabla.", carport, customer);
        this.offer = new Offer(0, LocalDateTime.now(), 1000.0, 200.0, request);
    }
    /**
     * Test of getSent method, of class Offer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalOffer() {
        Offer offer = new Offer(1, LocalDateTime.now(), -1.0, 200.0, request);
    }

    /**
     * Test of getPrice method of Offer class
     */
    @Test
    public void testGetPrice() {
        assertEquals(1000.0, offer.getPrice(), 0);
    }

     /**
     * Test of getID method of Offer class.
     */
    
    @Test
    public void testGetID() {
        assertEquals(0, offer.getId());
    }

     /**
     * Test of getShippingCosts method of Offer class
     */
    
    @Test
    public void testGetShippingCosts() {
        assertEquals(200, offer.getShippingCosts(), 0);
    }

     /**
     * Test of GetFirstName method of Customer class through Offer Object.
     */
    
    
    @Test
    public void testGetCustFirstNameThroughOffer() {
        assertEquals("Derpman", offer.getRequest().getCustomer().getFirstName());
    }

     /**
     * Test of GetSlope method of Roof class through Offer.
     */
    
    @Test
    public void testGetRoofSlopeThroughOffer() {
        assertEquals(0, offer.getRequest().getCarport().getRoof().getSlope());
    }

     /**
     * Test of GetCarportWidth method of Carport class through Offer.
     */
    
    @Test
    public void testGetCarportWidthThroughOffer() {
        assertEquals(250, offer.getRequest().getCarport().getWidth());
    }
}
