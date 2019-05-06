package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;
import data.customExceptions.*;
import data.help_classes.PartsList;

/**
 *
 * @author Henning
 */
public class RoofCalc {

    //Considered as constant values.
    //Please find correct terminology
    private static int topBattenSlot = 8;
    private static int roofTileMatPacks = 2;

    public static LinkedList<Part> calculateParts(Carport carport) {
        LinkedList<Part> parts = new LinkedList();
        parts.add(calcTileCount(carport));
        parts.add(calcRidgeTile(carport));
        parts.add(calcRidgeTileSlot(carport));

        Part topBattenSlotPart = new Part("B & C Toplægte holder", topBattenSlot, "monteres på toppen af spæret (til toplægte)", 32.0);
        Part roofTileMatPacksPart = new Part("B & C tagstens bindere & nakkekroge", roofTileMatPacks, "til montering af tagsten, alle ydersten + hver anden fastgøres", 69);

        parts.add(topBattenSlotPart);
        parts.add(roofTileMatPacksPart);

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
//        Ratio = 926.4
        int result = 0; 
        double preCasted = 0.0;
        System.out.println("Width: " + carport.getWidth());
        System.out.println("Length: " + carport.getLength());
        System.out.println("Height: " + carport.getHeight());
        preCasted = Math.ceil(carport.getWidth() / 2);
        System.out.println(preCasted);
        System.out.println("Slope:  " + carport.getRoof().getSlope());
        double degrees = Math.toDegrees(carport.getRoof().getSlope());
        System.out.println("Cos: " + Math.cos(20)); 
        System.out.println("Cos + to degrees: " + Math.toDegrees(Math.cos(20)));
        double toDegrees = Math.cos(Math.toRadians((double)carport.getRoof().getSlope()));
        System.out.println("To degrees: " + toDegrees);
        preCasted = 375 / toDegrees;
        //System.out.println("precast + to degrees " + preCasted);
        System.out.println("Degrees: " + Math.cos(Math.toDegrees((double) carport.getRoof().getSlope())));
        preCasted = preCasted * 2;
        System.out.println(preCasted);
        preCasted = preCasted * (double) carport.getLength() / 939.27;
        System.out.println(preCasted);

        result = (int) Math.ceil(preCasted);

//        int cm2 = (int) Math.ceil((carport.getLength() * carport.getWidth()) / 876);
        return new Part("B & C Dobbelt -s sort", result, "monteres på taglægter 6 rækker af 24 sten på hver side af taget", 4.5);
    }

    private static Part calcRidgeTile(Carport carport) {
        //Non-dependant on width
        //Length 730 / 21 = 34.7 cm

        int cm = (int) Math.ceil((carport.getLength() / 34.7));
        return new Part("B & C Rygsten sort", cm, "monteres på toplægte med medfølgende beslag se tagstens vejledning", 100);
    }

    private static Part calcRidgeTileSlot(Carport carport) {
        //Containers for ridgetiles. Same proportions.
        int cm = (int) Math.ceil((carport.getLength() / 34.7));
        return new Part("B & C rygstensbeslag", cm, "Til montering af rygsten", 75);
    }

}
