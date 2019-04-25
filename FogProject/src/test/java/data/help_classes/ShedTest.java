/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class ShedTest {
    
    public ShedTest() {
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

    @Test (expected = IllegalArgumentException.class)
    public void testInitShed01() {
        Shed failShed01 = new Shed(0, 210, "Plastic");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitShed02() {
        Shed failShed02 = new Shed(300, -200, "Plastic");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitShed03() {
        Shed failShed03 = new Shed(300, 210, "");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInitShed04() {
        Shed failShed04 = new Shed(300, 210, null);
    }
    
}
