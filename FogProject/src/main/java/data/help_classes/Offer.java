/*
 */
package data.help_classes;

import java.time.LocalDateTime;

/**
 * @author Simon Asholt Norup
 */
public class Offer {

    private final LocalDateTime sent;
    private final double price;
    private final double shipping_costs;
    private final Carport carport;

    public Offer(LocalDateTime sent, double price, double shipping_costs, Carport carport) {
        if (sent == null || price < 0.0 || shipping_costs < 0.0 || carport == null) {
            throw new IllegalArgumentException();
        }
        this.sent = sent;
        this.price = price;
        this.shipping_costs = shipping_costs;
        this.carport = carport;
    }

    public LocalDateTime getSent() {
        return sent;
    }

    public double getPrice() {
        return price;
    }

    public double getShippingCosts() {
        return shipping_costs;
    }

    public Carport getCarport() {
        return carport;
    }

}
