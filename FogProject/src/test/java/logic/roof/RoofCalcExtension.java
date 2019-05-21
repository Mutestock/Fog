package logic.roof;

import logic.partslist.RoofCalc;

/**
 *
 * @author Henning
 */
public class RoofCalcExtension extends RoofCalc {

    RoofCalcInterface rServ;

    public RoofCalcExtension(RoofCalcInterface rServ) {
        this.rServ = rServ;
    }
}
