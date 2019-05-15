package data.customExceptions;

/**
 *
 * @author Henning
 */
public class NoRequestOnSessionException extends Exception {

    /**
     * This exception is used specifically when there is no request on the session. 
     * This is useful when other values are stored on the session but the request itself isn't.
     * 
     */
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
