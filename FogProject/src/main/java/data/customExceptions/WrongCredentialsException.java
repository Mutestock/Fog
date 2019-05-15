package data.customExceptions;

/**
 *
 * @author Henning
 */
public class WrongCredentialsException extends Exception {

    /**
     *  This method is used whenever the user attempts to log in without submitting the correct credentials.
     */
    public WrongCredentialsException() {
    }
    public WrongCredentialsException(String message) {
        super(message);
    }
    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
    public WrongCredentialsException(Throwable cause) {
        super(cause);
    }
    public WrongCredentialsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
