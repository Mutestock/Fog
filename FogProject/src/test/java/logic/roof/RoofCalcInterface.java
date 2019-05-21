package logic.roof;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Henning
 */
public interface RoofCalcInterface {
    public LinkedList<Part> calculateParts(Carport carport);
}
