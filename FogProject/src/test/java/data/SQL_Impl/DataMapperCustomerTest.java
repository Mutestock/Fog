package data.SQL_Impl;

import data.help_classes.Carport;
import data.help_classes.Customer;
import data.help_classes.Offer;
import data.help_classes.Request;
import data.help_classes.Roof;
import data.help_classes.Shed;
import java.time.LocalDateTime;
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
    private LocalDateTime time;
    private Roof roof;
    private Shed shed;
    private Carport carport;
    private Request request;
    private Customer customer;
    private Offer offer;
    public DataMapperCustomerTest() {
        this.time = LocalDateTime.now();
        this.roof = new Roof(-1,"plasttrapezplader",0);
        this.shed = new Shed(-1,270,270,"bones");
        this.carport = new Carport(-1,420,420,roof,shed);
        this.customer = new Customer(-1,"Lars","Larsen","Ostevej 21","4588","Byen","45781245","email@email.com");
        this.request = new Request(-1,time,"cheesedipper",carport,customer);
        //this.offer = new Offer(-1, time, 9999, 1111, request);
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
    public void testCreateRequest() {
        try{
        System.out.println("createRequest");
        Request request = this.request;
        DataMapperCustomer instance = new DataMapperCustomer(true);
        instance.createRequest(request);        
        assertTrue(true);
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Something went wrong");
        }
    }
    
}
