package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Henning
 */
public class RoofCalc {
    
    private static final int topBattenSlotAmount = 8;
    private static final int roofTileMatPacksAmount = 2;
    private static final double ridgeTileRatio = 34.7;
    private static final double tileRatio = 939.27;
    private static final double minimumPlateExtrusion = 5.0;
    private static final double minimumPlateLengthOverlap = 20;
    private static final double plateWidth = 109;
    private static final double plateWaveLength = 4.5;

    private static final double priceStandardTile = 10;
    private static final double priceRidgeTile = 50;
    private static final double priceRidgeTileSlot = 40;
    private static final double pricePlate600 = 100;
    private static final double pricePlate360 = 75;
    private static final double priceTopBatten = 50;
    private static final double priceMatPack = 100;

    private static final String descriptionTopBatten = "monteres på toppen af spæret (til toplægte)";
    private static final String descriptionMatPack = "til montering af tagsten, alle ydersten + hver anden fastgøres";
    private static final String descriptionStandardTile = "monteres på taglægter 6 rækker af 24 sten på hver side af taget";
    private static final String descriptionRidgeTileSlot = "Til montering af rygsten";
    private static final String descriptionRidgeTile = "monteres på toplægte med medfølgende beslag se tagstens vejledning";
    private static final String descriptionPlate600 = "109 x 600. tagplader monteres på spær";
    private static final String descriptionPlate360 = "109 x 360. tagplader monteres på spær";

    private static final String nameTopBatten = "B & C Toplægte holder";
    private static final String nameMatPack = "B & C tagstens bindere & nakkekroge";
    private static final String nameStandardTile = "B & C Dobbelt -s sort";
    private static final String nameRidgeTile = "B & C Rygsten sort";
    private static final String nameRidgeTileSlot = "B & C rygstensbeslag";
    private static final String namePlate600 = "Plastmo Ecolyte blåtonet";
    private static final String namePlate360 = "Plastmo Ecolyte blåtonet";

    private static final String unitTopBatten = "stk";
    private static final String unitMatPack = "pk";
    private static final String unitStandardTile = "stk";
    private static final String unitRidgeTile = "stk";
    private static final String unitRidgeTileSlot = "stk";
    private static final String unitPlate600 = "stk";
    private static final String unitPlate360 = "stk";

    /**
     *
     * Main calculator for roof part list generation. To understand parts refer
     * to:
     * {@link data.help_classes.Part#Part(String, int, int, String, String, double) Part Constructor}
     *
     * Refer to field constants to change the outcome. Utilises several methods
     * in order to function correctly:
     *
     * {@link #calcTileCount(carport) calcTileCount}
     * {@link #calcRidgeTile(carport) calcRidgeTile}
     * {@link #calcRidgeTileSlot(carport) calcRidgeTileSlot}
     * {@link #calcFlatRoofPlates600(carport) calcFlatRoofPlates600}
     * {@link #calcFlatRoofPlates360(carport) calcFlatRoofPlates360}
     *
     * Additional parts without the need for calculation are included inside the
     * method. These are pre-defined and added whenever the user picks a roof:
     *
     * Top batten Package of various materials (MatPack)
     *
     * To understand ratios:
     *
     * Standard tile count is calculated using the following formula:
     *
     * width / 2 = b b / cos(A) = c c* 2 = modified width modified width *
     * length / ratio = brick count
     *
     * Ridge tile count and ridge tile slot count: Length / ratio = brick count
     *
     * Plates are chosen by preference, meaning that if there's enough space for
     * a 600 plate, then it will be chosen. Otherwise a 360 plate will be placed
     * and assigned to be cut off. The last plate must extrude 5cm in order to
     * fulfil the requirements. There's a minimum overlap of 20cm between
     * plates. There must be a minimum of 2 waves on each overlap. To comply
     * with given schematics, the default wavelength has been set to 4.5 cm,
     * since the standard length of a plasmo plate is 109cm in all scenarios.
     * Note that using the plasmo roof generator, the estimated amount of plates
     * needed will be higher than the given schematics.
     *
     * All these values can be changed in constant fields.
     * 
     * @param carport
     * @return LinkedList<part>
     */
    public static LinkedList<Part> calculateParts(Carport carport) {
        LinkedList<Part> parts = new LinkedList();

        if (carport.getRoof().getRaised() == true) {
            parts.add(calcTileCount(carport));
            parts.add(calcRidgeTile(carport));
            parts.add(calcRidgeTileSlot(carport));
            Part topBattenSlotPart = new Part(nameTopBatten, topBattenSlotAmount, unitTopBatten, descriptionTopBatten, priceTopBatten);
            Part roofTileMatPacksPart = new Part(nameMatPack, roofTileMatPacksAmount, unitMatPack, descriptionMatPack, priceMatPack);

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
        int result = 0;
        double preCasted = 0.0;
        preCasted = carport.getWidth() / 2;
        double toDegrees = Math.cos(Math.toRadians((double) carport.getRoof().getSlope()));
        preCasted = preCasted / toDegrees;
        preCasted = preCasted * 2;
        preCasted = preCasted * (double) carport.getLength() / tileRatio;
        result = (int) Math.ceil(preCasted);
        return new Part(nameStandardTile, result, unitStandardTile, descriptionStandardTile, priceStandardTile);
    }

    private static Part calcRidgeTile(Carport carport) {
        int result = (int) Math.ceil((carport.getLength() / ridgeTileRatio));
        return new Part(nameRidgeTile, result, unitRidgeTile, descriptionRidgeTile, priceRidgeTile);
    }

    private static Part calcRidgeTileSlot(Carport carport) {
        int result = (int) Math.ceil((carport.getLength() / ridgeTileRatio));
        return new Part(nameRidgeTileSlot, result, unitRidgeTileSlot, descriptionRidgeTileSlot, priceRidgeTileSlot);
    }

    private static Part calcFlatRoofPlates600(Carport carport) {
        int result = ((carport.getLength() + minimumPlateExtrusion) / (600 - minimumPlateLengthOverlap) >= 1)
                ? (carport.getWidth() / (int) Math.ceil(plateWidth - (2 * plateWaveLength))) : 0;
        return new Part(namePlate600, result, unitPlate600, descriptionPlate600, pricePlate600);
    }

    private static Part calcFlatRoofPlates360(Carport carport) {
        int calc = ((carport.getLength() + minimumPlateExtrusion) % (600 - minimumPlateLengthOverlap) != 0
                && ((double) carport.getLength() / (360.0 - minimumPlateLengthOverlap) > 0))
                ? (carport.getWidth() / (int) Math.ceil(plateWidth - (2 * plateWaveLength))) : 0;
        return new Part(namePlate360, calc, unitPlate360, descriptionPlate360, pricePlate360);
    }
}
