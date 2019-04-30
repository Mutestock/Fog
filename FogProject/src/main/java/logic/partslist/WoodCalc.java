package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author ???
 */
public class WoodCalc {
    
    private static int shedLengthPillar = 4;
    private static double distanceBetweenRoofBeams = 0.5;
    private static double roofBeamsWidth = 0.5;
    
    //Main method for calculating the parts list.
    public static LinkedList<Part> calculateParts(Carport carport){
        throw new UnsupportedOperationException();
    }
 
    
    
    //Help methods for the main method to calculate amount of specific parts.
    //Calculates the amount of wooden pillars needed for the carport.
    private static int calcPillarAmount (Carport carport) {
        int amount = 0;
        //First the amount of pillars used for the shed.
        if (carport.getShed().getWidth() > shedLengthPillar) {
            amount += 6;
        } else {
            amount += 4;
        }
        
        //Then the amount of pillars used for the rest of the carport.
        amount += 4;
        
        //Plus one is a spare part.
        return amount + 1;
    }
    
    
    //Calculates the amount of wooden beams used for the roof support.
    private static int calculateRoofSupport (Carport carport) {
        int amount = 0;
        //Returns the amount of wooden beams with the width of a beam and
        //the static distance between each beam divided by the carport length.
        amount = (int) Math.ceil(((distanceBetweenRoofBeams + roofBeamsWidth) / carport.getLength()) + 1);
        return amount;
    }
    
}
