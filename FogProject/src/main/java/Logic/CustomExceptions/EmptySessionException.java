package Logic.CustomExceptions;

/**
 *
 * @author Henning
 */
public class EmptySessionException extends Exception{

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
