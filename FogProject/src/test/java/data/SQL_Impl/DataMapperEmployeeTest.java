package data.SQL_Impl;

import data.help_classes.*;
import java.time.LocalDateTime;
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
public class DataMapperEmployeeTest {
    private LocalDateTime time;
    private Roof roof;
    private Shed shed;
    private Carport carport;
    private Request request;
    private Offer offer;
    public DataMapperEmployeeTest() {
        time = LocalDateTime.now();
        roof = new Roof(-1,"plasttrapezplader",0);
        shed = new Shed(-1,270,270,"bones");
        carport = new Carport(-1,420,420,roof,shed);
        //offer = new Offer(-1, time, 9999, 1111, request);
        
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

//    /**
//     * Test of readRequestsIncomplete method, of class DataMapperEmployee.
//     */
//    @Test
//    public void testReadRequestsIncomplete() throws Exception {
//        System.out.println("readRequestsIncomplete");
//        DataMapperEmployee instance = new DataMapperEmployee();
//        LinkedList<Request> expResult = null;
//        LinkedList<Request> result = instance.readRequestsIncomplete();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of readRequestsComplete method, of class DataMapperEmployee.
//     */
//    @Test
//    public void testReadRequestsComplete() throws Exception {
//        System.out.println("readRequestsComplete");
//        DataMapperEmployee instance = new DataMapperEmployee();
//        LinkedList<Request> expResult = null;
//        LinkedList<Request> result = instance.readRequestsComplete();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of readRequestsUnread method, of class DataMapperEmployee.
//     */
//    @Test
//    public void testReadRequestsUnread() throws Exception {
//        System.out.println("readRequestsUnread");
//        DataMapperEmployee instance = new DataMapperEmployee();
//        LinkedList<Request> expResult = null;
//        LinkedList<Request> result = instance.readRequestsUnread();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

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
     * Test of readOffer method, of class DataMapperEmployee.
     */
    @Test
    public void testReadOffer() throws Exception {
        System.out.println("readOffer");
        int requestID = 0;
        DataMapperEmployee instance = new DataMapperEmployee();
        Offer expResult = new Offer(-1, time, 9999, 1111, request);
        instance.createOffer(expResult);
        Offer result = instance.readOffer(requestID);
        assertEquals(expResult, result);
    }
    
}
