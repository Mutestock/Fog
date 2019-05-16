package data.customExceptions;

/**
 *
 * @author Henning
 */
public class InvalidSymbolException extends IllegalArgumentException {

    /**
     * This exception is used whenever the user tries to use symbols which aren't allowed in the name field. 
     * This is generally caught via regex. 
     */
    public InvalidSymbolException() {
    }
    public InvalidSymbolException(String message) {
        super(message);
    }
    public InvalidSymbolException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidSymbolException(Throwable cause) {
        super(cause);
    }
}
