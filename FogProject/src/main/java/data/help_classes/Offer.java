/*
 */
package data.help_classes;

import java.time.LocalDateTime;

/**
 * @author Simon Asholt Norup
 */
public class Offer {

    private final int id;
    private final LocalDateTime sent;
    private final double price;
    private final double shipping_costs;
    private final Request request;

    public Offer(int id, LocalDateTime sent, double price, double shipping_costs, Request request) {
        if (sent == null || price < 0.0 || shipping_costs < 0.0 || request == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.sent = sent;
        this.price = price;
        this.shipping_costs = shipping_costs;
        this.request = request;
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

    public Request getRequest() {
        return request;
    }

    public int getId() {
        return id;
    }
}
