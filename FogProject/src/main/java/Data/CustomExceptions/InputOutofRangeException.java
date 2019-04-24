package Data.CustomExceptions;

/**
 *
 * @author Henning
 */
public class InputOutofRangeException extends Exception{

    public InputOutofRangeException() {
    }

    public InputOutofRangeException(String message) {
        super(message);
    }

    public InputOutofRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputOutofRangeException(Throwable cause) {
        super(cause);
    }

    public InputOutofRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}
