package data.customExceptions;

/**
 *
 * @author Henning
 */
public class NoRequestOnSessionException extends Exception {

    public NoRequestOnSessionException() {
    }

    public NoRequestOnSessionException(String message) {
        super(message);
    }

    public NoRequestOnSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRequestOnSessionException(Throwable cause) {
        super(cause);
    }

    public NoRequestOnSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
