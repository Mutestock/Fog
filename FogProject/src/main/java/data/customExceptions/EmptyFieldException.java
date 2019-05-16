
package data.customExceptions;

/**
 *
 * @author Henning
 */
public class EmptyFieldException extends Exception{

    /**
     *This exception is used whenever the user doesn't insert information into the necessary fields in the .jsp site.
     * 
     */
    public EmptyFieldException() {
    }
    public EmptyFieldException(String message) {
        super(message);
    }
    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyFieldException(Throwable cause) {
        super(cause);
    }
    public EmptyFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
  
    
}
