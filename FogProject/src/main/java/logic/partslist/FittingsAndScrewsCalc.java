package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Lukas Bjørnvad
 */
public class FittingsAndScrewsCalc {

    public static LinkedList<Part> calculateParts(Carport carport, LinkedList<Part> boM) {
        int width = carport.getWidth();
        int length = carport.getLength();
        LinkedList<Part> boMScrews = new LinkedList<>();
        if (carport.getRoof().getSlope() == 0) {
            boMScrews.add(getPerforatedBand());
            boMScrews.add(getRoofingScrews(length, width));
            boMScrews.add(getUniversalFittings(boM, true));
            boMScrews.add(getUniversalFittings(boM, false));
            boMScrews.add(getScrewsWoodbeam2(boM));
            boMScrews.add(getScrewsFittings(boM, boMScrews));
            boMScrews.add(getBoardBolts(boM, carport));
            boMScrews.add(getSquarePiece(boM, carport));

            if (carport.getShed() != null) {
                boMScrews.add(getDoorHandle()); // can't see a reason to give any parameters
                boMScrews.add(getDoorFittings()); // can't see a reason to give any parameters
//                boMScrews.add(getAngledFittings(boM));
//                boMScrews.add(getCoveringScrews(boM, true));
//                boMScrews.add(getCoveringScrews(boM, false));
            }
        } else {
            boMScrews.add(getUniversalFittingsAngle(boM, true));
            boMScrews.add(getUniversalFittingsAngle(boM, false));
            //boMScrews.add(getScrewsMsc(boM)); // not done yet,  potentialt problem med taglægt siden den bruger taglægte og en anden bruger toplægte
            boMScrews.add(getScrewsFittingsAngle(boM, boMScrews)); // potentialt problem med taglægt siden den bruger toplægte og en anden bruger taglægte
            boMScrews.add(getBoardBolts(boM, carport));
            boMScrews.add(getAngledSquarePiece(boMScrews));
//            boMScrews.add(getScrewsRoofLath(boM)); // potentialt problem med taglægt siden den bruger toplægte og en anden bruger taglægte

            if (carport.getShed() != null) {
                boMScrews.add(getDoorHandle()); // can't see a reason to give any parameters
                boMScrews.add(getDoorFittings()); // can't see a reason to give any parameters
//                boMScrews.add(getAngledFittings(boM));
//                boMScrews.add(getAngledShedCoveringScrews(boM, false));
//                boMScrews.add(getAngledShedCoveringScrews(boM, true));
            }
        }

        return boMScrews;
    }

    private static Part getRoofingScrews(int length, int width) {
        double screws = ((length / 100) * (width / 100)) * 12;
        int screwpacks = (int) Math.ceil(screws / 200);
        return new Part("Roofing screws", screwpacks, "Skruer til tagplader", 395);
    }

    private static Part getPerforatedBand() {
        return new Part("PerforatedBand", 10000, 2, "Til vindkryds på spær", 209);
    }

    private static Part getUniversalFittings(LinkedList<Part> boM, boolean right) {
        Part fittings = getPart("45x195mm. spærtræ ubh. spær", boM);
        if (right) {
            return new Part("Fittings right", fittings.getAmount(), "Til montering af spær på rem", 25);
        } else {
            return new Part("Fittings left", fittings.getAmount(), "Til montering af spær på rem", 25);
        }
    }

    private static Part getScrewsWoodbeam2(LinkedList<Part> boM) {
        double amount = getPart("Fittings Beam", boM).getAmount() * 8;
        int ramount = (int) Math.ceil(amount / 200);
        return new Part("Screws for attaching of woodbeam2", ramount, "Til montering af stern & vandbrædt", 199);
    }

