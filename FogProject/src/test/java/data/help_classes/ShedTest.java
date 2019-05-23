/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simon Asholt Norup
 */
public class ShedTest {

    private final Shed shed;

    public ShedTest() {
        this.shed = new Shed(0, 350, 450, "Vanilla Twilight");
    }

    /**
     * Test of getID method of Shed class.
     */
    public void testGetID() {
        assertEquals(0, shed.getId());
    }

    /**
     * Test of getLength method of Shed class.
     */
    public void testGetLength() {
        assertEquals(350, shed.getLength());
    }

    /**
     * Test of getWidth method of Shed class.
     */
    public void testGetWidth() {
        assertEquals(450, shed.getWidth());
    }

    /**
     * Test of getWallCoverings method of Shed class.
     */
    public void testGetWallCoverings() {
        assertEquals("Vanilla Twilight", shed.getWallCoverings());
    }

    /**
     * Test of attempt at creating an invalid Shed object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalShed1() {
        Shed illegalShed = new Shed(1, 0, 210, "Plastic");
    }

    /**
     * Test of attempt at creating an invalid Shed object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalShed2() {
        Shed illegalShed = new Shed(1, 300, -200, "Plastic");
    }

    /**
     * Test of attempt at creating an invalid Shed object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalShed3() {
        Shed illegalShed = new Shed(1, 300, 210, "");
    }

    /**
     * Test of attempt at creating an invalid Shed object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalShed4() {
        Shed illegalShed = new Shed(1, 300, 210, null);
    }

}
