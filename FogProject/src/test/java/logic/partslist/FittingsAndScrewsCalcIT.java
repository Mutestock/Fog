package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import data.help_classes.Roof;
import data.help_classes.Shed;
import java.util.LinkedList;
import java.util.logging.Level;
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
public class FittingsAndScrewsCalcIT {

    Roof roof;
    Shed shed;
    LinkedList<Part> boM;
    Carport carport;

    public FittingsAndScrewsCalcIT() {
        this.roof = new Roof(3, "OrphanTears", 15);
        this.shed = new Shed(3, 250, 250, "Screaming faces");
        this.carport = new Carport(3, 450, 450, roof, shed);
        this.boM = WoodCalc.calculateParts(carport);
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
    //Tests if there is negative values it throws an error 
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeValues() {
        Carport tcarport = new Carport(0, -200, -300, roof, shed);
        FittingsAndScrewsCalc.calculateParts(tcarport, boM);
    }
    // May need to be removed as the one that throws the error is Carport
    @Test(expected = IllegalArgumentException.class)
    public void testNullCarportValues() {

        Carport tcarport = new Carport(0, 0, 0, null, null);
        FittingsAndScrewsCalc.calculateParts(tcarport, boM);
    }
    
//    @Test(expected = IllegalArgumentException.class)
//    public void testNullBoMValues() {
//        Carport tcarport = new Carport(0, 0, 0, null, null);
//        boM = WoodCalc.calculateParts(tcarport);;
//        FittingsAndScrewsCalc.calculateParts(carport, boM);
//    }
    //Tests if any changes as caused the list to turn up empty
     @Test
    public void testIfEmpty() {
            LinkedList<Part> cBoM = FittingsAndScrewsCalc.calculateParts(carport, boM);
            assertTrue(!cBoM.isEmpty());
        }
    //Tests if all the parts that are expected to be there are there.
    @Test
    public void testListSize() {
        LinkedList<Part> cBoM = FittingsAndScrewsCalc.calculateParts(carport, boM);
        assertTrue(cBoM.size() == 12);
    }

}
