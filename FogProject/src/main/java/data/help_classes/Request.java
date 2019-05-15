package data.help_classes;

import java.time.LocalDateTime;

/**
 * Entity class of a request (not an HTTP request).
 * Holds all nessescary information of an request.
 */
public class Request {

    private final int id;
    private final LocalDateTime sent;
    private final String comments;
    private final Carport carport;
    private Customer customer;
    private final boolean hasReceivedOffer;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param id unique integer value for each request.
     * @param sent local date time of the time where offer was sent.
     * @param comments a string that describes the request (personalized by the employee).
     * @param carport a carport object.
     * @param customer a customer object.
     * @param hasReceivedOffer boolean if the offer has been recieved.
     */
    public Request(int id, LocalDateTime sent, String comments, Carport carport, Customer customer, boolean hasReceivedOffer) {
        if (sent == null || carport == null || comments == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.sent = sent;
        this.comments = comments;
        this.carport = carport;
        this.customer = customer;
        this.hasReceivedOffer = hasReceivedOffer;
    }

    
    /**
     * Constructor used when the offer has not been received.
     * @param id unique integer value for each request.
     * @param sent local date time of the time where offer was sent.
     * @param comments a string that describes the request (personalized by the employee).
     * @param carport a carport object.
     * @param customer a customer object.     
     */
    public Request(int id, LocalDateTime sent, String comments, Carport carport, Customer customer) {
        this(id, sent, comments, carport, customer, false);
    }

    
    /**
     * Constructor used when the offer has not been received and theres no customer attached.
     * @param id unique integer value for each request.
     * @param sent local date time of the time where offer was sent.
     * @param comments a string that describes the request (personalized by the employee).
     * @param carport a carport object.   
     */
    public Request(int id, LocalDateTime sent, String comments, Carport carport) {
        this(id, sent, comments, carport, null, false);
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
