package data.SQL_Impl;

import data.help_classes.Carport;
import data.help_classes.Request;
import data.help_classes.Roof;
import data.help_classes.Shed;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Bjørnvad
 */
public class DataMapperCustomerTest {

    public DataMapperCustomerTest() {
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
     * Test of createRequest method, of class DataMapperCustomer.
     */
    @Test
    public void testCreateRequest() throws Exception {
        System.out.println("createRequest");
        Request request = null;
        DataMapperCustomer instance = new DataMapperCustomer();
        instance.createRequest(request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readShedId method, of class DataMapperCustomer.
     */
    @Test
    public void testReadShedId() {
        System.out.println("readShedId");
        Shed shed = null;
        DataMapperCustomer instance = new DataMapperCustomer();
        int expResult = 0;
        int result = instance.readShedId(shed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readRoofId method, of class DataMapperCustomer.
     */
    @Test
    public void testReadRoofId() {
        System.out.println("readRoofId");
        Roof roof = null;
        DataMapperCustomer instance = new DataMapperCustomer();
        int expResult = 0;
        int result = instance.readRoofId(roof);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readCarportId method, of class DataMapperCustomer.
     */
    @Test
    public void testReadCarportId() {
        System.out.println("readCarportId");
        Carport carport = null;
        DataMapperCustomer instance = new DataMapperCustomer();
        int expResult = 0;
        int result = instance.readCarportId(carport);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
