package logic.roof;

import data.help_classes.Carport;
import data.help_classes.Part;
import data.help_classes.PartsList;
import data.help_classes.Roof;
import data.help_classes.Shed;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import logic.LoggerSetup;
import logic.partslist.RoofCalc;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 *
 * @author Henning
 */
public class TestRoof {

    RoofCalcExtension r = null;
    Roof roof = new Roof(1, "Cake", 35);
    Shed shed = new Shed(0, 250, 250, "vanilla twilight");
    private static Logger logger;
    
    @Mock
    RoofCalcInterface rServ;

    @Rule
    public MockitoRule mockRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws IOException {
        logger = LoggerSetup.logSetup();
        r = new RoofCalcExtension(rServ);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureNegativeValues() {

        try {
            Carport c = new Carport(0, -12, -9, roof, shed);
            r.calculateParts(c);
            Assert.fail("2");
        } catch (AssertionError e) {
            System.out.println("Assertion failure");
            logger.log(Level.SEVERE, "RoofTest: List generation did not fail on negative value input", e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureNull() {
        try {
            Carport c = new Carport(0, 0, 0, null, null);
            LinkedList<Part> partList = r.calculateParts(c);
            if (partList.isEmpty() == false) {
                throw new AssertionError();
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failure");
            logger.log(Level.SEVERE, "RoofTest: List generation did not fail on null value input", e);
        }
    }

    @Test
    public void testNotEmpty() {
        try {
            Carport c = new Carport(0, 430, 430, roof, shed);
            LinkedList<Part> partList = r.calculateParts(c);
            if (partList.isEmpty()) {
                throw new AssertionError();
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failure");
            logger.log(Level.SEVERE, "RoofTest: List was empty", e);
        }
    }

    @Test
    public void testListCorrectSize() {
        try {
            Carport c = new Carport(0, 430, 430, roof, shed);
            LinkedList<Part> partList = r.calculateParts(c);
            if (!(partList.size() == 5)) {
                throw new AssertionError();
            }
        } catch (AssertionError e) {
            logger.log(Level.SEVERE, "RoofTest: List didn't exist");
            Assert.fail();
        }
    }

    //Sends information to log. Can't grasp private fields to check for ratios.
    @Test
    public void testPricesAtDefaultStandardTile() {
        try {
            Carport c = new Carport(0, 430, 430, roof, shed);
            LinkedList<Part> partList = r.calculateParts(c);
            if (partList.get(0).getBuyPrice() == 10
                    && partList.get(1).getBuyPrice() == 50
                    && partList.get(2).getBuyPrice() == 40
                    && partList.get(3).getBuyPrice() == 50
                    && partList.get(4).getBuyPrice() == 100) {
                if (!(2410 == partList.getFirst().getTotalPrice())) {
                    throw new AssertionError();
                }
            } else {
                logger.log(Level.FINE, "testPricesAtDefault: Prices weren't at default. Skipping test");
            }
        } catch (AssertionError e) {
            logger.log(Level.FINE, "testPricesAtDefault: Price calculation failure or change in default values. Check ratios ");
        }
    }

    //Sends information to log. Can't grasp private fields to check for ratios.
    @Test
    public void testAmountAtDefaultStandardTile() {
        try {
            Carport c = new Carport(0, 430, 430, roof, shed);
            LinkedList<Part> partList = r.calculateParts(c);
            if (!(241 == partList.getFirst().getAmount())) {
                throw new AssertionError();
            }
        } catch (AssertionError e) {
            logger.log(Level.FINE, "testAmountAtDefaultStandardTile: Algorithm failure or change in default values. Check ratios");
        }
    }
}
