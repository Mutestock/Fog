package data.SQL_Impl;

import data.help_classes.User;
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
public class DataMapperUserTest {

    private User user;

    public DataMapperUserTest() {
        user = new User("theuser", "password");
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
//     * Test of addUser method, of class DataMapperUser.
//     */
//    @Test
//    public void testAddUser() throws Exception {
//        System.out.println("addUser");
//        User user = new User("this","");
//        DataMapperUser instance = new DataMapperUser();
//        instance.addUser(user);
//        // TODO review the generated test code and remove the default call to fail.
//    }
    /**
     * Test of getUser method, of class DataMapperUser.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String username = "";
        DataMapperUser instance = new DataMapperUser();
        instance.addUser(user);
        User expResult = user;
        User result = instance.getUser(username);
        assertEquals(expResult, result);
    }

}
