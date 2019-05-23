/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class CarportTest {

    private final Shed shed;
    private final Roof roof;

    public CarportTest() {
        this.roof = new Roof(1, "Cake", 35);
        this.shed = new Shed(0, 250, 250, "vanilla twilight");
    }

    /**
     * Test of getLength method of Carport class.
     */
    @Test
    public void testGetLength() {
//        Carport carport = new Carport(0, 200, 300, roof, shed);
        Carport c = new Carport(0, 430, 600, roof, shed);
        int length = c.getLength();
        Assert.assertEquals(length, 430);
    }

    /**
     * Test of getWidth method of Carport class.
     */
    @Test
    public void testGetWidth() {
        Carport c = new Carport(0, 430, 600, roof, shed);
        int width = c.getWidth();
        Assert.assertEquals(width, 600);
    }

     /**
     * Test of getRoofSlope method of Roof class through Carport object.
     */
    
    @Test
    public void testGetRoof() {
        Carport c = new Carport(0, 430, 600, roof, shed);
        int roofSlope = c.getRoof().getSlope();
        Assert.assertEquals(roofSlope, 35);
    }

    /**
     * Test of attempt of creating an invalid Carport
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalValues() {
        Carport carport = new Carport(1, 300, 300, null, shed);
    }

}
