package data.help_classes;

import java.util.LinkedList;

/**
 * Entity class of a parts list.
 * Holds all nessescary information of a parts list.
 */
public class PartsList {

    private final LinkedList<Part> woodPackage;
    private final LinkedList<Part> roofPackage;
    private final LinkedList<Part> fittingsAndScrews;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param woodPackage a linked list with the wooden parts of the carport.
     * @param roofPackage a linked list with the roof parts of the carport.
     * @param fittingsAndScrews  a linked list with all the fitting and screws of the carport.
     */
    public PartsList(LinkedList<Part> woodPackage, LinkedList<Part> roofPackage, LinkedList<Part> fittingsAndScrews) {
        if (woodPackage == null || roofPackage == null || fittingsAndScrews == null) {
            throw new IllegalArgumentException();
        }
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
