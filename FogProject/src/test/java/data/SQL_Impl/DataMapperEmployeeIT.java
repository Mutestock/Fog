package data.SQL_Impl;

import data.help_classes.Carport;
import data.help_classes.Customer;
import data.help_classes.Offer;
import data.help_classes.Request;
import data.help_classes.Roof;
import data.help_classes.Shed;
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
public class DataMapperEmployeeIT {

    private LocalDateTime time;
    private Roof roof;
    private Shed shed;
    private Carport carport;
    private Request request;
    private Customer customer;
    private Offer offer;

    public DataMapperEmployeeIT() {
        this.time = LocalDateTime.now();
        this.roof = new Roof(-1, "plasttrapezplader", 0);
        this.shed = new Shed(-1, 270, 270, "bones");
        this.carport = new Carport(-1, 420, 420, roof, shed);
        this.customer = new Customer(-1, "Lars", "Larsen", "Ostevej 21", "4588", "Byen", "45781245", "email@email.com");
        this.request = new Request(-1, time, "cheesedipper", carport, customer);
        this.offer = new Offer(-1, time, 9999, 1111, request);
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
        DataMapperEmployee instance = new DataMapperEmployee(true);

        LinkedList<Request> result = instance.readAllRequests();
        assertTrue(result.size() >= 1);

    }

    /**
     * Test of readRequest method, of class DataMapperEmployee.
     */
//    @Test
//    public void testReadRequest() throws Exception {
//        try {
//            System.out.println("readRequest");
//            int id = 0;
//            DataMapperEmployee instance = new DataMapperEmployee(true);
//            Request expResult = null;
//            Request result = instance.readRequest(id);
//            assertEquals(expResult, result);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            fail("Something went wrong");
//        }
//    }

    /**
     * Test of createOffer method, of class DataMapperEmployee.
     */
    @Test
    public void testCreateOffer() throws Exception {
       try {
        System.out.println("createOffer");
        Offer offer = this.offer;
        DataMapperEmployee instance = new DataMapperEmployee();
        instance.createOffer(offer);
       } catch (Exception ex) {
            ex.printStackTrace();
            fail("Something went wrong");
        }
    }

    /**
     * Test of readOffer method, of class DataMapperEmployee.
     */
//    @Test
//    public void testReadOffer() throws Exception {
//        System.out.println("readOffer");
//        int requestID = 0;
//        DataMapperEmployee instance = new DataMapperEmployee();
//        Offer expResult = null;
//        Offer result = instance.readOffer(requestID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
