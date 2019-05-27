/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon Asholt Norup
 */
public class CustomerTest {

    private final Customer cust;

    public CustomerTest() {

        this.cust = new Customer(0, "Cake", "Jake", "Wollabing 22", "2800", "derp", "39393939", "iheard@youlike.trucks");
    }

    /**
     * Test of getName method of Customer Class
     */
    @Test
    public void testGetName() {
        String actual = cust.getFirstName() + " " + cust.getLastName();
        assertEquals("Cake Jake", actual);
    }

     /**
     * Test of attempt at creating an invalid Customer Object
     */
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCustomerValues() {
        Customer illegalCust = new Customer(0, "cake", "Jake", "No", "293", "derp", "3939393912", "Iheard.youlike.trucks");
        illegalCust.getAddress();
    }

    /**
     * Test of getAddress method of Customer Class
     */
    @Test
    public void testGetAddress() {
        assertEquals("Wollabing 22", cust.getAddress());
    }

    /**
     * Test of getZipcode method of Customer Class
     */
    @Test
    public void testGetZipcode() {
        assertEquals("2800", cust.getZipcode());
    }

    /**
     * Test of getCity method method of Customer Class
     */
    @Test
    public void testGetCity() {
         assertEquals("derp", cust.getCity());
    }

    /**
     * Test of getPhone method of Customer Class
     */
    @Test
    public void testGetPhone() {
       assertEquals("39393939", cust.getPhone());
    }

    /**
     * Test of getEmail method of Customer Class
     */
    @Test
    public void testGetEmail01() {
        assertEquals("iheard@youlike.trucks", cust.getEmail());
    }
}
