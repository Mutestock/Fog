package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Emil 
 */
public class WoodCalc {
    
    
    
    
    //Main method for calculating the parts list.
    public static LinkedList<Part> calculateParts(Carport carport){
        LinkedList<Part> parts = new LinkedList();
        
        //Essential parts.                                Danish translation:
        parts.add(calcPillarAmount(carport));           //Stolpe.
        parts.add(calcRafterBoardNorm2(carport));       //Rem. carport del.
        parts.add(calcHeadboards(carport));             //Gavle.
        
        //Parts for sloped roof
        if (carport.getRoof().getRaised()) {
            parts.add(calcRafterBoardSlope(carport));    //Spær, med rejsning.
            parts.add(calcFasciaBoards1(carport));       //Stern.
            parts.add(calcFasciaBoards2(carport));       //Stern.
            parts.add(calcFasciaBoards3(carport));       //Stern.   
            parts.add(calcFasciaBoards4(carport));       //Stern.
        }
        //Parts for nonsloped roof
        else { 
            parts.add(calcRafterBoardNorm1(carport));    //Spær, uden rejsning.
            parts.add(calcFasciaBoardsSlope1(carport));  //Sternbrædder til carport del.
            if (carport.getShed() != null) {
                parts.add(calcFasciaBoardsSlope2(carport));  //Sternbrædder til skur del.
            }
        }   
        
        //Parts for the shed
        if (carport.getShed() != null) {
            parts.add(calcRafterBoardNorm3(carport));    //Rem. skur del.
            parts.add(calcInterTies1());                 //Løsholter for side.
            parts.add(calcInterTies2(carport));          //Løsholter for gavle.
            parts.add(calcWallCovering(carport));        //Beklædning af skur.
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
            return new Part("45x195mm. spærtræ ubh. spær",carport.getWidth(),(int) Math.ceil(Math.ceil(carport.getLength()/100)*2),"Spær monteres på rem",29.99);
    }
    
    //Returns a part-set, the rafter board, used when the carport roof has a slope. (Spær) 
    private static Part calcRafterBoardSlope (Carport carport) {
            return new Part("Færdigskåret byg-selv-spær ",carport.getWidth(),(carport.getLength()/100)+1,"Et byg-selv-sæt til carportens spær. Antallet viser stk-mængde sættet indeholder.",199.99);
    }   
    
    //Returns two strop boards (Rem) 
    private static Part calcRafterBoardNorm2 (Carport carport) {
            return new Part("45x195mm. spærtræ ubh. Carport rem",carport.getWidth(),2,"Remme monteres i sider, sadles i stolper. Carport del.",29.99);
    }
    
    //Returns one more strop board, used if the carport is with a shed (Rem) 
    private static Part calcRafterBoardNorm3 (Carport carport) {
            return new Part("45x195mm. spærtræ ubh. Skur rem",carport.getWidth(),1,"Remme monteres i sider, sadles i stolper. Skur del",29.99);
    }
    
    //Returns the two boards used for the headboard (Gavle) 
    private static Part calcHeadboards (Carport carport) {
            return new Part("45x195mm. spærtræ ubh. Gavle",carport.getLength(),2,"Gavle monteres på remme i enderne.",29.99);
    }
    
    //Returns the amount of inter-ties used for the sides of the shed (Løsholter).
    private static Part calcInterTies1 () {
            return new Part("45x95 Reglar ubh. Skur side",240,4,"Løsholter i siderne af skur",29.99);
    }
    
    //Returns the rest of inter-ties used for the the shed (Løsholter).
    private static Part calcInterTies2 (Carport carport) {
            return new Part("45x95 Reglar ubh. Skur gavle",carport.getLength(),6,"Løsholter i gavle af skur",29.99);
    }
    
    //Returns the amount of boards used for the wall covering used for the the shed (Beklædning).
    private static Part calcWallCovering (Carport carport) {
            return new Part("19x100 mm. trykimp. Bræt Beklædning",carport.getWidth(),200,"Beklædning af skur 1 på 2",29.99);
    }
    
    //Returns the amount of fascia-boards used for the carport if there is a slope.
    private static Part calcFasciaBoardsSlope1 (Carport carport) {
            return new Part("25x150mm. trykimp. Bræt Carport stern rejsning",600,2,"Sternbrædder til siderne Carport del",29.99);
    }
    
    //Returns the amount of fascia-boards used for the shed if thecarport has a slope.
    private static Part calcFasciaBoardsSlope2 (Carport carport) {
            return new Part("25x150mm. trykimp. Bræt Skur stern rejsning",540,1,"Sternbrædder til siderne Skur del (deles)",29.99);
    }
    
    //Returns the amount of boards used for the wall covering used for the the shed (Beklædning).
    private static Part calcFasciaBoards1 (Carport carport) {
            return new Part("25x200mm. trykimp. Bræt Understern ender",360,4,"Understernbrædder til for & bagende",29.99);
    }
    
    //Returns the amount of boards used for the wall covering used for the the shed (Beklædning).
    private static Part calcFasciaBoards2 (Carport carport) {
            return new Part("25x200mm. trykimp. Bræt Understern sider",540,4,"Understernbrædder til siderne",29.99);
    }
    
    //Returns the amount of boards used for the wall covering used for the the shed (Beklædning).
    private static Part calcFasciaBoards3 (Carport carport) {
            return new Part("25x200mm. trykimp. Bræt Overstern ender",360,2,"Oversternbrædder til for & bagende",29.99);
    }
    
    //Returns the amount of boards used for the wall covering used for the the shed (Beklædning).
    private static Part calcFasciaBoards4 (Carport carport) {
            return new Part("25x200mm. trykimp. Bræt Overstern sider",540,4,"Oversternbrædder til siderne",29.99);
    }
    
     
    
}
