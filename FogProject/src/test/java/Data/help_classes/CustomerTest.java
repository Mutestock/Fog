/*
 */
package Data.help_classes;

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
public class CustomerTest {
    
    public CustomerTest() {
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
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName01() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
        String expected = instance.getFirstName() + " " + instance.getLastName();
        assertEquals(expected, instance.getFullName());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetName02() {
        Customer instance = new Customer("", "Haggleman", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetName03() {
        Customer instance = new Customer("Derpface", null, "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.herb");
    }

    /**
     * Test of getAddress method, of class Customer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetAddress() {
        Customer instance = new Customer("Derpman", "Haggleface", "SwerpStreet22", "2800", "San Simon", "12345678", "derp@snerp.herb");
    }

    /**
     * Test of getZipcode method, of class Customer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetZipcode() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "A45h", "San Simon", "12345678", "derp@snerp.herb");
    }

    /**
     * Test of getCity method, of class Customer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetCity() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "1234", "12345678", "derp@snerp.herb");
    }

    /**
     * Test of getPhone method, of class Customer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetPhone() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "aaaf6666", "derp@snerp.herb");
    }

    /**
     * Test of getEmail method, of class Customer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetEmail01() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp @snerp.herb");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetEmail02() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "@snerp.herb");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetEmail03() {
        Customer instance = new Customer("Derpman", "Haggleface", "Swerp Street 22", "2800", "San Simon", "12345678", "derp@snerp.");
    }
    
}
