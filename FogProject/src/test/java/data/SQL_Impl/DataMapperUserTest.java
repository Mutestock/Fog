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
        user = new User("theuser", "thepassword");
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
     * Test of getUser method, of class DataMapperUser.
     */
    @Test
    public void testCreateNGetUser() throws Exception {
        System.out.println("getUser");
        DataMapperUser instance = new DataMapperUser(true);
        instance.addUser(user);
        User expResult = user;
        User result = instance.getUser(user.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        instance.deleteUser(user);
    }

}