    private static Part getScrewsFittings(LinkedList<Part> boM, LinkedList<Part> boMScrews) {
        double amount = (getPart("Fitttings right", boMScrews).getAmount() + getPart("Fitttings left", boMScrews).getAmount()) * 9;
        amount += (getPart("Wooden Rafter Board Beams", boM).getAmount() * 0.60) * 2;
        int ramount = (int) Math.ceil(amount / 250);
        return new Part("Screws Perforated", ramount, "Til montering af universalbeslag	+ hulbånd", 199);
    }

// Meget groft estimeret, skal nok kigge på den igen
// algoritmen  mangler også at tage højde for en masse små ting
    private static Part getBoardBolts(LinkedList<Part> boM, Carport carport) {
        double amount = -1;
        if (carport.getWidth() > 500) {
            if (carport.getShed() == null) {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 2; // fjerner 1 fordi der altid skal være en for meget
            } else {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 2 + 4;
            }
        } else {
            if (carport.getShed() == null) {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 0.66 * 2;
            } else {
                amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) * 0.66 * 2 + 4; //  needs further development
            }
        }
        int ramount = (int) Math.round(amount);
        return new Part("Board Bolts", ramount, "Til montering af rem på stolper", 38.33);
    }

    private static Part getSquarePiece(LinkedList<Part> boM, Carport carport) {
        double amount = -1;
        if (carport.getShed() == null) {
            amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1); // fjerner 1 fordi der altid skal være en for meget
        } else {
            amount = (getPart("97x97mm. trykimp. stolpe ", boM).getAmount() - 1) + 2;
        }
        int ramount = (int) Math.ceil(amount);
        return new Part("Square Pieces", ramount, "Til montering af rem på stolper", 10.11);
    }

    // Meget groft estimeret, skal nok kigge på den igen
    private static Part getCoveringScrews(LinkedList<Part> boM, boolean outer) {
        double amount = -1;
        int ramount = 0;
        if (outer) {
            amount = getPart("Træ vægs bjælker", boM).getAmount() * 8 / 300;
            ramount = (int) Math.ceil(amount);
            return new Part("Coverscrews outer", ramount, "Til	montering af yderste beklædning", 149);
        } else {
            amount = getPart("Træ vægs bjælker", boM).getAmount() * 6 / 300;
            ramount = (int) Math.ceil(amount);
            return new Part("Coverscrews inner", ramount, "Til	montering af inderste beklædning", 129);
        }
    }

    private static Part getAngledFittings(LinkedList<Part> boM) {// løsholter
        int amount = getPart("Træ vægs bjælker", boM).getAmount(); // name should be updated as there is an overlap with something else
        return new Part("AngledFittings", amount, "Til montering af løsholter i skur", 25);
    }

    private static Part getDoorHandle() {
        return new Part("DoorHandle", 1, "Til lås på dør i skur", 143);
    }

    private static Part getDoorFittings() {
        return new Part("DoorFittings", 2, "Til	skurdør", 63.96);
    }

    private static Part getUniversalFittingsAngle(LinkedList<Part> boM, boolean right) {
        Part fittings = getPart("Færdigskåret byg-selv-spær ", boM);
        if (right) {
            return new Part("Fittings right", fittings.getAmount(), "Til montering af spær på rem", 25);// ' 8
        } else {
            return new Part("Fittings left", fittings.getAmount(), "Til montering af spær på rem", 25);// ' 8
        }
    }

    private static Part getScrewsFittingsAngle(LinkedList<Part> boM, LinkedList<Part> boMScrews) {
        double amount = (getPart("Fittings right", boMScrews).getAmount() + getPart("Fittings left", boMScrews).getAmount()) * 9;
        amount += getPart("45x195mm. spærtræ ubh. rem", boM).getAmount() * getPart("Færdigskåret byg-selv-spær ", boM).getAmount();
        int ramount = (int) Math.ceil(amount / 250);
        return new Part("Screws fittings Angled", ramount, "Til montering af universalbeslag	+ toplægte", 199);
    }

    private static Part getScrewsMsc(LinkedList<Part> boM) {
      double amount= getPart("Stern", boM).getAmount()+ getPart("VindSkeder", boM).getAmount()+getPart("Vindkryds", boM).getAmount()+getPart("Vandbræt", boM).getAmount();
      int ramount = (int) Math.ceil(amount/200);       
      return new Part("Screws msc", ramount,"Til montering af Stern, vindskeder, vindkryds & vandbræt",199);
    }
    private static Part getAngledSquarePiece(LinkedList<Part> boM) {
        Part boardBolts = getPart("Board Bolts", boM);
        return new Part("Square Pieces", boardBolts.getAmount(), "Til montering af rem på stolper", 10.11);
    }

    private static Part getAngledShedCoveringScrews(LinkedList<Part> boM, boolean outer) {
        double amount = -1;
        int ramount = 0;
        if (outer) {
            amount = getPart("Træ vægs bjælker", boM).getAmount() * 6 / 200;
            ramount = (int) Math.ceil(amount);
            return new Part("Coverscrews outer", ramount, "placeholder", 149);
        } else {
            amount = getPart("Træ vægs bjælker", boM).getAmount() * 6 / 350;
            ramount = (int) Math.ceil(amount);
            return new Part("Coverscrews inner", ramount, "placeholder", 129);
        }
    }

    private static Part getScrewsRoofLath(LinkedList<Part> boM) { // lægter mangler at være lavet
        double amount = getPart("Wooden Beam used as the strop", boM).getAmount() * 6;
        int ramount = (int) Math.ceil(amount / 100);
        return new Part("ScrewsRoof Lath", ramount, "Til montering af yderste beklædning", 189);
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
