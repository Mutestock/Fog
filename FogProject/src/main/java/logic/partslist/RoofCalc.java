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
        //Might change depending on carport width. Hard to say without knowing the proportions of a tile
        //360*730 = 262800cm^2
        //262800 / 300 = 876 cm^2 per tile

        int cm2 = (int) Math.ceil((carport.getLength() * carport.getWidth()) / 876);
        return new Part("B & C Dobbelt -s sort", cm2, "monteres på taglægter 6 rækker af 24 sten på hver side af taget", 50);
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
