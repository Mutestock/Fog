package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
  * The class used to calclulate the amount of screws, fittings, and msc items, for a carport object.
 * @author Lukas Bjørnvad
 * @version 1.0
 */

public class FittingsAndScrewsCalc {

    /**
     * This initialises the different methods for calculating the smaller parts of the Bill of Materials (BoM).
     * Utilizes the part constructor when creating parts.
     * Utilizes the BoM generated by WoodCalc as many of the parts are needed for the calculations.
     * @param carport carport with length, width, shed or no shed, type of roof and height.
     * @param boM the part list for the wood. Needed for the calculations with relation to screws and fittings.
     * @return LinkedList with Parts 
     */
    public static LinkedList<Part> calculateParts(Carport carport, LinkedList<Part> boM) {
        int width = carport.getWidth();
        int length = carport.getLength();
        LinkedList<Part> boMScrews = new LinkedList<>();

        if (!carport.getRoof().getRaised()) {
            boMScrews.add(getPerforatedBand()); // hulbånd
            boMScrews.add(getRoofingScrews(length, width)); // skruer for tagplader
            boMScrews.add(getUniversalFittings(boM, true, carport)); // beslag for montering af spær på rem
            boMScrews.add(getUniversalFittings(boM, false, carport)); //  beslag for montering af spær på rem
            boMScrews.add(getScrewsBeams(boM, carport)); // Stern og vandbræt
            boMScrews.add(getScrewsFittings(boM, boMScrews));
            boMScrews.add(getBoardBolts(boM, carport));
            boMScrews.add(getSquarePiece(boMScrews, carport));

            if (carport.getShed() != null) {
                boMScrews.add(getDoorHandle()); // can't see a reason to give any parameters
                boMScrews.add(getDoorFittings()); // can't see a reason to give any parameters
                boMScrews.add(getAngledFittings(boM));
                boMScrews.add(getCoveringScrews(boM, true));
                boMScrews.add(getCoveringScrews(boM, false));
            }
        } else {
            boMScrews.add(getUniversalFittingsAngle(boM, true));
            boMScrews.add(getUniversalFittingsAngle(boM, false));
            boMScrews.add(getScrewsMsc(boM, carport)); //
            boMScrews.add(getScrewsFittingsAngle(boM, boMScrews)); // 
            boMScrews.add(getBoardBolts(boM, carport));
            boMScrews.add(getAngledSquarePiece(boMScrews));
            boMScrews.add(getScrewsRoofLath(boM, carport)); 

            if (carport.getShed() != null) {
                boMScrews.add(getDoorHandle()); // can't see a reason to give any parameters
                boMScrews.add(getDoorFittings()); // can't see a reason to give any parameters
                boMScrews.add(getAngledFittings(boM));
                boMScrews.add(getAngledShedCoveringScrews(boM, false));
                boMScrews.add(getAngledShedCoveringScrews(boM, true));
            }
        }

        return boMScrews;
    }
    // Screws for the fastening of the roofing plates
   // calculated based on the cm^2 of roof
    private static Part getRoofingScrews(int length, int width) {
        double screws = ((length / 100) * (width / 100)) * 12;
        int screwpacks = (int) Math.ceil(screws / 200);
        return new Part("plastmo bundskruer 200 stk.", screwpacks, "pk.", "Skruer til tagplader", 395);
    }
    //konstant size and amount
    private static Part getPerforatedBand() {
        return new Part("hulbånd 1x20 mm. 10 mtr.", 2, "rulle", "Til vindkryds på spær", 209);
    }
//  Fittings for fastening the poles to the roof
    private static Part getUniversalFittings(LinkedList<Part> boM, boolean right, Carport carport) {
        Part fittings = getPart("45x195mm. spærtræ ubh. spær", boM);
//        if(carport.getShed() != null){
//        fittings = getPart("45x195mm. spærtræ ubh. spær", boM);    
//        }
        if (right) {
            return new Part("universal 190 mm højre", fittings.getAmount(), "stk", "Til montering af spær på rem", 25);
        } else {
            return new Part("universal 190 mm venstre", fittings.getAmount(), "stk", "Til montering af spær på rem", 25);
        }
    }
//  Screws for attaching the beams at the ends and sides of the carport
    private static Part getScrewsBeams(LinkedList<Part> boM, Carport carport) {
        double amount = getPart("19x100mm. trykimp. Bræt Vandbræt Sider", boM).getAmount() + getPart("19x100mm. trykimp. Bræt Vandbræt Ender", boM).getAmount();
        if (carport.getShed() != null) {
            amount += getPart("25x200mm. trykimp. Bræt Understern ender", boM).getAmount() + getPart("25x200mm. trykimp. Bræt Understern sider", boM).getAmount()
                    + getPart("25x125mm. trykimp. Bræt Overstern ender", boM).getAmount()
                    + getPart("25x125mm. trykimp. Bræt Overstern sider", boM).getAmount();
        }
        amount = amount * 6;
        int ramount = (int) Math.ceil(amount / 200);
        return new Part("4,5 x 60 mm. skruer 200 stk.", ramount, "pk", "Til montering af stern & vandbrædt", 199);
    }
//  The screws for attaching the fittings
    private static Part getScrewsFittings(LinkedList<Part> boM, LinkedList<Part> boMScrews) {
        double amount = (getPart("universal 190 mm højre", boMScrews).getAmount() + getPart("universal 190 mm venstre", boMScrews).getAmount()) * 9;
        amount += (getPart("45x195mm. spærtræ ubh. spær", boM).getAmount() * 0.60) * 2;
        int ramount = (int) Math.ceil(amount / 250);
        return new Part("4,0 x 50 mm. beslagskruer 250 stk.", ramount, "pk", "Til montering af universalbeslag + hulbånd", 199);
    }

// Meget groft estimeret, skal nok kigge på den igen
//  calculate the bolts needed for fastening the poles
    private static Part getBoardBolts(LinkedList<Part> boM, Carport carport) {
        double amount;
        if (carport.getWidth() < 500) {
            if (carport.getShed() == null) {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 2; // fjerner 1 fordi der altid skal være en for meget
            } else {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 2 + 4;
            }
        } else {
            if (carport.getShed() == null) {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1)  * 2;
            } else {
                if(carport.getShed().getWidth()< carport.getWidth()-60){
                    amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 2 + 4;   
            }else{
                    amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1)*0.8 * 2 + 4;  
                    }
        }
      
    }  
        int ramount = (int) Math.round(amount);
        return new Part("bræddebolt 10 x 120 mm.", ramount,"stk", "Til montering af rem på stolper", 38.33);
    }
    // square pieces for protecting the wood when attaching the Board Bolts
    private static Part getSquarePiece(LinkedList<Part> boM, Carport carport) {
        double amount;
        if (carport.getShed() == null) {
            amount = (getPart("bræddebolt 10 x 120 mm.", boM).getAmount() - 1); // fjerner 1 fordi der altid skal være en for meget
        } else {
            amount = (getPart("bræddebolt 10 x 120 mm.", boM).getAmount() - 1) + 2;
        }
        int ramount = (int) Math.ceil(amount);
        return new Part("firkantskiver 40x40x11mm", ramount,"stk", "Til montering af rem på stolper", 10.11);
    }

    // Meget groft estimeret, skal nok kigge på den igen
    //Screws for the planks covering the shed inside and out
    private static Part getCoveringScrews(LinkedList<Part> boM, boolean outer) {
        double amount;
        int ramount;
        if (outer) {
            amount = getPart("19x100 mm. trykimp. Bræt Beklædning", boM).getAmount() * 8 / 400;
            ramount = (int) Math.ceil(amount);
            return new Part("4,5 x 70 mm. Skruer 400 stk.", ramount,"pk" ,"Til montering af yderste beklædning", 149);
        } else {
            amount = getPart("19x100 mm. trykimp. Bræt Beklædning", boM).getAmount() * 6 / 300;
            ramount = (int) Math.ceil(amount);
            return new Part("4,5 x 50 mm. Skruer 300 stk.", ramount,"pk" ,"Til montering af inderste beklædning", 129);
        }
    }
    // generic doorhandle, always 1 as there is only 1 door
    private static Part getDoorHandle() {
        return new Part("stalddørsgreb 50x75", 1,"sæt" ,"Til lås på dør i skur", 143);
    }
    // Normal door fittings, always 2 as there is only 1 door
    private static Part getDoorFittings() {
        return new Part("t hængsel 390 mm", 2,"stk", "Til skurdør", 63.96);
    }
    // The fittings for the shed with angeled roof
    private static Part getAngledFittings(LinkedList<Part> boM) {// løsholter
        int amount = getPart("45x95 Reglar ubh. Skur side", boM).getAmount() + getPart("45x95 Reglar ubh. Skur gavle", boM).getAmount(); // name should be updated as there is an overlap with something else
        return new Part("vinkelbeslag 35", amount,"stk", "Til montering af løsholter i skur", 25);
    }
    // Fittings for attaching the rafterboards (spær)
    private static Part getUniversalFittingsAngle(LinkedList<Part> boM, boolean right) {
        Part fittings = getPart("Færdigskåret byg-selv-spær ", boM);
        if (right) {
            return new Part("universal 190 mm højre", fittings.getAmount(),"stk" ,"Til montering af spær på rem", 25);// ' 8
        } else {
            return new Part("universal 190 mm venstre", fittings.getAmount(),"stk", "Til montering af spær på rem", 25);// ' 8
        }
    }
    // The screws for the attaching of the rafterboards
    private static Part getScrewsFittingsAngle(LinkedList<Part> boM, LinkedList<Part> boMScrews) {
        double amount = (getPart("universal 190 mm højre", boMScrews).getAmount() + getPart("universal 190 mm venstre", boMScrews).getAmount()) * 9;
        amount += getPart("45x195mm. spærtræ ubh. Carport rem", boM).getAmount() * getPart("Færdigskåret byg-selv-spær ", boM).getAmount();
        int ramount = (int) Math.ceil(amount / 250);
        return new Part("4,5 x 60 mm. Skruer 200 stk.", ramount, "pk.","Til montering af universalbeslag + toplægte", 199);
    }
    // screws for the msc items that needs to be attached in the shed with angled roof 
    private static Part getScrewsMsc(LinkedList<Part> boM, Carport carport) {
        double amount = getPart("25x150mm. trykimp. Bræt Carport stern rejsning", boM).getAmount();
        amount += getPart("25x150mm. trykimp. Bræt Vindskede", boM).getAmount();
        amount += getPart("19x100mm. trykimp. Bræt Vandbræt Rejsning", boM).getAmount();
        if (carport.getShed() != null) {
            amount += getPart("25x150mm. trykimp. Bræt Skur stern rejsning", boM).getAmount();
        }
        amount = amount * 6;
        int ramount = (int) Math.ceil(amount / 200);
        return new Part("4,5 x 60 mm. Skruer 200 stk.", ramount,"pk.", "Til montering af Stern, vindskeder, vindkryds & vandbræt", 199);
    }
    // Square pieces for protecting the wood when attaching board bolts, on the angled roof
    private static Part getAngledSquarePiece(LinkedList<Part> boMScrews) {
        Part boardBolts = getPart("bræddebolt 10 x 120 mm.", boMScrews);
        return new Part("firkantskiver 40x40x11mm", boardBolts.getAmount(),"stk","Til montering af rem på stolper", 10.11);
    }
    // screws for covering the shed inside and outside, angled roof
    private static Part getAngledShedCoveringScrews(LinkedList<Part> boM, boolean outer) {
        double amount;
        int ramount;
        if (outer) {
            amount = getPart("19x100 mm. trykimp. Bræt Beklædning", boM).getAmount() * 6 / 200;
            ramount = (int) Math.ceil(amount);
            return new Part("4,5 x 70 mm. Skruer 200 stk.", ramount,"pk." ,"til montering af yderste bræt ved beklædning", 149);
        } else {
            amount = getPart("19x100 mm. trykimp. Bræt Beklædning", boM).getAmount() * 6 / 350;
            ramount = (int) Math.ceil(amount);
            return new Part("4,5 x 50 mm. Skruer 350 stk.", ramount, "pk.","til montering af inderste bræt ved beklædning", 129);
        }
    }
    // Screws for attaching laths 
    private static Part getScrewsRoofLath(LinkedList<Part> boM, Carport carport) { 
        double amount = getPart("38x73mm. Taglægte T1.", boM).getAmount()
                + getPart("38x73mm. Taglægte T1. Rygsten", boM).getAmount();
        if (carport.getShed() != null) {
            amount += getPart("38x73mm. Lægte ubh.", boM).getAmount();
        }
        amount = amount * 6;
        int ramount = (int) Math.ceil(amount / 100);
        return new Part("5,0 x 100 mm. skruer 100 stk.", ramount,"pk.", "til taglægter", 189);
    }

    private static Part getPart(String name, LinkedList<Part> boM) {
        for (Part p : boM) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
