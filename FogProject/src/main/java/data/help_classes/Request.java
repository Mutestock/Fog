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

    public Request(int id, LocalDateTime sent, String comments, Carport carport, Customer customer) {
        if (sent == null || comments == null || carport == null || customer == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.sent = sent;
        this.comments = comments;
        this.carport = carport;
        this.customer = customer;
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
    
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
    
}
