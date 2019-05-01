package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Emil 
 */
public class WoodCalc {
    
    
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
        if (carport.getRoof().getRaised()) {
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
    private static int lengthBetweenPillars = 480;
    //lengthBetweenPillars describes the longest allowed distance between each pillar.
    private static Part calcPillarAmount (Carport carport) {
        int amount = 0;
        
        //The amount of pillars used for the carport.
        if (carport.getLength() > lengthBetweenPillars) {
            amount += 6;
        } else {
            amount += 4;
        }
        
        //The amount of pillars used for the shed.
        if (carport.getShed() != null) {
            if (carport.getShed().getWidth() > lengthBetweenPillars) {
                amount += 6;
            } else {
                amount += 4;
            }
        }
        
        //Plus one is a spare part.
        amount ++;
        return new Part("Træ Stolpe",(int) Math.ceil(carport.getHeight()+0.9),amount,"Stolpe til brug af tagstøtte", 15.95);
    }
    
        
    //Calculates the amount of wooden beams used for the roof support.
    private static Part calcRoofSupport (Carport carport) {
        int amount = 0;
        //Returns the amount of wooden beams with the width of a beam and
        //the static distance between each beam divided by the carport length.
        amount = (int) Math.ceil(((distanceBetweenRoofBeams + roofBeamsWidth) / carport.getLength()) + 1);
        return new Part("Træ Bjælke",carport.getWidth(),amount,"Træ Bjælke til brug for tag støtten langs bredden",30.95);
    }
    
    //Returns a part if the roof is sloped for the roof barge (Vindskeder).
    private static Part calcWoodenBeam1 (Carport carport) {
        if (carport.getRoof().getSlope() > 0) {
            return new Part("Træ Bjælke",carport.getLength(),2,"Træ bjælker brugt som vindskeder til carport sider",23.50);
        } else {
            return null;
        }
    }
    
    //Returns a part for the fascia board of the carport (Overstern.
    private static Part calcWoodenBeam2 (Carport carport) {
            return new Part("Træ Bjælke",carport.getLength(),2,"Træ bjælker brug som carportens overstern",235.95);
    }
    
    //Returns a part for the fascia board of the shed (Stern). 
    private static Part calcWoodenBeam3 (Carport carport) {
        if (carport.getShed() == null) {
            return null;
        } else {
            return new Part("Træ Bjælke",carport.getShed().getWidth(),2,"Træ bjælke brugt til carportens stern",129.95);
        }
    }
    
    //Returns a part for the wooden strop (tagrem).
    private static Part calcWoodenBeam4 (Carport carport) {
            return new Part("Træ Bjælke",carport.getWidth(),3,
                    "Træ bjælke brugt til tagremme i sider",35.95);
    }
    
    //Returns a part for the wooden beams of the roof (taglægte).
    private static Part calcWoodenBeam5 (Carport carport) {                 //Mængden er konstant pt.
            return new Part("Træ Bjælke",carport.getLength(),25,
                    "Træ bjælker brugt som tagfodslægter",35.95);
    }
    
    //Returns a part-set, the rafter board, if the roof is sloped and wooden beams if the roof isn't sloped. (Spær) 
    private static Part calcRafterBoard (Carport carport) {
        if (carport.getRoof().getSlope() == 0) {
            return new Part("Træ Spær Brædder",carport.getWidth(),20,"Træ Brædder til carportens spær",149.99);
        } else {
            return new Part("Træ Spær sæt",carport.getWidth(),1,"Et fuldt byg-selv-sæt til carportens spær",199.99);
        }
    }
    
   //Returns a part for the shed walling. (Beklædning)
    private static Part calcShedWalling (Carport carport) {
        if (carport.getShed() != null) {
            return new Part("Træ vægs bjælker",10,((carport.getShed().getLength()+carport.getShed().getWidth())*2)/10,
                    "Træ bjælker til beklædningen af skuret",3.95);
        } else {
            return null;
        }
    }
    
   //Returns a part for the shed transoms. (Toplægte)
    private static Part calcShedTransoms (Carport carport) {
        if (carport.getShed() != null) {                             //length is currently constant
            return new Part("Træ vægs bjælker",carport.getShed().getWidth(),10,
                    "Træ bjælker brugt til toplægten",15.95);
        } else {
            return null;
        }
    }
    
}
