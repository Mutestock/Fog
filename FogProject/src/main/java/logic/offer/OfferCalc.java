package logic.offer;

import data.help_classes.*;
import java.time.LocalDateTime;

/**
 *
 * @author Simon Asholt Norup
 */
public class OfferCalc {

    public static Offer generateOffer(PartsList parts, Request request) {
        double price = calculatePrice(parts);
        double shippingcosts = calculateShippingCosts(request);
        return new Offer(-1, LocalDateTime.now(), price, shippingcosts, request);
    }

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
//        int zipcode = 0;
//        System.out.println("ENTERED CALCSHIp");
//        System.out.println("ENTERED CALCSHIp");
//        System.out.println("ENTERED CALCSHIp");
//        try {
//            zipcode = Integer.parseInt(request.getCustomer().getZipcode());
//        } catch (Exception e) {
//            System.out.println("Entered Exception");
//            System.out.println("Entered Exception");
//            System.out.println("Entered Exception");
//            return -1;
//        }

        // Bliver altid leveret fredag
    }

    private static Location getLocation(int zipcode) {
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

    private static enum Location {
        SJAELLAND, FYN, JYLLAND
    }

}
