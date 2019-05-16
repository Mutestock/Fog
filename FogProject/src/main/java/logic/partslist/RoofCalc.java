package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Henning
 */
public class RoofCalc {
    
    private static final int TOP_BATTEN_SLOT_AMOUNT = 8;
    private static final int ROOF_TILE_MAT_PACKS_AMOUNT = 2;
    private static final double RIDGE_TILE_RATIO = 34.7;
    private static final double TILE_RATIO = 939.27;
    private static final double MINIMUM_PLATE_EXTRUSION = 5.0;
    private static final double MINIMUM_PLATE_LENGTH_OVERLAP = 20;
    private static final double PLATE_WIDTH = 109;
    private static final double PLATE_WAVE_LENGTH = 4.5;

    private static final double PRICE_STANDARD_TILE = 10;
    private static final double PRICE_RIDGE_TILE = 50;
    private static final double PRICE_RIDGE_TILE_SLOT = 40;
    private static final double PRICE_PLATE600 = 100;
    private static final double PRICE_PLATE360 = 75;
    private static final double PRICE_TOP_BATTEN = 50;
    private static final double PRICE_MAT_PACK = 100;

    private static final String DESCRIPTION_TOP_BATTEN = "monteres på toppen af spæret (til toplægte)";
    private static final String DESCRIPTION_MAT_PACK = "til montering af tagsten, alle ydersten + hver anden fastgøres";
    private static final String DESCRIPTION_STANDARD_TILE = "monteres på taglægter 6 rækker af 24 sten på hver side af taget";
    private static final String DESCRIPTION_RIDGE_TILE_SLOT = "Til montering af rygsten";
    private static final String DESCRIPTION_RIDGE_TILE = "monteres på toplægte med medfølgende beslag se tagstens vejledning";
    private static final String DESCRIPTION_PLATE600 = "109 x 600. tagplader monteres på spær";
    private static final String DESCRIPTION_PLATE360 = "109 x 360. tagplader monteres på spær";

    private static final String NAME_TOP_BATTEN = "B & C Toplægte holder";
    private static final String NAME_MAT_PACK = "B & C tagstens bindere & nakkekroge";
    private static final String NAME_STANDARD_TILE = "B & C Dobbelt -s sort";
    private static final String NAME_RIDGE_TILE = "B & C Rygsten sort";
    private static final String NAME_RIDGE_TILE_SLOT = "B & C rygstensbeslag";
    private static final String NAME_PLATE600 = "Plastmo Ecolyte blåtonet";
    private static final String NAME_PLATE360 = "Plastmo Ecolyte blåtonet";

    private static final String UNIT_TOP_BATTEN = "stk";
    private static final String UNIT_MAT_PACK = "pk";
    private static final String UNIT_STANDARD_TILE = "stk";
    private static final String UNIT_RIDGE_TILE = "stk";
    private static final String UNIT_RIDGE_TILE_SLOT = "stk";
    private static final String UNIT_PLATE600 = "stk";
    private static final String UNIT_PLATE360 = "stk";

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
            Part topBattenSlotPart = new Part(NAME_TOP_BATTEN, TOP_BATTEN_SLOT_AMOUNT, UNIT_TOP_BATTEN, DESCRIPTION_TOP_BATTEN, PRICE_TOP_BATTEN);
            Part roofTileMatPacksPart = new Part(NAME_MAT_PACK, ROOF_TILE_MAT_PACKS_AMOUNT, UNIT_MAT_PACK, DESCRIPTION_MAT_PACK, PRICE_MAT_PACK);

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
        preCasted = preCasted * (double) carport.getLength() / TILE_RATIO;
        result = (int) Math.ceil(preCasted);
        return new Part(NAME_STANDARD_TILE, result, UNIT_STANDARD_TILE, DESCRIPTION_STANDARD_TILE, PRICE_STANDARD_TILE);
    }

    private static Part calcRidgeTile(Carport carport) {
        int result = (int) Math.ceil((carport.getLength() / RIDGE_TILE_RATIO));
        return new Part(NAME_RIDGE_TILE, result, UNIT_RIDGE_TILE, DESCRIPTION_RIDGE_TILE, PRICE_RIDGE_TILE);
    }

    private static Part calcRidgeTileSlot(Carport carport) {
        int result = (int) Math.ceil((carport.getLength() / RIDGE_TILE_RATIO));
        return new Part(NAME_RIDGE_TILE_SLOT, result, UNIT_RIDGE_TILE_SLOT, DESCRIPTION_RIDGE_TILE_SLOT, PRICE_RIDGE_TILE_SLOT);
    }

    private static Part calcFlatRoofPlates600(Carport carport) {
        int result = ((carport.getLength() + MINIMUM_PLATE_EXTRUSION) / (600 - MINIMUM_PLATE_LENGTH_OVERLAP) >= 1)
                ? (carport.getWidth() / (int) Math.ceil(PLATE_WIDTH - (2 * PLATE_WAVE_LENGTH))) : 0;
        return new Part(NAME_PLATE600, result, UNIT_PLATE600, DESCRIPTION_PLATE600, PRICE_PLATE600);
    }

    private static Part calcFlatRoofPlates360(Carport carport) {
        int calc = ((carport.getLength() + MINIMUM_PLATE_EXTRUSION) % (600 - MINIMUM_PLATE_LENGTH_OVERLAP) != 0
                && ((double) carport.getLength() / (360.0 - MINIMUM_PLATE_LENGTH_OVERLAP) > 0))
                ? (carport.getWidth() / (int) Math.ceil(PLATE_WIDTH - (2 * PLATE_WAVE_LENGTH))) : 0;
        return new Part(NAME_PLATE360, calc, UNIT_PLATE360, DESCRIPTION_PLATE360, PRICE_PLATE360);
    }
}
