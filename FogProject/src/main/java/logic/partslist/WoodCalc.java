package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Emil 
 */
public class WoodCalc {
    
    private static int shedLengthBetweenPillar = 400;
    private static int distanceBetweenRoofBeams = 50;
    private static int roofBeamsWidth = 50;
    
    
    //Main method for calculating the parts list.
    public static LinkedList<Part> calculateParts(Carport carport){
        LinkedList<Part> parts = new LinkedList();
        
        //Essential parts.                              danish translation:
        parts.add(calcPillarAmount(carport));       //Stolpe
        parts.add(calcRoofSupport(carport));        //Tag støtte
        parts.add(calcRafterBoard(carport));        //Spær
        parts.add(calcWoodenBeam2(carport));        //Vandbræt på vindskeder
        parts.add(calcWoodenBeam4(carport));        //Tagrem
        parts.add(calcWoodenBeam5(carport));        //Taglægte
        
        //Parts for sloped roof
        if (carport.getRoof().getSlope() > 0) {
            parts.add(calcWoodenBeam1(carport));}   //Vindskeder til carport
        
        //Parts for the shed
        if (carport.getShed() != null) {
            parts.add(calcWoodenBeam3(carport));}   //Sternbrædder til skur
        if (carport.getShed() != null) {
            parts.add(calcShedWalling(carport));}   //Skurets træ beklædning
        if (carport.getShed() != null) {
            parts.add(calcShedTransoms(carport));}  //Skurets træ løsholter
        
        
        return parts;
    }
 
    
    
    //Help methods for the main method to calculate amount of specific parts.
    //Calculates the amount of wooden pillars needed for the carport.
    private static Part calcPillarAmount (Carport carport) {
        int amount = 0;
        //First the amount of pillars used for the shed.
        if (carport.getShed().getWidth() > shedLengthBetweenPillar) {
            amount += 6;
        } else {
            amount += 4;
        }
        //Then the amount of pillars used for the rest of the carport.
        amount += 4;
        //Plus one is a spare part.
        amount ++;
        return new Part("Wooden Pillar",(int) Math.ceil(carport.getHeight()+0.9),amount,"Pillars used for roof support", 15.95);
    }
    
    //Calculates the amount of wooden beams used for the roof support.
    private static Part calcRoofSupport (Carport carport) {
        int amount = 0;
        //Returns the amount of wooden beams with the width of a beam and
        //the static distance between each beam divided by the carport length.
        amount = (int) Math.ceil(((distanceBetweenRoofBeams + roofBeamsWidth) / carport.getLength()) + 1);
        return new Part("Wooden Beam",carport.getWidth(),amount,"Wooden beams used for the roof support along the width",30.95);
    }
    
    //Returns a part if the roof is sloped for the roof barge.
    private static Part calcWoodenBeam1 (Carport carport) {
        if (carport.getRoof().getSlope() > 0) {
            return new Part("Wooden Beam",carport.getLength(),2,"Wooden beams used for the roof barge",23.50);
        } else {
            return null;
        }
    }
    
    //Returns a part for the fascia board of the carport.
    private static Part calcWoodenBeam2 (Carport carport) {
            return new Part("Wooden Beam",carport.getLength(),2,"Wooden beams used for the fascia board of the carport",235.95);
    }
    
    //Returns a part for the fascia board of the shed.
    private static Part calcWoodenBeam3 (Carport carport) {
        if (carport.getShed() == null) {
            return null;
        } else {
            return new Part("Wooden Beam",carport.getShed().getWidth(),2,"Wooden beams used for the fascia board of the carport",129.95);
        }
    }
    
    //Returns a part for the wooden strop (tagrem).
    private static Part calcWoodenBeam4 (Carport carport) {
            return new Part("Wooden Beam used as the strop",carport.getWidth(),3,
                    "Wooden beams used as the strop of the carport",35.95);
    }
    
    //Returns a part for the wooden beams of the roof (taglægte).
    private static Part calcWoodenBeam5 (Carport carport) {                 //Mængden er konstant pt.
            return new Part("Wooden Beam used as the strop",carport.getLength(),25,
                    "Wooden beams used as the framework of the roof",35.95);
    }
    
    //Returns a part-set, the rafter board, if the roof is sloped and wooden beams if the roof isn't sloped. 
    private static Part calcRafterBoard (Carport carport) {
        if (carport.getRoof().getSlope() == 0) {
            return new Part("Wooden Rafter Board Beams",carport.getWidth(),20,"Wooden beams for the rafter board",149.99);
        } else {
            return new Part("Rafter Board set",carport.getWidth(),1,"A complete build-yourself set of a rafter board",199.99);
        }
    }
    
   //Returns a part for the shed walling.
    private static Part calcShedWalling (Carport carport) {
        if (carport.getShed() != null) {
            return new Part("Wooden wall beams",10,(carport.getShed().getLength()+carport.getShed().getWidth())*2,
                    "Wooden beams for the wall covering of the shed",3.95);
        } else {
            return null;
        }
    }
    
   //Returns a part for the shed transoms.
    private static Part calcShedTransoms (Carport carport) {
        if (carport.getShed() != null) {                             //length is currently constant
            return new Part("Wooden wall beams",carport.getShed().getWidth(),10,
                    "Wooden beams for shed transoms",15.95);
        } else {
            return null;
        }
    }
    
}
