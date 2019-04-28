package logic.offer;

import data.help_classes.*;
import java.time.LocalDateTime;

/**
 *
 * @author ???
 */
public class OfferCalc {
    
    public static Offer generateOffer(PartsList parts, Request request) {
        double price = calculatePrice(parts);
        double shippingcosts = calculateShippingCosts(request);
        return new Offer(-1, LocalDateTime.now(), price, shippingcosts, request);
    }
    
    private static double calculatePrice(PartsList parts) {
        throw new UnsupportedOperationException();
    }
    
    private static double calculateShippingCosts(Request request) {
        throw new UnsupportedOperationException();
    }
    
}
