package logic.offer;

import data.help_classes.*;
import java.time.LocalDateTime;

/**
 *
 * @author Simon Asholt Norup
 */
public class OfferCalc {

    
    /**
     * 
     * @param parts LinkedList of part objects.
     * @param request request object used to get needed objects.
     * @return offer object based on the specifications.
     */
    public static Offer generateOffer(PartsList parts, Request request) {
        double price = calculatePrice(parts);
        double shippingcosts = calculateShippingCosts(request);
        return new Offer(-1, LocalDateTime.now(), price, shippingcosts, request);
    }

    
    /**
     * 
     * @param parts LinkedList of part objects.
     * @return the total price of the LinkedList of part objects.
     */
    private static double calculatePrice(PartsList parts) {
        double price = 0;
        for (Part part : parts.getWoodPackage()) {
            price += part.getTotalPrice();
        }
        for (Part part : parts.getRoofPackage()) {
            price += part.getTotalPrice();
        }
        for (Part part : parts.getFittingsAndScrews()) {
            price += part.getTotalPrice();
        }
        return price;
    }

    
    /**
     * 
     * @param request request object used to get customer zipcode.
     * @return a static price based on the customer zipcode.
     */
    private static double calculateShippingCosts(Request request) {
        if (request.getCustomer() == null) {
            return - 1;
        } else {
            final int zipcode = Integer.parseInt(request.getCustomer().getZipcode());
            final Location loc = getLocation(zipcode);
            switch (loc) {
                case SJAELLAND:
                    return 0;
                case FYN:
                    return 1475;
                case JYLLAND:
                    return 1875;
                default:
                    throw new AssertionError();
            }
        }
        // Bliver altid leveret fredag
    }

    
    /**
     * 
     * @param zipcode zipcode of the customer.
     * @return location based on the given zipcode.
     */
    public static Location getLocation(int zipcode) {
        if (zipcode >= 0 && zipcode < 3700) {
            return Location.SJAELLAND;
        }
        if (zipcode >= 4000 && zipcode < 5000) {
            return Location.SJAELLAND;
        }
        if (zipcode >= 5000 && zipcode < 6000) {
            return Location.FYN;
        }
        if (zipcode >= 6000 && zipcode < 10000) {
            return Location.JYLLAND;
        }
        throw new IllegalArgumentException();
    }

}
