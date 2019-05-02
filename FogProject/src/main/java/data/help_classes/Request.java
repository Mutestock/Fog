/*
 */
package data.help_classes;

import java.time.LocalDateTime;

/**
 * @author Simon Asholt Norup
 */
public class Request {

    private final int id;
    private final LocalDateTime sent;
    private final String comments;
    private final Carport carport;
    private final Customer customer;
    private final boolean hasReceivedOffer;
    
    public Request(int id, LocalDateTime sent, String comments, Carport carport, Customer customer, boolean hasReceivedOffer) {
        if (sent == null || comments == null || carport == null || customer == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.sent = sent;
        this.comments = comments;
        this.carport = carport;
        this.customer = customer;
        this.hasReceivedOffer = hasReceivedOffer;
    }

    public Request(int id, LocalDateTime sent, String comments, Carport carport, Customer customer) {
        this(id, sent, comments, carport, customer, false);
    }

    public LocalDateTime getSent() {
        return sent;
    }

    public String getComments() {
        return comments;
    }

    public Carport getCarport() {
        return carport;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public int getId() {
        return id;
    }

    public boolean hasReceivedOffer() {
        return hasReceivedOffer;
    }
    
}
