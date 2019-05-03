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
        
        //Essential parts.                                Danish translation:
        parts.add(calcPillarAmount(carport));           //Stolpe.
        parts.add(calcRafterBoardNorm2(carport));       //Rem. carport del.
        
        //Parts for sloped roof
        if (carport.getRoof().getRaised()) {
            parts.add(calcRafterBoardSlope(carport));    //Spær, med rejsning.
        }
        //Parts for nonsloped roof
        else { 
            parts.add(calcRafterBoardNorm1(carport));    //Spær, uden rejsning.
        }   
        
        //Parts for the shed
        if (carport.getShed() != null) {
            parts.add(calcRafterBoardNorm3(carport));    //Rem. skur del.
        }  
        
        return parts;
    }
 
    
    
    
    
    //Help methods for the main method to calculate amount of specific parts.
    //Calculates the amount of wooden pillars needed for the carport.
    private static int lengthBetweenPillars = 480;
    //lengthBetweenPillars describes the longest allowed distance between each pillar.
    private static Part calcPillarAmount (Carport carport) {
        int amount = 0;
        
        //The amount of pillars used for the carport based on length.
        if (carport.getLength() > lengthBetweenPillars) {
            amount += 6;
        } else {
            amount += 4;
        }
        
        //The amount of pillars used for the carport based on width.
        if (carport.getWidth() > lengthBetweenPillars) {
            amount += 1 ;
        }
        
        //The amount of pillars used for the shed.
        if (carport.getShed() != null) {
            if (carport.getShed().getWidth() > lengthBetweenPillars) {
                amount += 5;
            } else {
                amount += 3;
            }
        }
        
        //Plus one is a spare part.
        amount ++;
        return new Part("97x97mm. trykimp. stolpe ",(int) Math.ceil(carport.getHeight()+0.9),amount,"Stolpe nedgraves 90cm i jord.", 15.95);
    }
    
    //Returns the amount of rafter boards based on the width of the carport. (Spær) 
    private static Part calcRafterBoardNorm1 (Carport carport) {
            return new Part("45x195mm. spærtræ ubh.",carport.getWidth(),(int) Math.ceil(Math.ceil(carport.getLength()/100)*2),"Spær monteres på rem",29.99);
    }
    
    //Returns a part-set, the rafter board, used when the carport roof has a slope. (Spær) 
    private static Part calcRafterBoardSlope (Carport carport) {
            return new Part("Færdigskåret byg-selv-spær ",carport.getWidth(),(carport.getLength()/100)+1,"Et byg-selv-sæt til carportens spær. Antallet viser stk-mængde sættet indeholder.",199.99);
    }   
    
    //Returns two strop boards (Rem) 
    private static Part calcRafterBoardNorm2 (Carport carport) {
            return new Part("45x195mm. spærtræ ubh. ",carport.getWidth(),2,"Remme monteres i sider, sadles i stolper. Carport del.",29.99);
    }
    
    //Returns one more strop board, used if the carport is with a shed (Rem) 
    private static Part calcRafterBoardNorm3 (Carport carport) {
            return new Part("45x195mm. spærtræ ubh. ",carport.getWidth(),1,"Remme monteres i sider, sadles i stolper. Skur del",29.99);
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
