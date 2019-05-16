package data.customExceptions;

/**
 *
 * @author Henning
 */
public class EmptySessionException extends Exception{

    /**
     * This exception is used whenever the user attempts to enter a URL without the necessary information on the session.
     * e.g. if the user tries to enter the partslist without having logged in / a user on the session.
     * Also caught on missing width/height/roof etc..
     * Generally checks for null values.
     */
    public EmptySessionException() {
    }
    public EmptySessionException(String message) {
        super(message);
    }
    public EmptySessionException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptySessionException(Throwable cause) {
        super(cause);
    }
    public EmptySessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
