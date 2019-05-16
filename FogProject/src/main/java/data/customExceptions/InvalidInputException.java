package data.customExceptions;

/**
 *
 * @author Henning
 */
public class InvalidInputException extends Exception {

    /**
     * This exception is used whenever the user tries to insert information which is not allowed in a field.
     * It's also used whenever the user fails to select information in sites which asks for you to select something else than the default value.
     * e.g. the carport design page where the drop down menu's default tap is "Please select length" but that tap in and of itself is invalid.
     */
    public InvalidInputException() {
    }
    public InvalidInputException(String message) {
        super(message);
    }
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidInputException(Throwable cause) {
        super(cause);
    }
    public InvalidInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
