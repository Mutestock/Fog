/*
 */
package data.help_classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon Asholt Norup
 */
public class RoofTest {

    private final Roof roof;
    private final Roof roofFlat;
    private final Carport carport;

    public RoofTest() {

        this.roof = new Roof(0, "Vanilla Twilight", 35);
        this.roofFlat = new Roof(0, "Vanilla Twilight", 0);
        this.carport = new Carport(0, 500, 250, roof, null);
    }

    /**
     * Test of getRaised method of Roof class.
     */
    @Test
    public void testGetRaised() {
        assertEquals(true, roof.getRaised());
        assertEquals(false, roofFlat.getRaised());
    }

    /**
     * Test of getID method of Roof class.
     */
    @Test
    public void testGetID() {
        assertEquals(0, roof.getId());
    }

    /**
     * Test of getSlope method of Roof class.
     */
    @Test
    public void testGetSlope() {
        assertEquals(35, roof.getSlope());
    }

    /**
     * Test of getType method of Roof class.
     */
    @Test
    public void testType() {
        assertEquals("Vanilla Twilight", roof.getType());
    }

    /**
     * Test of getHeight method of Roof class.
     */
    @Test
    public void testHeight() {
        assertEquals(88, roof.getRoofHeight(carport));
    }

    /**
     * Test of attempt at creating an invalid Roof object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRoof1() {
        Roof illegalRoof = new Roof(1, "", 25);
    }

    /**
     * Test of attempt at creating an invalid Roof object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testllegalRoof2() {
        Roof illegalRoof = new Roof(1, null, 25);
    }

    /**
     * Test of attempt at creating an invalid Roof object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testllegalRoof3() {
        Roof illegalRoof = new Roof(1, "Eternittag B7 - Sort", -22);
    }

}
