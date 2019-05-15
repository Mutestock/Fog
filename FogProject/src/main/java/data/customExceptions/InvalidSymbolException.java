package data.customExceptions;

/**
 *
 * @author Henning
 */
public class InvalidSymbolException extends IllegalArgumentException {

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
