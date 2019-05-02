package logic.partslist;

import data.help_classes.Carport;
import data.help_classes.Part;
import java.util.LinkedList;

/**
 *
 * @author Lukas Bjørnvad
 */
public class FittingsAndScrewsCalc {
    
    public static LinkedList<Part> calculateParts(Carport carport, LinkedList<Part> boM){
        if(carport.getRoof().getSlope()==0){
           boM.add(new Part("perforatedBand", 10000, 2,"Hulbånd der er altid 2 når den er flad", 209));
           boM.add(getRoofingScrews(carport.getLength(), carport.getWidth()));
           boM.add(getUniversalFittings(boM, true));
           boM.add(getUniversalFittings(boM, false));
           boM.add(getScrewsWoodbeam2(boM));
        }
        
        return boM;
    }

    private static Part getRoofingScrews(int length, int width) {
     double screws = ((length/100)*(width/100))*12; 
     int screwpacks = (int) Math.round(screws/200);
     
     return new Part("Roofing screws", screwpacks, "The screws for the roof, 12 per m^2", 395);
    }

    private static Part getUniversalFittings(LinkedList<Part> boM, boolean right) {
        Part fittings = getPart("Wooden Rafter Board Beams", boM);
        if(right){
        return new Part("Fittings right", fittings.getAmount(), "fittings for attaching wooden rafters right side",  25);
        }else{
        return new Part("Fittings left", fittings.getAmount(), "fittings for attaching wooden rafters left side",  25);   
        }
    }

    private static Part getPart(String name, LinkedList<Part> boM) {
       for(Part p : boM){
           if(p.getName().equals(name)){
               return p;
           }
       }
       return null;
    }

    private static Part getScrewsWoodbeam2(LinkedList<Part> boM) {
        int amount = getPart("WoodenBeam")
        return new Part("Screws for attachingof woodbeam2", )
    }
}
