/*
 */
package data.help_classes;

import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class RequestTest {
    
    private final Roof roof;
    private final Shed shed;
    private final Carport carport;
    private final Customer customer;
    private final Request request;
    
    public RequestTest() {
        this.roof = new Roof(0, "Plastic", 0);
        this.shed = new Shed(0, 190, 190, "Plastic");
        this.carport = new Carport(0, 500, 250, roof, shed);
        this.customer = new Customer(0, "Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
        this.request = new Request(0, LocalDateTime.now(), "foo", carport, customer);
    }
    /**
     * Test of attempt at creating an invalid request object.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testIllegalRequest() {
        Request request = new Request(1, null, "Blablabla", carport, customer);
    }  

    /**
     * Test of getComments method of Request Class
     */
    @Test
    public void testGetComments() {
       assertEquals("foo", request.getComments());
    }
    
      /**
     * Test of getFirstName method of Customer Class through request object.
     */
    
    @Test
    public void testCustomerNameThroughRequest() {
        assertEquals("Derpman", request.getCustomer().getFirstName());
    }
    
     /**
     * Test of getID method of Request class.
     */
    
    @Test
    public void testGetID() {
        assertEquals(0,request.getId());
    }
    
    /**
     * Test of hasRecievedOffer method of Request class.
     */
    
    @Test
    public void testGetHasRecievedOrder() {
        assertEquals(false, request.hasReceivedOffer());
    }
    
    /**
     * Test of getHeight method of Carport class through Request Object.
     */
    @Test
    public void testGetCarportHeightThroughRequest() {
        assertEquals(210, request.getCarport().getHeight());
    }
}
