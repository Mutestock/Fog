/*
 */
package Data.help_classes;

import java.time.LocalDateTime;

/**
 * @author Simon Asholt Norup
 */
public class Request {

    private final LocalDateTime sent;
    private final String comments;
    private final Carport carport;

    public Request(LocalDateTime sent, String comments, Carport carport) {
        if (sent == null || comments == null || carport == null) {
            throw new IllegalArgumentException();
        }
        this.sent = sent;
        this.comments = comments;
        this.carport = carport;
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
}
