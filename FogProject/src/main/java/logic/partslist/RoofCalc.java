package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Henning
 */
public class RoofCalc {

    //Considered as constant values.
    //Please find correct terminology
    private static final int topBattenSlot = 8;
    private static final int roofTileMatPacks = 2;

    public static LinkedList<Part> calculateParts(Carport carport) {
        LinkedList<Part> parts = new LinkedList();

        if (carport.getRoof().getRaised() == true) {
            parts.add(calcTileCount(carport));
            parts.add(calcRidgeTile(carport));
            parts.add(calcRidgeTileSlot(carport));
            Part topBattenSlotPart = new Part("B & C Toplægte holder", topBattenSlot, "stk", "monteres på toppen af spæret (til toplægte)", 32.0);
            Part roofTileMatPacksPart = new Part("B & C tagstens bindere & nakkekroge", roofTileMatPacks, "pk.", "til montering af tagsten, alle ydersten + hver anden fastgøres", 69);

            parts.add(topBattenSlotPart);
            parts.add(roofTileMatPacksPart);
        } else if (calcFlatRoofPlates600(carport).getAmount() == 0) {
            parts.add(calcFlatRoofPlates360(carport));
        } else {
            parts.add(calcFlatRoofPlates600(carport));
            parts.add(calcFlatRoofPlates360(carport));

        }

        return parts;
    }

    private static Part calcTileCount(Carport carport) {

//        step 1: width / 2 = b
//        step 2: b / cos(A) = c
//        step 3: c*2 = modified width
//        step 4: modified width * length / ratio = brick count
//        step 1: 360 / 2 = 180
//        step 2: 180 / cos(20) = 191,55
//        step 3: 191 * 2 = 383,10
//        step 4: 386 * 730 / 300 = 939.27
//        Ratio = 939.27
        int result = 0;
        double preCasted = 0.0;
        preCasted = carport.getWidth() / 2;
        double toDegrees = Math.cos(Math.toRadians((double) carport.getRoof().getSlope()));
        preCasted = preCasted / toDegrees;
        preCasted = preCasted * 2;
        preCasted = preCasted * (double) carport.getLength() / 939.27;
        result = (int) Math.ceil(preCasted);
        return new Part("B & C Dobbelt -s sort", result, "stk", "monteres på taglægter 6 rækker af 24 sten på hver side af taget", 4.5);
    }

    private static Part calcRidgeTile(Carport carport) {
        //Non-dependant on width
        //Length 730 / 21 = 34.7 cm

        int result = (int) Math.ceil((carport.getLength() / 34.7));
        return new Part("B & C Rygsten sort", result, "stk", "monteres på toplægte med medfølgende beslag se tagstens vejledning", 100);
    }

    private static Part calcRidgeTileSlot(Carport carport) {
        //Containers for ridgetiles. Same proportions.
        int result = (int) Math.ceil((carport.getLength() / 34.7));
        return new Part("B & C rygstensbeslag", result, "stk", "Til montering af rygsten", 75);
    }

    private static Part calcFlatRoofPlates600(Carport carport) {
        /*Rules:
        
        Modulus
        
        plates must overlap with atleast 2 waves
        Long plates will overlap the small ones
        In length, plates must overlap with atleast 20cm
        Plate backside must go 5cm over the edge
        
        Length Varies:
        
        600
        480
        360
        300
        240
        
        estimate desired length variations from measurements
        
        Width is always 109
        
        example Dimensions:
        
        600x780
        
        Length:
        
        
        
        at the current point in time anything over 600 will result in acquiring an extra 360 plate.
        
        Width of single plate = 109
        
        600 = width of example
    
        trial and error on site : 
        100 cm ~> 2 plates
        95cm ~> 1 plate
        98cm ~> 2 plates
        97cm ~> 1 plate
        195 cm ~> 3 plates
        185 cm ~> 2 plates
        190 cm ~> 2 plates
        192 -> 2 plates
        194 / 2 plates = 97 cm
        
        WARNING:
        ===
        as per the assignment schematics the width of the schematics must be 100cm or over post waves to fill in the 6 plates defined in the example.
        The ACTUAL size of the is 97cm. This means, that the schematics in the assignment should use 7 plates and not 6.
        
        This means that the length of the waves must tbe set to max 4.5cm instead of the actual 6cm
        
        Plate size set to 100 to fit with assignment details.
        
        Add to report!!!
        ===
        
        WARNING:
        ====
        Actual site uses more variations than 109x600 & 109x360
        
        
        ====
        
        
        
        600cm / 100 = 6 plates
        
        
        194 / 2 = 
        
        200 m to 202 m ~> +2 plates
        
        1 plate +
        
        (Plate - 20) % 
        
        
        
        9cm overlay per. 
        2 wave overlay min. 9cm / 2 = 4.5cm per wave 
       
        5cm must extrude from the end of the carport roof
        
        (780cm+5) / 600cm = 1
        780 % 100 = 180
        180 + 20 = 200
        360 = 1
        
        780 
         */

        int result = ((carport.getLength() + 5) / (600 - 20) >= 1)
                ? (carport.getWidth() / 100) : 0;
        return new Part("Plastmo Ecolyte blåtonet", result, "stk", "109 x 600. tagplader monteres på spær", 95);
    }

    private static Part calcFlatRoofPlates360(Carport carport) {

        int calc = ((carport.getLength() + 5) % (600 - 20) != 0 && ((double) carport.getLength() / (360.0 - 20.0) > 0))
                ? (carport.getWidth() / 100) : 0;
        return new Part("Plastmo Ecolyte blåtonet", calc, "stk", "109 x 360. tagplader monteres på spær", 65);
    }

}
