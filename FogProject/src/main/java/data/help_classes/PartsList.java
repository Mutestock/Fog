package data.help_classes;

import java.util.LinkedList;

/**
 *
 * @author Simon Asholt Norup
 */
public class PartsList {
    
    private final LinkedList<Part> woodPackage;
    private final LinkedList<Part> roofPackage;
    private final LinkedList<Part> fittingsAndScrews;

    public PartsList(LinkedList<Part> woodPackage, LinkedList<Part> roofPackage, LinkedList<Part> fittingsAndScrews) {
        this.woodPackage = woodPackage;
        this.roofPackage = roofPackage;
        this.fittingsAndScrews = fittingsAndScrews;
    }

    public LinkedList<Part> getWoodPackage() {
        return woodPackage;
    }

    public LinkedList<Part> getRoofPackage() {
        return roofPackage;
    }

    public LinkedList<Part> getFittingsAndScrews() {
        return fittingsAndScrews;
    }
    
}
