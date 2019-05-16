package data.SQL_Impl;

import data.help_classes.Offer;
import data.help_classes.Request;
import java.util.LinkedList;
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
public class DataMapperEmployeeIT {
    
    public DataMapperEmployeeIT() {
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
     * Test of readAllRequests method, of class DataMapperEmployee.
     */
    @Test
    public void testReadAllRequests() throws Exception {
        System.out.println("readAllRequests");
        DataMapperEmployee instance = new DataMapperEmployee();
        LinkedList<Request> expResult = null;
        LinkedList<Request> result = instance.readAllRequests();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readRequest method, of class DataMapperEmployee.
     */
    @Test
    public void testReadRequest() throws Exception {
        System.out.println("readRequest");
        int id = 0;
        DataMapperEmployee instance = new DataMapperEmployee();
        Request expResult = null;
        Request result = instance.readRequest(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOffer method, of class DataMapperEmployee.
     */
    @Test
    public void testCreateOffer() throws Exception {
        System.out.println("createOffer");
        Offer offer = null;
        DataMapperEmployee instance = new DataMapperEmployee();
        instance.createOffer(offer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readOffer method, of class DataMapperEmployee.
     */
    @Test
    public void testReadOffer() throws Exception {
        System.out.println("readOffer");
        int requestID = 0;
        DataMapperEmployee instance = new DataMapperEmployee();
        Offer expResult = null;
        Offer result = instance.readOffer(requestID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
