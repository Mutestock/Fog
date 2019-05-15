package data.help_classes;

import java.time.LocalDateTime;

/**
 * Entity class of an offer.
 * Holds all nessescary information of an offer.
 */
public class Offer {

    private final int id;
    private final LocalDateTime sent;
    private final double price;
    private final double shipping_costs;
    private Request request;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param id id the unique int value of the offer.
     * @param sent boolean wether the offer has been sent.
     * @param price integer value of the price.
     * @param shipping_costs integer value of the shipping cost.
     * @param request holds a request object (not http request).
     */
    public Offer(int id, LocalDateTime sent, double price, double shipping_costs, Request request) {
        if (sent == null || price < 0.0 || request == null) {
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
    
    public void setRequest(Request request) {
        this.request = request;
    }

    public int getId() {
        return id;
    }
}
