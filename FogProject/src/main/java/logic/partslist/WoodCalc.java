package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 * The class used to calclulate the amount of wooden pieces for a carport object.
 * @author Emil Jógvan Bruun
 * @version 1.0
 */
public class WoodCalc {

    
    //Main method for calculating the parts list.
    /**
     * Main method of generating the wooden parts list.
     * @param carport predefined carport object with length and width.
     * @return LinkedList List of parts through use of help methods.
     */
    public static LinkedList<Part> calculateParts(Carport carport) {
        LinkedList<Part> parts = new LinkedList();

        //Essential parts.                                      Danish translation:
        parts.add(calcPillarAmount(carport));                   //Stolpe.
        parts.add(calcRafterBoardNorm2(carport));               //Rem. carport del.
        parts.add(calcHeadboards(carport));                     //Gavle.

        //Parts for sloped roof
        if (carport.getRoof().getRaised()) {
            parts.add(calcRafterBoardSlope(carport));           //Spær, med rejsning.
            parts.add(calcFasciaBoardsSlope1(carport));         //Sternbrædder til carport del.
            if (carport.getShed() != null) {
                parts.add(calcFasciaBoardsSlope2(carport));}    //Sternbrædder til skur del.
            parts.add(calcWaterBoardSlope(carport));            //Vandbræt til vindskeder.
            parts.add(calcBarge(carport));                      //Vindskeder.
            parts.add(calcRoofLaths1(carport));                 //Taglægte.
            parts.add(calcRoofLaths2(carport));                 //Taglægte til rygsten.
        } //Parts for nonsloped roof
        else {
            parts.add(calcRafterBoardNorm1(carport));           //Spær, uden rejsning.
            parts.add(calcFasciaBoards1(carport));              //Stern.
            parts.add(calcFasciaBoards2(carport));              //Stern.
            parts.add(calcFasciaBoards3(carport));              //Stern.   
            parts.add(calcFasciaBoards4(carport));              //Stern.
            parts.add(calcWaterBoard1(carport));                //Vandbræt til sider.
            parts.add(calcWaterBoard2(carport));                //Vandbræt til ender.
        }

        //Parts for the shed
        if (carport.getShed() != null) {
            parts.add(calcRafterBoardNorm3(carport));           //Rem. skur del.
            parts.add(calcInterTies1());                        //Løsholter for side.
            parts.add(calcInterTies2(carport));                 //Løsholter for gavle.
            parts.add(calcWallCovering(carport));               //Beklædning af skur.
            parts.add(calcLathdoor(carport));                   //Lægte til z på dør.
        }
           
        return parts;
        
    
        
    }

   
    
    private static final int LENGTH_BETWEEN_PILLARS = 480;
    //lengthBetweenPillars describes the longest allowed distance between each pillar.
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns an amount of wooden piller parts based on the length of the carport and the shed if there is one.
     */
    private static Part calcPillarAmount(Carport carport) {
        int amount = 0;

        //The amount of pillars used for the carport based on length.
        if ((carport.getLength() - (5+(carport.getLength()*0.2))) > LENGTH_BETWEEN_PILLARS) {
            amount += 6;
        } else {
            amount += 4;
        }
        //The amount of pillars used for the carport based on width.
        if (carport.getWidth() > LENGTH_BETWEEN_PILLARS) {
            amount += 1;
        }
        //The amount of pillars used for the shed.
        if (carport.getShed() != null) {
            if (carport.getShed().getWidth() > LENGTH_BETWEEN_PILLARS) {
                amount += 5;
            } else {
                amount += 3;
            }
            
            //If the shed has a smaller width than the carport, 3 more poles are needed
            if (carport.getShed().getWidth() < carport.getWidth()-60) {
                amount += 3;
                //4 if the carport is long enough
                if (carport.getLength() - (5+(carport.getLength()*0.2)) > LENGTH_BETWEEN_PILLARS) {
                    amount ++;
                }
            }   
        }
        //Plus one is a spare part.
        amount++;

        int length = (int) Math.ceil(carport.getHeight() + 0.9);
        double meter_price = 30;
        double price = meter_price * (length / 100);
        return new Part("97x97mm. trykimp. stolpe ", length, amount, "stk", "Stolpe nedgraves 90cm i jord.", price);
    }

    
    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @return the amount of rafter boards based on the width of the carport. (Spær i danish) 
     */
    private static Part calcRafterBoardNorm1(Carport carport) {
        int length = carport.getWidth();
        double meter_price = 33;
        double price = meter_price * (length / 100);
        return new Part("45x195mm. spærtræ ubh. spær", length, (int) Math.ceil(Math.ceil(carport.getLength() / 100) * 2), "stk", "Spær monteres på rem", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @return Returns a part-set, the rafter board, used when the carport roof has a slope. (Spær in danish)
     */
    private static Part calcRafterBoardSlope(Carport carport) {
        int length = carport.getWidth();
        double price = 100;
        return new Part("Færdigskåret byg-selv-spær ", length, (carport.getLength() / 100) + 1, "sæt", "Et byg-selv-sæt til carportens spær. Antallet viser stk-mængde sættet indeholder.", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @return //Returns two strop boards (Rem in danish)
     */
    private static Part calcRafterBoardNorm2(Carport carport) {
        int length = carport.getWidth();
        double meter_price = 33;
        double price = meter_price * (length / 100);
        return new Part("45x195mm. spærtræ ubh. Carport rem", length, 2, "stk", "Remme monteres i sider, sadles i stolper. Carport del.", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns one more strop board, used if the carport is with a shed (Rem in danish) 
     */
    private static Part calcRafterBoardNorm3(Carport carport) {
        int length = carport.getWidth();
        double meter_price = 33;
        double price = meter_price * (length / 100);
        return new Part("45x195mm. spærtræ ubh. Skur rem", length, 1, "stk", "Remme monteres i sider, sadles i stolper. Skur del", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the two boards used for the headboard (Gavle in danish) 
     */
    private static Part calcHeadboards(Carport carport) {
        int length = carport.getLength();
        double meter_price = 33;
        double price = meter_price * (length / 100);
        return new Part("45x195mm. spærtræ ubh. Gavle", length, 2, "stk", "Gavle monteres på remme i enderne.", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @returns the amount of inter-ties used for the sides of the shed (Løsholter in danish).
     */
    private static Part calcInterTies1() {
        int length = 240;
        double meter_price = 13;
        double price = meter_price * (length / 100);
        return new Part("45x95 Reglar ubh. Skur side", length, 4, "stk", "Løsholter i siderne af skur", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the rest of inter-ties used for the the shed (Løsholter in danish).
     */
    private static Part calcInterTies2(Carport carport) {
        int length = carport.getLength();
        double meter_price = 13;
        double price = meter_price * (length / 100);
        return new Part("45x95 Reglar ubh. Skur gavle", length, 6, "stk", "Løsholter i gavle af skur", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of boards used for the wall covering used for the the shed (Skur beklædning in danish).
     */
    private static Part calcWallCovering(Carport carport) {
        int length = carport.getWidth();
        double meter_price = 7;
        double price = meter_price * (length / 100);
        return new Part("19x100 mm. trykimp. Bræt Beklædning", length, 200, "stk", "Beklædning af skur 1 på 2", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of fascia-boards used for the carport if there is a slope (Sternbrædder in danish).
     */
    private static Part calcFasciaBoardsSlope1(Carport carport) {
        int length = 600;
        double meter_price = 25;
        double price = meter_price * (length / 100);
        return new Part("25x150mm. trykimp. Bræt Carport stern rejsning", length, 2, "stk", "Sternbrædder til siderne Carport del", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of fascia-boards used for the shed if thecarport has a slope (Sternbrædder in danish).
     */
    private static Part calcFasciaBoardsSlope2(Carport carport) {
        int length = 540;
        double meter_price = 25;
        double price = meter_price * (length / 100);
        return new Part("25x150mm. trykimp. Bræt Skur stern rejsning", length, 1, "stk", "Sternbrædder til siderne Skur del (deles)", price);
    }


    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of boards used for the wall covering used for the the shed (Understern in danish).
     */
    private static Part calcFasciaBoards1(Carport carport) {
        int length = 360;
        double meter_price = 25;
        double price = meter_price * (length / 100);
        return new Part("25x200mm. trykimp. Bræt Understern ender", length, 4, "stk", "Understernbrædder til for & bagende", price);
    }


    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of boards used for the wall covering used for the the shed (Understern in danish).
     */
    private static Part calcFasciaBoards2(Carport carport) {
        int length = 540;
        double meter_price = 25;
        double price = meter_price * (length / 100);
        return new Part("25x200mm. trykimp. Bræt Understern sider", length, 4, "stk", "Understernbrædder til siderne", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of boards used for the wall covering used for the the shed (Overstern in danish).
     */
    private static Part calcFasciaBoards3(Carport carport) {
        int length = 360;
        double meter_price = 14;
        double price = meter_price * (length / 100);
        return new Part("25x125mm. trykimp. Bræt Overstern ender", length, 2, "stk", "Oversternbrædder til forenden", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of boards used for the wall covering used for the the shed (Overstern in danish).
     */
    private static Part calcFasciaBoards4(Carport carport) {
        int length = 540;
        double meter_price = 14;
        double price = meter_price * (length / 100);
        return new Part("25x125mm. trykimp. Bræt Overstern sider", length, 4, "stk", "Oversternbrædder til siderne", price);
    }
    
 
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of waterboards used for the carport if the roof is sloped. (Vandbræt in danish).
     */
    private static Part calcWaterBoardSlope(Carport carport) {
        int length = 480;
        double meter_price = 7;
        double price = meter_price * (length / 100);
        return new Part("19x100mm. trykimp. Bræt Vandbræt Rejsning", length, 2, "stk", "Vandbræt på vindskeder", price);
    }
    

    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of waterboards used for the carport sides. (Vandbræt in danish).
     */
    private static Part calcWaterBoard1(Carport carport) {
        int length = 540;
        double meter_price = 7;
        double price = meter_price * (length / 100);
        return new Part("19x100mm. trykimp. Bræt Vandbræt Sider", length, 4, "stk", "Vandbræt på stern i sider", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of waterboards used for the carport ends. (Vandbræt in danish).
     */
    private static Part calcWaterBoard2(Carport carport) {
        int length = 360;
        double meter_price = 7;
        double price = meter_price * (length / 100);
        return new Part("19x100mm. trykimp. Bræt Vandbræt Ender", length, 2, "stk", "Vandbræt på stern i ender", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of barges used for the carport if the roof is sloped. (Vindskeder in danish).
     */
    private static Part calcBarge(Carport carport) {
        int length = 480;
        double meter_price = 25;
        double price = meter_price * (length / 100);
        return new Part("25x150mm. trykimp. Bræt Vindskede", length, 2, "stk", "Vindskeder på rejsning", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the lath for the z of the backside of the shed door. (Lægte in danish).
     */
    private static Part calcLathdoor(Carport carport) {
        int length = 420;
        double meter_price = 10;
        double price = meter_price * (length / 100);
        return new Part("38x73mm. Lægte ubh.", length, 1, "stk", "Til z på bagside af dør", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the amount of roof laths used for the carport if the roof is sloped. (Taglægte in danish).
     */
    private static Part calcRoofLaths1(Carport carport) {
        int length = 540;
        double meter_price = 10;
        double price = meter_price * (length / 100);
        return new Part("38x73mm. Taglægte T1.", length, 21, "stk", "Til montering på spær.", price);
    }

    
    /**
     * Help method used to calculate some of the parts needed for the carport (wooden parts).
     * @param carport predefined carport object with length and width.
     * @returns the rest of the amount of roof laths used for the carport if the roof is sloped. (Taglægte in danish).
     */
    private static Part calcRoofLaths2(Carport carport) {
        int length = 540;
        double meter_price = 10;
        double price = meter_price * (length / 100);
        return new Part("38x73mm. Taglægte T1. Rygsten", length, 21, "stk", "Toplægte til montering af rygsten.", price);
    }
    

}
